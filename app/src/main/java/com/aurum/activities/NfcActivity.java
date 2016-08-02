package com.aurum.activities;

/*
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
*/

import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


import com.aurum.R;

import java.nio.charset.Charset;
import java.util.Locale;

public class NfcActivity extends AppCompatActivity {

    /*

    boolean mWriteMode = false;
    private NfcAdapter mNfcAdapter;
    private PendingIntent mNfcPendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);

        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mNfcAdapter = NfcAdapter.getDefaultAdapter(NfcActivity.this);
                mNfcPendingIntent = PendingIntent.getActivity(NfcActivity.this, 0,
                        new Intent(NfcActivity.this, NfcActivity.class).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

                enableTagWriteMode();

                new AlertDialog.Builder(NfcActivity.this).setTitle("Touch tag to write")
                        .setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {
                                disableTagWriteMode();
                            }

                        }).create().show();
            }
        });
    }

    private void enableTagWriteMode() {
        mWriteMode = true;
        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        IntentFilter[] mWriteTagFilters = new IntentFilter[] { tagDetected };
        mNfcAdapter.enableForegroundDispatch(this, mNfcPendingIntent, mWriteTagFilters, null);
    }

    private void disableTagWriteMode() {
        mWriteMode = false;
        mNfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        // Tag writing mode
        if (mWriteMode && NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {
            Tag detectedTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            NdefRecord record = NdefRecord.createMime( ((TextView)findViewById(R.id.mime)).getText().toString(), ((TextView)findViewById(R.id.value)).getText().toString().getBytes());
            NdefMessage message = new NdefMessage(new NdefRecord[] { record });
            if (writeTag(message, detectedTag)) {
                Toast.makeText(this, "Success: Wrote placeid to nfc tag", Toast.LENGTH_LONG)
                        .show();
            }
        }
    }

    public boolean writeTag(NdefMessage message, Tag tag) {
        int size = message.toByteArray().length;
        try {
            Ndef ndef = Ndef.get(tag);
            if (ndef != null) {
                ndef.connect();
                if (!ndef.isWritable()) {
                    Toast.makeText(getApplicationContext(),
                            "Error: tag not writable",
                            Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (ndef.getMaxSize() < size) {
                    Toast.makeText(getApplicationContext(),
                            "Error: tag too small",
                            Toast.LENGTH_SHORT).show();
                    return false;
                }
                ndef.writeNdefMessage(message);
                return true;
            } else {
                NdefFormatable format = NdefFormatable.get(tag);
                if (format != null) {
                    try {
                        format.connect();
                        format.format(message);
                        return true;
                    } catch (IOException e) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }
    }
*/

    /*

    private NfcAdapter nfcAdapter;

    PendingIntent pendingIntent;
    IntentFilter intentFilters[];

    boolean writeMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);


        if(nfcAdapter != null && nfcAdapter.isEnabled()) {
            Toast.makeText(this, "NFC Enabled", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "NFC Disabled", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "NFC Enabled Resume", Toast.LENGTH_LONG).show();
        enableForegroundDispatchSystem();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "NFC DIsable Pause", Toast.LENGTH_LONG).show();
        disableForegroundDisableSystem();
    }

    private boolean enableForegroundDispatchSystem() {
        Intent intent = new Intent(NfcActivity.this, NfcActivity.class).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);


        intentFilters = new IntentFilter[] {};


        String[][] techList = new String[][] {
                { android.nfc.tech.Ndef.class.getName() },
                { android.nfc.tech.NdefFormatable.class.getName() }
        };

        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilters, techList);
        return true;
    }

    private boolean disableForegroundDisableSystem() {
        nfcAdapter.disableForegroundDispatch(this);
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {

        Toast.makeText(this, "New Intent", Toast.LENGTH_LONG).show();

        if(intent.hasCategory(NfcAdapter.EXTRA_TAG)) {
            Toast.makeText(this, "NFC Intent", Toast.LENGTH_LONG).show();

            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            NdefMessage ndefMessage = createNdefMessage("Hellooo World from NFC");

            writeNdefMessage(tag, ndefMessage);
        }
    }

    private void formatTag(Tag tag, NdefMessage message) {
        try {

            NdefFormatable ndefFormatable = NdefFormatable.get(tag);

            if(ndefFormatable == null) {
                Toast.makeText(this, "Tag is nor formattable", Toast.LENGTH_LONG).show();
            }

            ndefFormatable.connect();
            ndefFormatable.format(message);
            ndefFormatable.close();

        } catch (Exception e) {
            Log.d("formatTag", e.getMessage());
        }
    }

    private void writeNdefMessage(Tag tag, NdefMessage message) {

        try {

            if(tag == null) {
                Toast.makeText(this, "Tag object can not be null", Toast.LENGTH_LONG).show();
                return;
            }

            Ndef ndef = Ndef.get(tag);

            if(ndef == null) {
                formatTag(tag, message);
            } else {
                ndef.connect();

                if(!ndef.isWritable()) {
                    Toast.makeText(this, "Tag is not writable", Toast.LENGTH_LONG).show();
                    ndef.close();
                    return;
                }

                ndef.writeNdefMessage(message);
                ndef.close();

                Toast.makeText(this, "Text written", Toast.LENGTH_LONG).show();
            }


        } catch (Exception e) {
            Log.d("writeNdefMessage", e.getMessage());
        }

    }

    private NdefRecord createTextRecord(String content) {
        try {
            byte[] language;
            language = Locale.getDefault().getLanguage().getBytes("UTF-8");

            final byte[] text = content.getBytes("UTF-8");
            final int languageSize = language.length;
            final int textSize = text.length;

            final ByteArrayOutputStream outputStream = new ByteArrayOutputStream(1 + languageSize + textSize);

            outputStream.write((byte) languageSize & 0x1f);
            outputStream.write(language, 0 , languageSize);
            outputStream.write(text, 0 , textSize);

            return new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, new byte[0], outputStream.toByteArray());



        } catch(Exception e) {
            Log.d("createTextRecord", e.getMessage());
        }

        return null;
    }

    private NdefMessage createNdefMessage(String content) {
        NdefRecord ndefRecord = createTextRecord(content);
        NdefMessage ndefMessage = new NdefMessage(new NdefRecord[] {ndefRecord});
        return  ndefMessage;
    }
*/
    private NfcAdapter mNfcAdapter;
    private TextView mTextView;
    private NdefMessage mNdefMessage;

    @Override
    public void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        setContentView(R.layout.activity_nfc);

        mTextView = (TextView)findViewById(R.id.sample_output);

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if (mNfcAdapter != null) {
            mTextView.setText("Tap to beam to another NFC device");
        } else {
            mTextView.setText("This phone is not NFC enabled.");
        }

        // create an NDEF message with two records of plain text type
        mNdefMessage = new NdefMessage(
                new NdefRecord[] {
                        createNewTextRecord("First sample NDEF text record", Locale.ENGLISH, true),
                        createNewTextRecord("Second sample NDEF text record", Locale.ENGLISH, true) });
    }

    public static NdefRecord createNewTextRecord(String text, Locale locale, boolean encodeInUtf8) {
        byte[] langBytes = locale.getLanguage().getBytes(Charset.forName("US-ASCII"));

        Charset utfEncoding = encodeInUtf8 ? Charset.forName("UTF-8") : Charset.forName("UTF-16");
        byte[] textBytes = text.getBytes(utfEncoding);

        int utfBit = encodeInUtf8 ? 0 : (1 << 7);
        char status = (char)(utfBit + langBytes.length);

        byte[] data = new byte[1 + langBytes.length + textBytes.length];
        data[0] = (byte) status;
        System.arraycopy(langBytes, 0, data, 1, langBytes.length);
        System.arraycopy(textBytes, 0, data, 1 + langBytes.length, textBytes.length);
        return new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, new byte[0], data);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mNfcAdapter != null)
            mNfcAdapter.enableForegroundNdefPush(this, mNdefMessage);
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mNfcAdapter != null)
            mNfcAdapter.disableForegroundNdefPush(this);
    }

}

package com.aurum.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.aurum.R;
import com.aurum.listeners.PinDialogListener;

public class PinDialog extends DialogFragment {

    PinDialogListener listener;

    public PinDialog() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /*

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pin_dialog, container, false);
        getDialog().setTitle("Simple Dialog");
        return rootView;
    }
    */

    public static PinDialog newInstance(int title) {
        PinDialog fragment = new PinDialog();
        Bundle args = new Bundle();
        args.putInt("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        int title = getArguments().getInt("title");

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View authPin = inflater.inflate(R.layout.dialog_auth_pin, null);

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomDialog);
        builder.setCancelable(true);

        final EditText enterPin = (EditText) authPin.findViewById(R.id.authPin);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialog, int id) {

            }
        });

        final AlertDialog dialog = builder.create();
        dialog.setView(authPin);
        dialog.show();

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (enterPin.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Field should not be empty", Toast.LENGTH_LONG).show();
                } else {
                    if (enterPin.getText().toString().length() < 4) {
                        Toast.makeText(getActivity(), "PIN should 4 numbers", Toast.LENGTH_LONG).show();
                    } else {
                        PinDialogListener listener = (PinDialogListener) getTargetFragment();
                        listener.okClickListener();
                    }
                }
            }
        });
/*


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_auth_pin, null);
        builder.setView(view);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.okClickListener();
                //Log.d("DIALOG", "POSITIVE");
                //dismiss();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("DIALOG", "NEGATIVE");
                dismiss();
            }
        });*/

        return dialog;
    }
}

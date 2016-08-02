package com.aurum.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.StateListDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.aurum.R;
import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.BraintreePaymentActivity;
import com.braintreepayments.api.PaymentRequest;
import com.braintreepayments.api.exceptions.InvalidArgumentException;
import com.braintreepayments.api.interfaces.BraintreeResponseListener;
import com.braintreepayments.api.interfaces.PaymentMethodNonceCreatedListener;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.google.android.gms.common.api.GoogleApiClient;

public class DropInActivity extends AppCompatActivity {

    private Button mDropInButton;

    private RadioGroup denominationGroup;
    private ViewGroup hourButtonLayout;

    private static final int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop_in);

        // Brightness programatically
        /*
        WindowManager.LayoutParams layout = getWindow().getAttributes();
        layout.screenBrightness = 1F;
        getWindow().setAttributes(layout);
        */

        hourButtonLayout = (ViewGroup) findViewById(R.id.radioGroup1);

        mDropInButton = (Button) findViewById(R.id.btn_drop_in);
        denominationGroup = (RadioGroup) findViewById(R.id.radioGroup1);



        for (int i = 0; i < 3; i++) {
            RadioButton button = new RadioButton(this);
            button.setId(i);
            button.setText("$" + i);
            button.setTextSize(18.00f);
            button.setPadding(10, 10, 10, 10);
            button.setButtonDrawable(new StateListDrawable());
            button.setBackground(getResources().getDrawable(R.drawable.rbtn_selector));
            button.setTextColor(getResources().getColorStateList(R.color.rbtn_textcolor_selector));
            denominationGroup.addView(button);
        }

    }

    public void onBraintreeSubmit(View v) {
        PaymentRequest paymentRequest = new PaymentRequest()
                .tokenizationKey("sandbox_kyb9qysm_c5gqq63fntzzjzgp")
                .primaryDescription("Buy Gift Card")
                .secondaryDescription("1 Item")
                .amount("$1.00")
                .defaultFirst(true);

        startActivityForResult(paymentRequest.getIntent(this), REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    PaymentMethodNonce paymentMethodNonce = data.getParcelableExtra(
                            BraintreePaymentActivity.EXTRA_PAYMENT_METHOD_NONCE
                    );
                    String nonce = paymentMethodNonce.getNonce();
                    Toast.makeText(this, nonce, Toast.LENGTH_SHORT).show();
                    break;
                case BraintreePaymentActivity.BRAINTREE_RESULT_DEVELOPER_ERROR:
                case BraintreePaymentActivity.BRAINTREE_RESULT_SERVER_ERROR:
                case BraintreePaymentActivity.BRAINTREE_RESULT_SERVER_UNAVAILABLE:
                    break;
                default:
                    break;
            }
        }
    }

}

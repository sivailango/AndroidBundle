package com.aurum.activities;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aurum.R;
import com.braintreepayments.cardform.OnCardFormSubmitListener;
import com.braintreepayments.cardform.view.CardForm;

public class CreditCardFormActivity extends AppCompatActivity implements OnCardFormSubmitListener {

    CardForm cardForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card_form);

        cardForm = (CardForm) findViewById(R.id.bt_card_form);
        cardForm.setRequiredFields(CreditCardFormActivity.this, true, true, true, true, "Purchase");
        cardForm.setOnCardFormSubmitListener(this);

        Button button = (Button) findViewById(R.id.btn_purchase);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(CreditCardFormActivity.this, cardForm.getCardNumber(), Toast.LENGTH_SHORT).show();

                Toast.makeText(CreditCardFormActivity.this, "asdasd", Toast.LENGTH_SHORT).show();
                if (cardForm.isValid()) {
                    Toast.makeText(CreditCardFormActivity.this, "Valid", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CreditCardFormActivity.this, "Invalid", Toast.LENGTH_SHORT).show();
                }

            }
        });

        /*
        cardForm.setOnCardFormSubmitListener(new OnCardFormSubmitListener() {
            @Override
            public void onCardFormSubmit() {

            }
        });

        cardForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreditCardFormActivity.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });*/


    }


    @Override
    public void onCardFormSubmit() {
        Toast.makeText(CreditCardFormActivity.this, "asdasd", Toast.LENGTH_SHORT).show();
        if (cardForm.isValid()) {
            //Toast.makeText(this, R.string.valid, Toast.LENGTH_SHORT).show();
        } else {
           // Toast.makeText(this, R.string.invalid, Toast.LENGTH_SHORT).show();
        }
    }
}

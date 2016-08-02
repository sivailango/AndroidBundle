package com.aurum.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.aurum.R;
import com.braintreepayments.api.AndroidPay;
import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.BraintreePaymentActivity;
import com.braintreepayments.api.PaymentButton;
import com.braintreepayments.api.PaymentRequest;
import com.braintreepayments.api.exceptions.InvalidArgumentException;
import com.braintreepayments.api.interfaces.BraintreeResponseListener;
import com.braintreepayments.api.interfaces.PaymentMethodNonceCreatedListener;
import com.braintreepayments.api.interfaces.TokenizationParametersListener;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wallet.Cart;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.LineItem;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.PaymentMethodTokenizationParameters;
import com.google.android.gms.wallet.PaymentMethodTokenizationType;
import com.google.android.gms.wallet.Wallet;
import com.google.android.gms.wallet.WalletConstants;
import com.google.android.gms.wallet.fragment.SupportWalletFragment;
import com.google.android.gms.wallet.fragment.WalletFragment;
import com.google.android.gms.wallet.fragment.WalletFragmentInitParams;
import com.google.android.gms.wallet.fragment.WalletFragmentMode;
import com.google.android.gms.wallet.fragment.WalletFragmentOptions;
import com.google.android.gms.wallet.fragment.WalletFragmentStyle;

import java.io.ByteArrayOutputStream;
import java.util.Collection;

public class AndroidPayActivity extends AppCompatActivity implements
        PaymentMethodNonceCreatedListener, View.OnClickListener {

    private BraintreeFragment mBraintreeFragment;
    private GoogleApiClient mGoogleApiClient;

    private SupportWalletFragment mWalletFragment;

    private static final int ANDROID_PAY_MASKED_WALLET_REQUEST_CODE = 1;
    private static final int ANDROID_PAY_FULL_WALLET_REQUEST_CODE = 2;

    Cart cart;

    //PaymentButton mPaymentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_pay);

        try {
            mBraintreeFragment = BraintreeFragment.newInstance(this, "sandbox_kyb9qysm_c5gqq63fntzzjzgp");
            mBraintreeFragment.getGoogleApiClient(new BraintreeResponseListener<GoogleApiClient>() {
                @Override
                public void onResponse(GoogleApiClient googleApiClient) {
                    mGoogleApiClient = googleApiClient;

                }
            });
        } catch (InvalidArgumentException e) {

        }

        AndroidPay.isReadyToPay(mBraintreeFragment, new BraintreeResponseListener<Boolean>() {
            @Override
            public void onResponse(Boolean isReadyToPay) {
                Toast.makeText(AndroidPayActivity.this, "Android Pay: " + isReadyToPay, Toast.LENGTH_LONG).show();

                if (isReadyToPay) {
                    Log.d("TAG", "Android Pay: " + isReadyToPay);

                }
            }
        });

        WalletFragmentStyle walletFragmentStyle = new WalletFragmentStyle()
                .setBuyButtonText(WalletFragmentStyle.BuyButtonText.BUY_WITH)
                .setBuyButtonAppearance(WalletFragmentStyle.BuyButtonAppearance.ANDROID_PAY_DARK)
                .setBuyButtonWidth(WalletFragmentStyle.Dimension.MATCH_PARENT);

        WalletFragmentOptions walletFragmentOptions = WalletFragmentOptions.newBuilder()
                .setEnvironment(WalletConstants.ENVIRONMENT_TEST)
                .setFragmentStyle(walletFragmentStyle)
                .setTheme(WalletConstants.THEME_LIGHT)
                .setMode(WalletFragmentMode.BUY_BUTTON)
                .build();

        mWalletFragment = SupportWalletFragment.newInstance(walletFragmentOptions);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.btn_payment, mWalletFragment)
                .commit();

        cart = Cart.newBuilder()
                .setCurrencyCode("USD")
                .setTotalPrice("20.00")
                .addLineItem(LineItem.newBuilder()
                        .setCurrencyCode("USD")
                        .setDescription("Description")
                        .setQuantity("1")
                        .setUnitPrice("20.00")
                        .setTotalPrice("20.00")
                        .build())
                .build();

        PaymentMethodTokenizationParameters.Builder parameters = PaymentMethodTokenizationParameters.newBuilder()
                .setPaymentMethodTokenizationType(PaymentMethodTokenizationType.PAYMENT_GATEWAY)
                .addParameter("gateway", "braintree")
                .addParameter("braintree:clientKey", "sandbox_kyb9qysm_c5gqq63fntzzjzgp");

        MaskedWalletRequest maskedWalletRequest = MaskedWalletRequest.newBuilder()
                .setMerchantName("Comics")
                .setPhoneNumberRequired(true)
                .setShippingAddressRequired(true)
                .setCurrencyCode("USD")
                .setEstimatedTotalPrice("20.00")
                .setCart(cart)
                .setPaymentMethodTokenizationParameters(parameters.build())
                .build();

        WalletFragmentInitParams.Builder startParamsBuilder = WalletFragmentInitParams.newBuilder()
                .setMaskedWalletRequest(maskedWalletRequest)
                .setMaskedWalletRequestCode(1)
                .setAccountName("Comics");

        mWalletFragment.initialize(startParamsBuilder.build());

    }

    private void startAndroidPay(final String amount) {
        AndroidPay.getTokenizationParameters(mBraintreeFragment, new TokenizationParametersListener() {
            @Override
            public void onResult(PaymentMethodTokenizationParameters parameters, Collection<Integer> allowedCardNetworks) {

                cart = Cart.newBuilder()
                        .setCurrencyCode("USD")
                        .setTotalPrice("20.00")
                        .addLineItem(LineItem.newBuilder()
                                .setCurrencyCode("USD")
                                .setDescription("Description")
                                .setQuantity("1")
                                .setUnitPrice("20.00")
                                .setTotalPrice("20.00")
                                .build())
                        .build();

                MaskedWalletRequest maskedWalletRequest = MaskedWalletRequest.newBuilder()
                        .setMerchantName("Comics")
                        .setPhoneNumberRequired(true)
                        .setShippingAddressRequired(true)
                        .setCurrencyCode("USD")
                        .setEstimatedTotalPrice("20.00")
                        .setCart(cart)
                        .setPaymentMethodTokenizationParameters(parameters)
                        .build();

                WalletFragmentInitParams.Builder startParamsBuilder = WalletFragmentInitParams.newBuilder()
                        .setMaskedWalletRequest(maskedWalletRequest)
                        .setMaskedWalletRequestCode(1)
                        .setAccountName("Comics");

                mWalletFragment.initialize(startParamsBuilder.build());

                /*
                cart = Cart.newBuilder()
                        .setCurrencyCode("USD")
                        .setTotalPrice(amount)
                        .addLineItem(LineItem.newBuilder()
                                .setCurrencyCode("USD")
                                .setDescription("Description")
                                .setQuantity("1")
                                .setUnitPrice(amount)
                                .setTotalPrice(amount)
                                .build())
                        .build();

                MaskedWalletRequest.Builder maskedWalletRequestBuilder =
                        MaskedWalletRequest.newBuilder()
                                .setMerchantName("Comics Conspiracy")
                                .setCurrencyCode("USD")
                                .setCart(cart)
                                .setEstimatedTotalPrice(amount)
                                .setPaymentMethodTokenizationParameters(parameters)
                                .addAllowedCardNetworks(allowedCardNetworks);

                Wallet.Payments.loadMaskedWallet(mGoogleApiClient,
                        maskedWalletRequestBuilder.build(), ANDROID_PAY_MASKED_WALLET_REQUEST_CODE);

                        */
            }
        });
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data)	{
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK || requestCode == 100) {

            if (requestCode == ANDROID_PAY_MASKED_WALLET_REQUEST_CODE) {

                String googleTransactionId =
                        ((MaskedWallet) data.getParcelableExtra(WalletConstants.EXTRA_MASKED_WALLET))
                                .getGoogleTransactionId();

                FullWalletRequest fullWalletRequest = FullWalletRequest.newBuilder()
                        .setGoogleTransactionId(googleTransactionId)
                        .setCart(cart)
                        .build();

                Wallet.Payments.loadFullWallet(mGoogleApiClient, fullWalletRequest,
                        ANDROID_PAY_FULL_WALLET_REQUEST_CODE);

            } else if (requestCode == ANDROID_PAY_FULL_WALLET_REQUEST_CODE) {
                AndroidPay.tokenize(mBraintreeFragment,
                        (FullWallet) data.getParcelableExtra(WalletConstants.EXTRA_FULL_WALLET));
            }

        } else if (resultCode == RESULT_CANCELED) {
        } else {
            int errorCode = -1;
            if (data != null) {
                errorCode = data.getIntExtra(WalletConstants.EXTRA_ERROR_CODE, -1);
            }
        }

    }

    @Override
    public void onPaymentMethodNonceCreated(PaymentMethodNonce paymentMethodNonce) {
        paymentMethodNonce.getNonce();
    }

    @Override
    public void onClick(View v) {

    }
}

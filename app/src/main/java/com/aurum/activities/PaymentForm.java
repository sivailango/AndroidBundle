package com.aurum.activities;

/**
 * Created by root on 12/8/16.
 */
public interface PaymentForm {
    public String getCardNumber();
    public String getCvc();
    public Integer getExpMonth();
    public Integer getExpYear();
    public String getCurrency();
}

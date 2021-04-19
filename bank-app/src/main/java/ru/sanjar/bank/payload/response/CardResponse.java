package ru.sanjar.bank.payload.response;

import java.util.Date;

public class CardResponse {
    private final String number;
    private final Date validityPeriod;
    private final Double balance;
    private final String CVV;
    private final String currency;

    public CardResponse(String number, Date validityPeriod, Double balance, String CVV, String currency) {
        this.number = number;
        this.validityPeriod = validityPeriod;
        this.balance = balance;
        this.CVV = CVV;
        this.currency = currency;
    }

    public String getNumber() {
        return number;
    }

    public Date getValidityPeriod() {
        return validityPeriod;
    }

    public Double getBalance() {
        return balance;
    }

    public String getCVV() {
        return CVV;
    }

    public String getCurrency() {
        return currency;
    }
}

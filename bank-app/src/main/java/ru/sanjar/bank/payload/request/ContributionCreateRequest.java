package ru.sanjar.bank.payload.request;

import java.util.Date;

public class ContributionCreateRequest {
    private String card;
    private Date date;
    private Double amount;

    public ContributionCreateRequest(String card, Date date, Double amount) {
        this.card = card;
        this.date = date;
        this.amount = amount;
    }

    public ContributionCreateRequest() {
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}

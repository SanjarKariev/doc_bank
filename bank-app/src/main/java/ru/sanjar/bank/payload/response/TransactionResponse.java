package ru.sanjar.bank.payload.response;

import java.util.Date;

public class TransactionResponse {
    private String id;
    private Date date;
    private String from;
    private String to;
    private Double amount;
    private String type;

    public TransactionResponse(String id, Date date, String from, String to, Double amount, String type) {
        this.id = id;
        this.date = date;
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public Double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }
}

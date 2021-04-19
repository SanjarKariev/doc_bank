package ru.sanjar.bank.payload.request;

public class TransferRequest {
    private String type;
    private String cardNumberFrom;
    private String cardNumberTo;
    private Double amount;

    public TransferRequest() {
    }

    public String getType() { return type; }

    public String getCardNumberFrom() {
        return cardNumberFrom;
    }

    public String getCardNumberTo() {
        return cardNumberTo;
    }

    public Double getAmount() {
        return amount;
    }
}

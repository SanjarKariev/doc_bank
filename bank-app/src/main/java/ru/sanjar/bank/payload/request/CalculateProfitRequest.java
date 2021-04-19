package ru.sanjar.bank.payload.request;

import java.util.Date;

public class CalculateProfitRequest {
    private Date startDate;
    private Date endDate;
    private Double amount;

    public CalculateProfitRequest(Date startDate, Date endDate, Double amount) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
    }

    public CalculateProfitRequest() {
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}

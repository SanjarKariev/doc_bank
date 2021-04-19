package ru.sanjar.bank.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.sanjar.bank.payload.response.TransactionResponse;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    private String id;
    private Date date;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "from_id", nullable = true)
    private Card from;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "to_id", nullable = true)
    private Card to;

    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ETransferType type;

    public Transaction(Card from, Card to, Double amount, ETransferType type) {
        this.id = UUID.randomUUID().toString();
        this.date = new Date();
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.type = type;
    }

    public Transaction(Card from, Double amount, ETransferType type) {
        this.id = UUID.randomUUID().toString();
        this.date = new Date();
        this.from = from;
        this.amount = amount;
        this.type = type;
    }

    public Transaction(int i, Card to, Double amount, ETransferType type) {
        this.id = UUID.randomUUID().toString();
        this.date = new Date();
        this.to = to;
        this.amount = amount;
        this.type = type;
    }

    public Transaction() {

    }

    public TransactionResponse toResponse() {
        String toId = "";
        String fromId = "";

        try {
            if (this.from.getId() != null) {
                fromId = this.from.getId();
            }
        } catch (NullPointerException ignored) {}

        try {
            if (this.to.getId() != null) {
                toId = this.to.getId();
            }
        } catch (NullPointerException ignored) {}


        return new TransactionResponse(this.id, this.date, fromId, toId, this.amount, this.type.name());
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Card getFrom() {
        return from;
    }

    public void setFrom(Card from) {
        this.from = from;
    }

    public Card getTo() {
        return to;
    }

    public void setTo(Card to) {
        this.to = to;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public ETransferType getType() {
        return type;
    }

    public void setType(ETransferType type) {
        this.type = type;
    }
}

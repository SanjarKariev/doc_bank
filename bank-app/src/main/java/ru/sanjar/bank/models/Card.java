package ru.sanjar.bank.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.sanjar.bank.payload.response.CardResponse;

import javax.persistence.*;
import java.util.Date;
import java.util.Random;
import java.util.Set;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    private String id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    private Date validityPeriod;
    private Double balance;
    private String CVV;

    @Enumerated(EnumType.STRING)
    @Column(length = 3)
    private ECurrency currency;

    @JsonIgnore
    @OneToMany(mappedBy="to", fetch = FetchType.EAGER)
    private Set<Transaction> taken;

    @JsonIgnore
    @OneToMany(mappedBy="from", fetch = FetchType.EAGER)
    private Set<Transaction> send;

    public Card(User owner, ECurrency currency) {
        this.id = "";

        Random random = new Random();
        for (int i = 0; i <= 3; i++) {
            id += (String.valueOf(random.nextInt(8999) + 1000));
        }

        this.owner = owner;
        Date date = new Date();
        date.setMonth(date.getMonth() + 6);
        this.validityPeriod = date;
        this.balance = 0.0;
        this.currency = currency;
        this.CVV = String.valueOf(random.nextInt(899) + 100);
    }

    public boolean isActive() {
        return this.validityPeriod.getTime() >= new Date().getTime();
    }

    public void rebuild() {
        Date date = new Date();
        date.setMonth(date.getMonth() + 6);
        this.validityPeriod = date;
    }

    public boolean canRemove(Double amount) {
        return this.balance >= amount;
    }

    public CardResponse toResponse() {
        return new CardResponse(id,validityPeriod, balance, CVV, currency.name());
    }

    public Card() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Date getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(Date validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public ECurrency getCurrency() {
        return currency;
    }

    public void setCurrency(ECurrency currency) {
        this.currency = currency;
    }

    public Set<Transaction> getTaken() {
        return taken;
    }

    public void setTaken(Set<Transaction> taken) {
        this.taken = taken;
    }

    public Set<Transaction> getSend() {
        return send;
    }

    public void setSend(Set<Transaction> send) {
        this.send = send;
    }
}


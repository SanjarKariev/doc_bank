package ru.sanjar.bank.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.sanjar.bank.payload.response.ContributionResponse;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "contributions")
public class Contribution {
    @Id
    private final String id = UUID.randomUUID().toString();
    private Date startDate;
    private Date endDate;
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(length = 3)
    private ECurrency currency;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private boolean inProgress;

    public Contribution(Date endDate, Double amount, ECurrency currency, User user) {
        this.startDate = new Date();
        this.endDate = endDate;
        this.amount = amount;
        this.currency = currency;
        this.user = user;
        this.inProgress = true;
    }

    public ContributionResponse toResponse() {
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        return new ContributionResponse(this.id, this.startDate, this.endDate, Double.parseDouble(decimalFormat.format(this.amount).replace(",", ".")), this.currency.name(), this.inProgress);
    }

    public Contribution() {

    }

    public String getId() {
        return id;
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

    public ECurrency getCurrency() {
        return currency;
    }

    public void setCurrency(ECurrency currency) {
        this.currency = currency;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }
}

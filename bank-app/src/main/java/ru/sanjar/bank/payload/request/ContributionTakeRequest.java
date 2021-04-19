package ru.sanjar.bank.payload.request;

public class ContributionTakeRequest {
    private String contribution;
    private String card;

    public ContributionTakeRequest(String contribution, String card) {
        this.contribution = contribution;
        this.card = card;
    }

    public ContributionTakeRequest() {
    }

    public String getContribution() {
        return contribution;
    }

    public void setContribution(String contribution) {
        this.contribution = contribution;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }
}

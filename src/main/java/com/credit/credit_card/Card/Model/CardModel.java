package com.credit.credit_card.Card.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cards")
public class CardModel {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private int id;

    @Column(name = "customer_id", nullable = false)
    private int customerId;

    @Column(name = "card_number", nullable =  false)
    private String cardNumber;

    @Column(name = "cvv", nullable = false)
    private int cvv;

    @Column(name = "expiration_date", nullable = false)
    private String expirationDate;

    @Column(name = "status")
    private String status;

    @Column(name = "credit_limit")
    private double creditLimit;

    @Column(name = "current_balance")
    private double currentBalance;

    //Builder
    public CardModel(int customerId, String cardNumber, int cvv, String expirationDate, String status,
            double creditLimit, double currentBalance) {
        this.customerId = customerId;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
        this.status = status;
        this.creditLimit = creditLimit;
        this.currentBalance = currentBalance;
    }

    //Getters and Setters
    public int getCardId() {
        return id;
    }

    public void setCardId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getCredit_limit() {
        return creditLimit;
    }

    public void setCredit_limit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    //void builder
    public CardModel(){ }
}

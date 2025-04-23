package org.banking.model;

import java.sql.Date;

public class Card {

    int id;
    int accountId;
    String cardNumber;
    Date expiryDate;

    public Card() {
    }

    public Card(int id, int accountId, String cardNumber, Date expiryDate) {
        this.id = id;
        this.accountId = accountId;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}

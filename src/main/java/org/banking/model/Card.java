package org.banking.model;

import java.sql.Date;

public class Card {

    int id;
    int account_id;
    String card_number;
    Date expiry_date;

    public Card() {
    }

    public Card(int id, int account_id, String card_number, Date expiry_date) {
        this.id = id;
        this.account_id = account_id;
        this.card_number = card_number;
        this.expiry_date = expiry_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public Date getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(Date expiry_date) {
        this.expiry_date = expiry_date;
    }
}

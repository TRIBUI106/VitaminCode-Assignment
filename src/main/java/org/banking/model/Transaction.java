package org.banking.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Transaction {

    int id;
    int from_account;
    int to_account;
    BigDecimal amout;
    Timestamp created_at;

    public Transaction(int id, int from_account, int to_account, BigDecimal amout, Timestamp created_at) {
        this.id = id;
        this.from_account = from_account;
        this.to_account = to_account;
        this.amout = amout;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFrom_account() {
        return from_account;
    }

    public void setFrom_account(int from_account) {
        this.from_account = from_account;
    }

    public int getTo_account() {
        return to_account;
    }

    public void setTo_account(int to_account) {
        this.to_account = to_account;
    }

    public BigDecimal getAmout() {
        return amout;
    }

    public void setAmout(BigDecimal amout) {
        this.amout = amout;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
}

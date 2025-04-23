package org.banking.model;

import java.math.BigDecimal;

public class Account {
    int id;
    int customer_id;
    int branch_id;
    BigDecimal balance;

    public Account(int id, int customer_id, int branch_id, BigDecimal balance) {
        this.id = id;
        this.customer_id = customer_id;
        this.branch_id = branch_id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(int branch_id) {
        this.branch_id = branch_id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}

package org.banking.model;

import java.math.BigDecimal;

public class Account {
    int id;
    int customerId;
    int branchId;
    BigDecimal balance;

    public Account() {
    }

    public Account(int id, int customer_id, int branchId, BigDecimal balance) {
        this.id = id;
        this.customerId = customer_id;
        this.branchId = branchId;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}

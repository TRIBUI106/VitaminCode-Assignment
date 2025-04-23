package org.banking.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Loan {

    int id;
    int accountId;
    int loadPackageId;
    BigDecimal amount;
    BigDecimal interestRate;
    int termInMonths;
    Date startDate;
    String status;

    public Loan() {
    }

    public Loan(int id, int accountId, int loadPackageId, BigDecimal amount, BigDecimal interestRate, int termInMonths, Date startDate, String status) {
        this.id = id;
        this.accountId = accountId;
        this.loadPackageId = loadPackageId;
        this.amount = amount;
        this.interestRate = interestRate;
        this.termInMonths = termInMonths;
        this.startDate = startDate;
        this.status = status;
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

    public void setAccountId(int account_id) {
        this.accountId = account_id;
    }

    public int getLoadPackageId() {
        return loadPackageId;
    }

    public void setLoadPackageId(int loadPackageId) {
        this.loadPackageId = loadPackageId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public int getTermInMonths() {
        return termInMonths;
    }

    public void setTermInMonths(int termInMonths) {
        this.termInMonths = termInMonths;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

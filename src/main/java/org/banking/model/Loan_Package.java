package org.banking.model;

import java.math.BigDecimal;

public class Loan_Package {

    int id;
    String name;
    BigDecimal maxAmount;
    BigDecimal interestRate;
    BigDecimal minMonthlyIncome;
    int minTransactionCount;
    String description;

    public Loan_Package() {
    }

    public Loan_Package(int id, String name, BigDecimal maxAmount, BigDecimal interestRate, BigDecimal min_monthly_income, int minTransactionCount, String description) {
        this.id = id;
        this.name = name;
        this.maxAmount = maxAmount;
        this.interestRate = interestRate;
        this.minMonthlyIncome = min_monthly_income;
        this.minTransactionCount = minTransactionCount;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getMinMonthlyIncome() {
        return minMonthlyIncome;
    }

    public void setMinMonthlyIncome(BigDecimal minMonthlyIncome) {
        this.minMonthlyIncome = minMonthlyIncome;
    }

    public int getMinTransactionCount() {
        return minTransactionCount;
    }

    public void setMinTransactionCount(int minTransactionCount) {
        this.minTransactionCount = minTransactionCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

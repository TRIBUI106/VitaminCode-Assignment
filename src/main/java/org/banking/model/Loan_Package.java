package org.banking.model;

import java.math.BigDecimal;

public class Loan_Package {

    int id;
    String name;
    BigDecimal max_amout;
    BigDecimal interest_rate;
    BigDecimal minMonthlyIncome;
    int min_transacantion_count;
    String description;

    public Loan_Package() {
    }

    public Loan_Package(int id, String name, BigDecimal max_amout, BigDecimal interest_rate, BigDecimal min_monthly_income, int min_transacantion_count, String description) {
        this.id = id;
        this.name = name;
        this.max_amout = max_amout;
        this.interest_rate = interest_rate;
        this.minMonthlyIncome = min_monthly_income;
        this.min_transacantion_count = min_transacantion_count;
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

    public BigDecimal getMax_amout() {
        return max_amout;
    }

    public void setMax_amout(BigDecimal max_amout) {
        this.max_amout = max_amout;
    }

    public BigDecimal getInterest_rate() {
        return interest_rate;
    }

    public void setInterest_rate(BigDecimal interest_rate) {
        this.interest_rate = interest_rate;
    }

    public BigDecimal getMinMonthlyIncome() {
        return minMonthlyIncome;
    }

    public void setMinMonthlyIncome(BigDecimal minMonthlyIncome) {
        this.minMonthlyIncome = minMonthlyIncome;
    }

    public int getMin_transacantion_count() {
        return min_transacantion_count;
    }

    public void setMin_transacantion_count(int min_transacantion_count) {
        this.min_transacantion_count = min_transacantion_count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

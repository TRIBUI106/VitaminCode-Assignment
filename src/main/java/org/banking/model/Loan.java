package org.banking.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Loan {

    int id;
    int account_id;
    int load_package_id;
    BigDecimal amout;
    BigDecimal interest_rate;
    int term_in_months;
    Date start_date;
    String status;

    public Loan(Date start_date, int id, int account_id, int load_package_id, BigDecimal amout, BigDecimal interest_rate, int term_in_months, String status) {
        this.start_date = start_date;
        this.id = id;
        this.account_id = account_id;
        this.load_package_id = load_package_id;
        this.amout = amout;
        this.interest_rate = interest_rate;
        this.term_in_months = term_in_months;
        this.status = status;
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

    public int getLoad_package_id() {
        return load_package_id;
    }

    public void setLoad_package_id(int load_package_id) {
        this.load_package_id = load_package_id;
    }

    public BigDecimal getAmout() {
        return amout;
    }

    public void setAmout(BigDecimal amout) {
        this.amout = amout;
    }

    public BigDecimal getInterest_rate() {
        return interest_rate;
    }

    public void setInterest_rate(BigDecimal interest_rate) {
        this.interest_rate = interest_rate;
    }

    public int getTerm_in_months() {
        return term_in_months;
    }

    public void setTerm_in_months(int term_in_months) {
        this.term_in_months = term_in_months;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

package org.banking.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Loan {

    int id;
    int account_id;
    int load_package_id;
    BigDecimal amount;
    BigDecimal interest_rate;
    int term_in_months;
    Date start_date;
    String status;

    public Loan() {
    }

    public Loan(int id, int account_id, int load_package_id, BigDecimal amount, BigDecimal interest_rate, int term_in_months, Date start_date, String status) {
        this.id = id;
        this.account_id = account_id;
        this.load_package_id = load_package_id;
        this.amount = amount;
        this.interest_rate = interest_rate;
        this.term_in_months = term_in_months;
        this.start_date = start_date;
        this.status = status;
    }
}

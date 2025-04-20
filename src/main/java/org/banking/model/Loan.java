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

}

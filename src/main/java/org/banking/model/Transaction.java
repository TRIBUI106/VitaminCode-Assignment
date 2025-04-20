package org.banking.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Transaction {

    int id;
    int from_account;
    int to_account;
    BigDecimal amout;
    Timestamp created_at;

}

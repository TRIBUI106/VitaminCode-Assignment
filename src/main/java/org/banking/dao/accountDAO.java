package org.banking.dao;

import org.banking.model.Account;
import org.banking.ultils.DBHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class accountDAO implements DAO<Account, Integer> {

    private DBHelper db;

    public accountDAO() throws SQLException {
        db = new DBHelper();
    }

    @Override
    public Integer insert(Account entity) throws SQLException {
        String sql = "INSERT INTO accounts (customer_id, branch_id, balance) VALUES (?, ?, ?)";
        return db.exeuteUpdate(sql, entity.getCustomer_id(), entity.getBranch_id(), entity.getBalance());
    }

    @Override
    public Integer update(Account entity, Integer id) throws SQLException {
        String sql = "UPDATE accounts SET customer_id = ?, branch_id = ?, balance = ? WHERE id = ?";
        return db.exeuteUpdate(sql, entity.getCustomer_id(), entity.getBranch_id(), entity.getBalance(), id);
    }

    @Override
    public Integer deleteByID(Integer id) throws SQLException {
        String sql = "DELETE FROM accounts WHERE id = ?";
        return db.exeuteUpdate(sql, id);
    }

    @Override
    public List<Account> getAll() throws SQLException {
        String sql = "SELECT id, customer_id, branch_id, balance FROM accounts";
        ResultSet rs = db.excuteQuery(sql);
        List<Account> accounts = new ArrayList<>();
        while (rs.next()) {
            Account account = new Account();
            account.setId(rs.getInt("id"));
            account.setCustomer_id();d(rs.getInt("customer_id"));
            account.setBranch_id(rs.getInt("branch_id"));
            account.setBalance(rs.getBigDecimal("balance"));
            accounts.add(account);
        }
        return accounts;
    }

    @Override
    public Account getById(Integer id) throws SQLException {
        String sql = "SELECT id, customer_id, branch_id, balance FROM accounts WHERE id = ?";
        ResultSet rs = db.excuteQuery(sql, id);
        Account account = null;
        if (rs.next()) {
            account = new Account();
            account.setId(rs.getInt("id"));
            account.setCustomerId(rs.getInt("customer_id"));
            account.setBranchId(rs.getInt("branch_id"));
            account.setBalance(rs.getBigDecimal("balance"));
        }
        return account;
    }
}
package org.banking.dao;

import org.banking.model.Transaction;
import org.banking.ultils.DBHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class transactionDAO implements DAO<Transaction, Integer> {

    private DBHelper db;

    public transactionDAO() throws SQLException {
        db = new DBHelper();
    }

    @Override
    public Integer insert(Transaction entity) throws SQLException {
        String sql = "INSERT INTO transactions (from_account, to_account, amount, created_at) VALUES (?, ?, ?, ?)";
        return db.exeuteUpdate(sql, entity.getFromAccount(), entity.getToAccount(), entity.getAmount(), entity.getCreatedAt());
    }

    @Override
    public Integer update(Transaction entity, Integer id) throws SQLException {
        String sql = "UPDATE transactions SET from_account = ?, to_account = ?, amount = ?, created_at = ? WHERE id = ?";
        return db.exeuteUpdate(sql, entity.getFromAccount(), entity.getToAccount(), entity.getAmount(), entity.getCreatedAt(), id);
    }

    @Override
    public Integer deleteByID(Integer id) throws SQLException {
        String sql = "DELETE FROM transactions WHERE id = ?";
        return db.exeuteUpdate(sql, id);
    }

    @Override
    public List<Transaction> getAll() throws SQLException {
        String sql = "SELECT id, from_account, to_account, amount, created_at FROM transactions";
        ResultSet rs = db.excuteQuery(sql);
        List<Transaction> transactions = new ArrayList<>();
        while (rs.next()) {
            Transaction transaction = new Transaction();
            transaction.setId(rs.getInt("id"));
            transaction.setFrom_account(rs.getInt("from_account"));
            transaction.setTo_account(rs.getInt("to_account"));
            transaction.setAmout(rs.getBigDecimal("amount"));
            transaction.setCreated_at(rs.getTimestamp("created_at"));
            transactions.add(transaction);
        }
        return transactions;
    }

    @Override
    public Transaction getById(Integer id) throws SQLException {
        String sql = "SELECT id, from_account, to_account, amount, created_at FROM transactions WHERE id = ?";
        ResultSet rs = db.excuteQuery(sql, id);
        Transaction transaction = null;
        if (rs.next()) {
            transaction = new Transaction();
            transaction.setId(rs.getInt("id"));
            transaction.setFrom_account(rs.getInt("from_account"));
            transaction.setTo_account(rs.getInt("to_account"));
            transaction.setAmout(rs.getBigDecimal("amount"));
            transaction.setCreated_at(rs.getTimestamp("created_at"));
        }
        return transaction;
    }
}
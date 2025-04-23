package org.banking.dao;

import org.banking.model.Loan;
import org.banking.ultils.DBHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class loanDAO implements DAO<Loan, Integer> {

    private DBHelper db;

    public loanDAO() throws SQLException {
        db = new DBHelper();
    }

    @Override
    public Integer insert(Loan entity) throws SQLException {
        String sql = "INSERT INTO loans (account_id, load_package_id, amount, interest_rate, term_in_months, start_date, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return db.exeuteUpdate(sql, entity.getAccountId(), entity.getLoadPackageId(), entity.getAmount(), entity.getInterestRate(), entity.getTermInMonths(), entity.getStartDate(), entity.getStatus());
    }

    @Override
    public Integer update(Loan entity, Integer id) throws SQLException {
        String sql = "UPDATE loans SET account_id = ?, load_package_id = ?, amount = ?, interest_rate = ?, term_in_months = ?, start_date = ?, status = ? WHERE id = ?";
        return db.exeuteUpdate(sql, entity.getAccountId(), entity.getLoadPackageId(), entity.getAmount(), entity.getInterestRate(), entity.getTermInMonths(), entity.getStartDate(), entity.getStatus(), id);
    }

    @Override
    public Integer deleteByID(Integer id) throws SQLException {
        String sql = "DELETE FROM loans WHERE id = ?";
        return db.exeuteUpdate(sql, id);
    }

    @Override
    public List<Loan> getAll() throws SQLException {
        String sql = "SELECT id, account_id, load_package_id, amount, interest_rate, term_in_months, start_date, status FROM loans";
        ResultSet rs = db.excuteQuery(sql);
        List<Loan> loans = new ArrayList<>();
        while (rs.next()) {
            Loan loan = new Loan();
            loan.setId(rs.getInt("id"));
            loan.setAccountId(rs.getInt("account_id"));
            loan.setLoadPackageId(rs.getInt("load_package_id"));
            loan.setAmount(rs.getBigDecimal("amount"));
            loan.setInterestRate(rs.getBigDecimal("interest_rate"));
            loan.setTermInMonths(rs.getInt("term_in_months"));
            loan.setStartDate(rs.getDate("start_date"));
            loan.setStatus(rs.getString("status"));
            loans.add(loan);
        }
        return loans;
    }

    @Override
    public Loan getById(Integer id) throws SQLException {
        String sql = "SELECT id, account_id, load_package_id, amount, interest_rate, term_in_months, start_date, status FROM loans WHERE id = ?";
        ResultSet rs = db.excuteQuery(sql, id);
        Loan loan = null;
        if (rs.next()) {
            loan = new Loan();
            loan.setId(rs.getInt("id"));
            loan.setAccountId(rs.getInt("account_id"));
            loan.setLoadPackageId(rs.getInt("load_package_id"));
            loan.setAmount(rs.getBigDecimal("amount"));
            loan.setInterestRate(rs.getBigDecimal("interest_rate"));
            loan.setTermInMonths(rs.getInt("term_in_months"));
            loan.setStartDate(rs.getDate("start_date"));
            loan.setStatus(rs.getString("status"));
        }
        return loan;
    }
}
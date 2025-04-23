package org.banking.dao;

import org.banking.model.Loan_Package;
import org.banking.ultils.DBHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class loanPackageDAO implements DAO<Loan_Package, Integer> {

    private DBHelper db;

    public loanPackageDAO() throws SQLException {
        db = new DBHelper();
    }

    @Override
    public Integer insert(Loan_Package entity) throws SQLException {
        String sql = "INSERT INTO loan_packages (name, max_amount, interest_rate, min_monthly_income, min_transaction_count, description) VALUES (?, ?, ?, ?, ?, ?)";
        return db.exeuteUpdate(sql, entity.getName(), entity.getMax_amout(), entity.getInterest_rate(), entity.getMin_monthly_income(), entity.getMin_transacantion_count(), entity.getDescription());
    }

    @Override
    public Integer update(Loan_Package entity, Integer id) throws SQLException {
        String sql = "UPDATE loan_packages SET name = ?, max_amount = ?, interest_rate = ?, min_monthly_income = ?, min_transaction_count = ?, description = ? WHERE id = ?";
        return db.exeuteUpdate(sql, entity.getName(), entity.getMax_amout(), entity.getInterest_rate(), entity.getMin_monthly_income(), entity.getMin_transacantion_count(), entity.getDescription(), id);
    }

    @Override
    public Integer deleteByID(Integer id) throws SQLException {
        String sql = "DELETE FROM loan_packages WHERE id = ?";
        return db.exeuteUpdate(sql, id);
    }

    @Override
    public List<Loan_Package> getAll() throws SQLException {
        String sql = "SELECT id, name, max_amount, interest_rate, min_monthly_income, min_transaction_count, description FROM loan_packages";
        ResultSet rs = db.excuteQuery(sql);
        List<Loan_Package> loanPackages = new ArrayList<>();
        while (rs.next()) {
            Loan_Package loanPackage = new Loan_Package();
            loanPackage.setId(rs.getInt("id"));
            loanPackage.setName(rs.getString("name"));
            loanPackage.setMax_amout(rs.getBigDecimal("max_amount"));
            loanPackage.setInterest_rate(rs.getBigDecimal("interest_rate"));
            loanPackage.setMinMonthlyIncome(rs.getBigDecimal("min_monthly_income"));
            loanPackage.setMin_transaction_count(rs.getInt("min_transaction_count"));
            loanPackage.setDescription(rs.getString("description"));
            loanPackages.add(loanPackage);
        }
        return loanPackages;
    }

    @Override
    public Loan_Package getById(Integer id) throws SQLException {
        String sql = "SELECT id, name, max_amount, interest_rate, min_monthly_income, min_transaction_count, description FROM loan_packages WHERE id = ?";
        ResultSet rs = db.excuteQuery(sql, id);
        Loan_Package loanPackage = null;
        if (rs.next()) {
            loanPackage = new Loan_Package();
            loanPackage.setId(rs.getInt("id"));
            loanPackage.setName(rs.getString("name"));
            loanPackage.setMax_amout(rs.getBigDecimal("max_amount"));
            loanPackage.setInterest_rate(rs.getBigDecimal("interest_rate"));
            loanPackage.setMinMonthlyIncome(rs.getBigDecimal("min_monthly_income"));
            loanPackage.setMin_transaction_count(rs.getInt("min_transaction_count"));
            loanPackage.setDescription(rs.getString("description"));
        }
        return loanPackage;
    }
}
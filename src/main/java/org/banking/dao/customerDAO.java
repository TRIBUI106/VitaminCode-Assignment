package org.banking.dao;

import org.banking.model.Customer;
import org.banking.ultils.DBHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class customerDAO implements DAO<Customer,Integer>{

    DBHelper db ;

    public customerDAO() throws SQLException {
        db = new DBHelper();
    }

    @Override
    public Integer insert(Customer entity) throws SQLException {
        String sql = " INSERT INTO vitamincode00.customers (name, phone, email, address, monthly_income)\n" +
                "VALUES (?, ?,?, ?,?)";
        Integer rs = db.exeuteUpdate(sql,entity.getName(),entity.getPhone(),entity.getEmail(),entity.getAddress(),entity.getMonthlyIncome());
        return rs;
    }

    @Override
    public Integer update(Customer entity, Integer id) {
        return null;
    }

    @Override
    public Integer deleteByID(Integer id) throws SQLException {
        String sql = "delete from customers where id   = ? ";
        Integer rs = db.exeuteUpdate(sql,id);
        return rs;
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        String sql = "select id, name, phone, email, address, monthly_income from customers ";
        ResultSet rs = db.excuteQuery(sql);
        List<Customer> listCustomer = new ArrayList<>();
        while (rs.next())
        {
            Customer c = new Customer();
            c.setId(rs.getInt("id"));
            c.setName(rs.getString("name"));
            c.setPhone(rs.getString("phone"));
            c.setAddress(rs.getString("address"));
            c.setMonthlyIncome(rs.getDouble("monthly_income"));
            listCustomer.add(c);
        }
        return listCustomer;
    }

    @Override
    public Customer getById(Integer id) throws SQLException {
        String sql = "select id, name, phone, email, address, monthly_income from customers where id = ?  ";
        ResultSet rs = db.excuteQuery(sql,id);
        Customer iCustomer = null;
        while (rs.next())
        {
            iCustomer = new Customer();
            iCustomer.setId(rs.getInt("id"));
            iCustomer.setName(rs.getString("name"));
            iCustomer.setPhone(rs.getString("phone"));
            iCustomer.setAddress(rs.getString("address"));
            iCustomer.setMonthlyIncome(rs.getDouble("monthly_income"));
        }
        return iCustomer;
    }
}

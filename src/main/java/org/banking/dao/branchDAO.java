package org.banking.dao;

import org.banking.model.Branch;
import org.banking.ultils.DBHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class branchDAO implements DAO<Branch, Integer> {

    private DBHelper db;

    public branchDAO() throws SQLException {
        db = new DBHelper();
    }

    @Override
    public Integer insert(Branch entity) throws SQLException {
        String sql = "INSERT INTO branches (name, location) VALUES (?, ?)";
        return db.exeuteUpdate(sql, entity.getName(), entity.getLocation());
    }

    @Override
    public Integer update(Branch entity, Integer id) throws SQLException {
        String sql = "UPDATE branches SET name = ?, location = ? WHERE id = ?";
        return db.exeuteUpdate(sql, entity.getName(), entity.getLocation(), id);
    }

    @Override
    public Integer deleteByID(Integer id) throws SQLException {
        String sql = "DELETE FROM branches WHERE id = ?";
        return db.exeuteUpdate(sql, id);
    }

    @Override
    public List<Branch> getAll() throws SQLException {
        String sql = "SELECT id, name, location FROM branches";
        ResultSet rs = db.excuteQuery(sql);
        List<Branch> branches = new ArrayList<>();
        while (rs.next()) {
            Branch branch = new Branch();
            branch.setId(rs.getInt("id"));
            branch.setName(rs.getString("name"));
            branch.setLocation(rs.getString("location"));
            branches.add(branch);
        }
        return branches;
    }

    @Override
    public Branch getById(Integer id) throws SQLException {
        String sql = "SELECT id, name, location FROM branches WHERE id = ?";
        ResultSet rs = db.excuteQuery(sql, id);
        Branch branch = null;
        if (rs.next()) {
            branch = new Branch();
            branch.setId(rs.getInt("id"));
            branch.setName(rs.getString("name"));
            branch.setLocation(rs.getString("location"));
        }
        return branch;
    }
}
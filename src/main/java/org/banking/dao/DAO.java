package org.banking.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO <T,K>{

    Integer insert(T entity) throws SQLException;

    Integer update(T entity , K id)throws SQLException;

    Integer deleteByID(K id) throws SQLException;

    List<T> getAll() throws SQLException;

    T getById( K id) throws SQLException;


}

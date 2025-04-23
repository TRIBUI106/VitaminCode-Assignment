package org.banking.ultils;

import java.sql.*;

public class DBHelper {

    private static final String URL = "jdbc:mysql://localhost:3306/vitamincode00";
    private static final String USERNAME = "root";
    private static final String PSW = "1234";

    private Connection conn ;

    static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public DBHelper() throws SQLException {
        this.conn = DriverManager.getConnection(URL,USERNAME,PSW);
    }

    public Connection getConnection()
    {
        return conn;
    }

    public void close() throws SQLException {
        conn.close();
    }

    private void setParams(PreparedStatement stmt, Object... parms )throws SQLException{
        for (int i = 0 ; i< parms.length ; i ++)
        {
            stmt.setObject(i+1 , parms[i]);
        }
    }
    public PreparedStatement createpre(String sql , Object... params) throws SQLException{
        PreparedStatement stmt = conn.prepareStatement(sql);
        setParams(stmt,params);
        return stmt;
    }


    public ResultSet excuteQuery(String sql, Object... params) throws SQLException{
        PreparedStatement pre = createpre(sql,params);
        return pre.executeQuery();
    }

    public int exeuteUpdate(String sql, Object... params) throws SQLException{
        PreparedStatement pre = createpre(sql,params);
        return pre.executeUpdate();
    }

    public ResultSet excuteQuery(String sql ) throws SQLException {
        Statement stmt = conn.createStatement();
        return  stmt.executeQuery(sql);
    }



}

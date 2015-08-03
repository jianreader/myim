package com.jian.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by patrick on 8/1/2015.
 */
public class DBConnection {
    private Connection _connection;
    private boolean _isAvailable = true;

    public DBConnection(String url, String user, String password) throws SQLException {
        _connection = DriverManager.getConnection(url, user, password);
    }

    public Connection getConnection()
    {
        _isAvailable = false;
        return _connection;
    }

    public boolean isAvailable()
    {
        return _isAvailable;
    }

    public void setAvailable(boolean b)
    {
        _isAvailable = b;
    }

    public void releaseConnection()
    {
        _isAvailable = true;
    }

    public void closeConnection() throws SQLException {
        _connection.close();
    }

}

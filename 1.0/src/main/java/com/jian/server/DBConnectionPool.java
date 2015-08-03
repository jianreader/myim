package com.jian.server;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by patrick on 7/31/2015.
 */
public class DBConnectionPool
{
    private int MAX_SIZE = 10;
    private String _url;
    private String _user;
    private String _password;
    private List<DBConnection> _dbConnections = new ArrayList<DBConnection>();

    public DBConnectionPool(String url, String user, String password, int size)
    {
        _url = url;
        _user = user;
        _password = password;
        if(size > 0)
        {
            MAX_SIZE = size;
        }
    }


    public DBConnection getDBConnection() throws SQLException {
        for(DBConnection connection : _dbConnections)
        {
            if(connection.isAvailable())
            {
                connection.setAvailable(false);
                return connection;
            }
        }
        if(_dbConnections.size() < MAX_SIZE)
        {
            DBConnection newConnection = new DBConnection(_url, _user, _password);
            newConnection.setAvailable(false);
            return newConnection;
        }
        return null;
    }

    public int getMaxSize()
    {
        return MAX_SIZE;
    }
}

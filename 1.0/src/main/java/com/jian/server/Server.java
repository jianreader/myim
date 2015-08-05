package com.jian.server;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by patrick on 8/1/2015.
 */
public class Server implements Runnable
{
    public static final int SERVER_PORT = 2013;
    private DBConnectionPool _dbConnectionPool;

    public Server() throws IOException
    {
       _dbConnectionPool = DBConnectionPoolFactory.getFactory().getDBConnectionPool();
    }

    public DBService createDBService() throws SQLException
    {
        return new DBServiceImpl();
    }

    public Account getAccount(String uid) throws SQLException, IOException {
        DBService dbService = createDBService();
        return dbService.getAccount(uid);
    }

    @Override
    public void run() {
        while(true)
        {

        }
    }
}

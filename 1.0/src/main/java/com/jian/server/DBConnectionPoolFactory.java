package com.jian.server;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by patrick on 8/1/2015.
 */
public class DBConnectionPoolFactory
{
    private static final String SERVER_DB_PROPERTY_FILE = "resources/server_db.properties";
    private static DBConnectionPoolFactory _factory = new DBConnectionPoolFactory();
    private DBConnectionPool _instance = null;

    public static DBConnectionPoolFactory getFactory()
    {
        return _factory;
    }

    public DBConnectionPool getDBConnectionPool() throws IOException {
        if(_instance == null)
        {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream(SERVER_DB_PROPERTY_FILE);
            Properties props = new Properties();
            if(is != null)
                 props.load(is);
            else
                throw new IOException("The server_db.properties file NOT found!");
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            String poolSize = props.getProperty("poolSize");
            is.close();
            int size = Integer.parseInt(poolSize);
            _instance = new DBConnectionPool(url, user, password, size);
        }
        return _instance;
    }
}

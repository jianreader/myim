package com.jian.server;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static org.junit.Assert.*;

/**
 * Created by Patrick on 8/1/2015.
 */
public class DBServicePoolFactoryTest {



    @Test
    public void testGetDBservicePool() throws Exception {
        DBConnectionPool pool = DBConnectionPoolFactory.getFactory().getDBConnectionPool();
        System.out.println("max size: " + pool.getMaxSize());


    }
}
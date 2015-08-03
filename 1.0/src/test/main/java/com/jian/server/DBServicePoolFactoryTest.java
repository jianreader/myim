package com.jian.server;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Patrick on 8/1/2015.
 */
public class DBServicePoolFactoryTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testGetDBservicePool() throws Exception {
        DBConnectionPool pool = DBConnectionPoolFactory.getFactory().getDBConnectionPool();
        System.out.println("max size: " + pool.getMaxSize());

    }
}
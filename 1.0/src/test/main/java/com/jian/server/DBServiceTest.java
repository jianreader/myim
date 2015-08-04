package com.jian.server;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static org.junit.Assert.fail;

/**
 * Created by patrick on 8/3/2015.
 */
public class DBServiceTest {

    @Before
    public void setUp() throws Exception {
      /*  Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
        System.out.println("Load the embedded driver");
        Connection conn = null;
        Properties props = new Properties();
        props.put("user", "patrick");  props.put("password", "welcome");
        //create and connect the database named helloDB
        conn= DriverManager.getConnection("jdbc:derby:helloDB;create=true", props);
        System.out.println("create and connect to helloDB");
        conn.setAutoCommit(false);

        // create a table and insert two records
        Statement s = conn.createStatement();
        s.execute("create table hellotable(uid varchar(20), name varchar(20), friends varchar(200), address varchar(20), port varchar(10), status boolean)");
        System.out.println("Created table hellotable");
        s.execute("insert into hellotable values('001', 'Mike', 'Grace', 'localhost', '1522', 'TRUE')");
        s.execute("insert into hellotable values ('002', 'Grace', 'Mike', 'localhost', '1522', 'TRUE')");
        conn.commit();
        conn.close();
        System.out.println("Committed transaction (creation) and closed connection");*/
    }

    @After
    public void tearDown() throws Exception
    {
       /* Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
        System.out.println("Load the embedded driver");
        Connection conn = null;
        Properties props = new Properties();
        props.put("user", "patrick");  props.put("password", "welcome");
        //create and connect the database named helloDB
        conn= DriverManager.getConnection("jdbc:derby:helloDB;create=true", props);
        Statement s = conn.createStatement();
        s.execute("drop table hellotable");
        System.out.println("Dropped table hellotable");
        s.close();
        System.out.println("Closed result set and statement");
        conn.commit();
        conn.close();
        System.out.println("Committed transaction (drop) and closed connection");

        try { // perform a clean shutdown
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException se) {
            System.out.println("Database shut down normally");
        }*/
    }

    @Test
    public void testDBService()
    {
        try {
            DBService dbService = new DBServiceImpl();
            Account a001 = dbService.getAccount("001");
            System.out.println("----- " + a001.getName());
        }
        catch (Exception e)
        {
            fail("Test fails");
        }
    }
}

package com.jian.server;

import java.sql.*;
import java.util.Properties;

/**
 * Created by patrick on 8/2/2015.
 */
public class CreateDB {
    public static void main(String[] args) {
        try { // load the driver
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
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
            s.execute("drop table hellotable");
            System.out.println("Dropped table hellotable");
            s.execute("create table hellotable(uid varchar(20), name varchar(20), friends varchar(200), address varchar(20), port varchar(10), status boolean)");
            System.out.println("Created table hellotable");
            s.execute("insert into hellotable values('001', 'Mike', '002', 'localhost', '1522', 'TRUE')");
            s.execute("insert into hellotable values ('002', 'Grace', '001', 'localhost', '1522', 'TRUE')");
            // list the two records
            ResultSet rs = s.executeQuery(
                    "SELECT * FROM hellotable where uid='001'");
            System.out.println("name\t\tscore");
            while(rs.next()) {
                StringBuilder builder = new StringBuilder(rs.getString(1));
                builder.append("\t");
                builder.append(rs.getString(2));
                System.out.println(builder.toString());
            }
            // delete the table
            /*s.execute("drop table hellotable");
            System.out.println("Dropped table hellotable");
               */
            rs.close();
            s.close();
            System.out.println("Closed result set and statement");
            conn.commit();
            conn.close();
            System.out.println("Committed transaction and closed connection");

            try { // perform a clean shutdown
                DriverManager.getConnection("jdbc:derby:;shutdown=true");
            } catch (SQLException se) {
                System.out.println("Database shut down normally");
            }
        } catch (Throwable e) {
            // handle the exception
        }
        System.out.println("SimpleApp finished");
    }
}

package com.jian.server;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

/**
 * Created by patrick on 8/1/2015.
 */
public class DBServiceImpl implements DBService {


    public Account getAccount(String uid) throws SQLException, IOException {
        String sqlString = "select * from " + AccountDB.ACCOUNTTABLE + " where " + AccountDB.UID + "=\'" + uid + "\'";
        DBConnection dbConnection = DBConnectionPoolFactory.getFactory().getDBConnectionPool().getDBConnection();
        Connection connection = dbConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery(sqlString);
        while(res.next()) {
            String name = res.getString(AccountDB.NAME);
            String friends = res.getString(AccountDB.FRIENDS);
            HashMap friendsMap = getFriendsMap(friends, connection);
            Account account = new Account(uid, name, friendsMap);
            return account;
        }
        dbConnection.releaseConnection();
        return null;
    }

    public Account login(String uid, String password) throws SQLException, IOException, LoginException{
        if(password == null)
        {
            throw new LoginException("No Password!");
        }
        String sqlString = "select * from " + AccountDB.ACCOUNTTABLE + " where " + AccountDB.UID + "=\'" + uid + "\'";
        DBConnection dbConnection = DBConnectionPoolFactory.getFactory().getDBConnectionPool().getDBConnection();
        Connection connection = dbConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery(sqlString);
        while(res.next()) {
            String pw = res.getString(AccountDB.PW);
            if(!password.equals(pw))
            {
                throw new LoginException("Wrong Password!");
            }
            String name = res.getString(AccountDB.NAME);
            String friends = res.getString(AccountDB.FRIENDS);
            HashMap friendsMap = getFriendsMap(friends, connection);
            Account account = new Account(uid, name, friendsMap);
            dbConnection.releaseConnection();
            return account;
        }
        dbConnection.releaseConnection();
        throw new LoginException("User does NOT exist!");
    }

    public void updateDB(String uid, String attribute, String value) throws SQLException, IOException {
        String sqlString = "update " + AccountDB.ACCOUNTTABLE + " set " + attribute + " = \'" + value + "\'" + " where " + AccountDB.UID + " = \'" + uid + "\'";
        DBConnection dbConnection = DBConnectionPoolFactory.getFactory().getDBConnectionPool().getDBConnection();
        Connection connection = dbConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery(sqlString);
        dbConnection.releaseConnection();
    }

    private HashMap<String, Friend> getFriendsMap(String friendsIDs, Connection connection) throws SQLException {
        HashMap<String, Friend> friendsMap = new HashMap<String, Friend>();
        if(friendsIDs == null || friendsIDs == "")
            return friendsMap;

        String[] fArray = friendsIDs.split(";");
        for(String friendID : fArray)
        {
            String sqlQuery = "select " + AccountDB.NAME + ", " + AccountDB.ADDRESS + ", " + AccountDB.PORT + ", " + AccountDB.STATUS + ", from " + AccountDB.ACCOUNTTABLE + " where " + AccountDB.UID + "=\'" + friendID + "\'";
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sqlQuery);
            if(res != null && res.next())
            {
                String name = res.getString(AccountDB.NAME);
                String address = res.getString(AccountDB.ADDRESS);
                String port = res.getString(AccountDB.PORT);
                boolean status = res.getBoolean(AccountDB.STATUS);
                Friend friend = new Friend(friendID, name, address, port, status);
                friendsMap.put(friendID, friend);
            }
        }
        return friendsMap;
    }



}

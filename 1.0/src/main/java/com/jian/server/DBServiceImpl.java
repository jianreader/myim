package com.jian.server;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

/**
 * Created by patrick on 8/1/2015.
 */
public class DBServiceImpl implements DBService {

    private enum COLUMN
    {
        UID, NAME, FRIENDS, ADDRESS, PORT, STATUS
    }


    public Account getAccount(String uid) throws SQLException, IOException {
        String sqlString = "select * from AccountsTable where uid=" + uid;
        DBConnection dbConnection = DBConnectionPoolFactory.getFactory().getDBConnectionPool().getDBConnection();
        Connection connection = dbConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery(sqlString);
        String name = res.getString(COLUMN.NAME.ordinal());
        String friends = res.getString(COLUMN.FRIENDS.ordinal());
        HashMap friendsMap = getFriendsMap(friends, connection);
        dbConnection.releaseConnection();
        Account account = new Account(uid, name, friendsMap);
        return account;
    }

    private HashMap<String, Friend> getFriendsMap(String friendsIDs, Connection connection) throws SQLException {
        HashMap<String, Friend> friendsMap = new HashMap<String, Friend>();
        if(friendsIDs == null || friendsIDs == "")
            return friendsMap;

        String[] fArray = friendsIDs.split(";");
        for(String friendID : fArray)
        {
            String sqlQuery = "select name, address, port, status from AccountsTable where uid=" + friendID;
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sqlQuery);
            if(res != null)
            {
                String name = res.getString(COLUMN.NAME.ordinal());
                String address = res.getString(COLUMN.ADDRESS.ordinal());
                String port = res.getString(COLUMN.PORT.ordinal());
                boolean status = res.getBoolean(COLUMN.STATUS.ordinal());
                Friend friend = new Friend(friendID, name, address, port, status);
                friendsMap.put(friendID, friend);
            }
        }
        return friendsMap;
    }


}

package com.jian.server;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by patrick on 8/4/2015.
 */
public class LoginOperation {
    private String _user ;
    private String _password;

    public LoginOperation(String line) throws LoginException {
        String[] array = line.split(":");
        if(array.length != 2)
            throw new LoginException("Wrong Login Message");
        _user = array[0];
        _password = array[1];

    }

    public LoginOperation(String user, String password)
    {
        _user = user;
        _password = password;
    }

    public Account Login() throws LoginException, SQLException, IOException {
        DBService dbService = new DBServiceImpl();
        Account account = dbService.login(_user, _password);
        dbService.updateDB(_user, AccountDB.STATUS, "true");
        return account;
    }
}

package com.jian.protocol;

import com.jian.server.Account;
import com.jian.server.LoginException;
import com.jian.server.LoginOperation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by patrick on 8/3/2015.
 */
public class ImProtocol {

    public static final String LOGIN = "login";
    public static final String SEND_MESSAGE = "send_message";
    public static final String ADD_FRIENDS = "add_friend";
    public static final String SEARCH_PERSON = "search_person";
    public static final String LOGIN_SUCCESS = "login_success";
    public static final String LOGIN_FAIL = "login_fail";
    public static final String SEARCH_SUCCESS = "search_success";
    public static final String SEARCH_FAIL = "search_fail";
    public static final String RECEIVE_MSG = "receive_msg";


}

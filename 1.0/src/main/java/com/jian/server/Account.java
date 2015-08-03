package com.jian.server;

import java.util.HashMap;

/**
 * Created by patrick on 8/1/2015.
 */
public class Account
{
    private String _name;
    private HashMap<String, Friend> _friendMap = new HashMap<String, Friend>();
    private String _uid;
    private String _address;
    private String _port;

    public Account(String uid, String name, HashMap friends)
    {
        _name = name;
        _uid = uid;
        _friendMap = friends;
    }


}

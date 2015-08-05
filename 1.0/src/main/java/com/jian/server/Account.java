package com.jian.server;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by patrick on 8/1/2015.
 */
public class Account implements Serializable
{
    private static final long serialVersionUID = 5950169519310163575L;
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

    public String getName()
    {
        return _name;
    }

    public String getUid()
    {
        return _uid;
    }

    public HashMap<String, Friend> getFriendMap()
    {
        return _friendMap;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        if (_uid.equals(account.getUid())) return false;
        return true;
    }

    public int hashCode() {
        return Integer.parseInt(_uid);
    }

}

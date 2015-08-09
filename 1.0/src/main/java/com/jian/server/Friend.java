package com.jian.server;

import java.io.Serializable;

/**
 * Created by Patrick on 8/1/2015.
 */
public class Friend implements Serializable{
    private static final long serialVersionUID = 5950163219310163575L;
    private String _name;
    private String _address;
    private String _port;
    private boolean _isOnline;
    private String _uid;

    public Friend(String uid, String name, String address, String port, boolean status)
    {
        _uid = uid;
        _name = name;
        _address = address;
        _port = port;
        _isOnline = status;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getAddress() {
        return _address;
    }

    public void setAddress(String url) {
        this._address = url;
    }

    public boolean isOnline() {
        return _isOnline;
    }

    public void setOnline(boolean isOnline) {
        this._isOnline = isOnline;
    }

    public String getUid() {
        return _uid;
    }

    public void setUid(String uid) {
        this._uid = uid;
    }

    public String getPort()
    {
        return _port;
    }

    public void setPort(String port)
    {
        _port = port;
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

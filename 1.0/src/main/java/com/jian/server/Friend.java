package com.jian.server;

/**
 * Created by Patrick on 8/1/2015.
 */
public class Friend {
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

    public String getUrl() {
        return _address;
    }

    public void setUrl(String url) {
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
}

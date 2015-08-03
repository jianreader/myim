package com.jian.server;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by patrick on 7/31/2015.
 */
public interface DBService
{
   public Account getAccount(String uid) throws SQLException, IOException;
}

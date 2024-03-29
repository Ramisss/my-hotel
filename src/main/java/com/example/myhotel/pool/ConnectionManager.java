package com.example.myhotel.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {

    private static final String URL_KEY = "db.url";

    private static final String USERNAME_KEY = "db.username";

    private static final String PASSWORD_KEY = "db.password";

    private ConnectionManager() {

    }

     static Connection open() throws SQLException {
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USERNAME_KEY),
                    PropertiesUtil.get(PASSWORD_KEY)
            );
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

}

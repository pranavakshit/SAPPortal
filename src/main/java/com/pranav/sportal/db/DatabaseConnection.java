// File: SAPPortal/src/main/java/com/pranav/sportal/db/DatabaseConnection.java
package com.pranav.sportal.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/records"; // Modify this to your actual database
    private static final String USER = "root"; // Your database username
    private static final String PASSWORD = "root"; // Your database password

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            throw new SQLException("Database connection error", e);
        }
    }
}

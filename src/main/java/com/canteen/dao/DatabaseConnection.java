package com.canteen.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:canteen.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
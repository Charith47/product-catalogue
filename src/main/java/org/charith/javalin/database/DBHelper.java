package org.charith.javalin.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.ResultSet;

public class DBHelper {
    private static final String DB_URL = "jdbc:sqlite:C:/sqlite/shop.db";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            System.out.println("ERROR:(SQL) " + e.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        return connection;
    }

    public static void printTables() throws SQLException {
        Connection connection = getConnection();
        DatabaseMetaData metadata = connection.getMetaData();

        String[] tableTypes = {"TABLE"};
        ResultSet rs = metadata.getTables(null, null, "%", tableTypes);

        System.out.println("Tables in the database:");
        while (rs.next()) {
            System.out.println(rs.getString("TABLE_NAME"));
        }
    }
}

package backend.repository;

import backend.config.DBConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getDBConnection() {
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(DBConfig.URL, DBConfig.USERNAME, DBConfig.PASSWORD);
            System.out.println("Connected to the database");

            createUserTable(conn);
            createPlanTable(conn);
        } catch (SQLException e) {
            System.err.println("SQL EXCEPTION " + e.getMessage());
        }
        return conn;
    }

    private static void createUserTable(Connection conn) throws SQLException {
        String query = """
                CREATE TABLE IF NOT EXISTS User (
                    username VARCHAR(255) PRIMARY KEY,
                    password VARCHAR(255),
                    isDaughter VARCHAR(255)
                );
                """;

        conn.createStatement().executeUpdate(query);
        System.out.println("Created the User table");
    }

    private static void createPlanTable(Connection conn) throws SQLException {
        String query = """
                CREATE TABLE IF NOT EXISTS Plan (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    name VARCHAR(255),
                    day VARCHAR(255),
                    start VARCHAR(255),
                    end VARCHAR(255),
                    owner VARCHAR(255)
                );
                """;
        conn.createStatement().executeUpdate(query);
        System.out.println("Created the User table");
    }

}

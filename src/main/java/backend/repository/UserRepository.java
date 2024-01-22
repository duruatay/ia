package backend.repository;

import backend.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private Connection dbConn;

    public UserRepository() {
        dbConn = DBConnection.getDBConnection();
    }

    public void createUser(User user) {
        String query = """
                INSERT INTO User VALUES ("DEFAULT_USERNAME", "DEFAULT_PASSWORD", "DEFAULT_IS_DAUGHTER");
                """;
        query = query.replace("DEFAULT_USERNAME", user.getUsername());
        query = query.replace("DEFAULT_PASSWORD", user.getPassword());
        query = query.replace("DEFAULT_IS_DAUGHTER", String.valueOf(user.isDaughter()));

        try{
            dbConn.createStatement().executeUpdate(query);
            System.out.println("Created new User " + user.getUsername() + " successfully");
        } catch(SQLException e) {
            System.err.println("SQL EXCEPTION " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = """
                SELECT * FROM User;
                """;

        try{
            ResultSet resultSet = dbConn.prepareStatement(query).executeQuery();
            while(resultSet.next()) {
                User user = new User(resultSet.getString("username"),
                        resultSet.getString("password"),
                        Boolean.parseBoolean(resultSet.getString("isDaughter")));
                users.add(user);
            }
        } catch(SQLException e) {
            System.err.println("SQL EXCEPTION " + e.getMessage());
        }

        return users;
    }


}

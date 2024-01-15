package backend.service;

import backend.config.SessionInfo;
import backend.model.User;

import java.util.ArrayList;
import java.util.List;


public class LoginService {
    private List<User> users;


    public LoginService() {
        users = new ArrayList<>();
        users.add(new User("daughter", "admin", true));
        users.add(new User("father", "admin", false));
    }


    public User login(String username, String password) {
        // TODO: Add validation logic on the input data. If valid continue with the logic below, else return null.
        for(User user: users) {
            if(username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                SessionInfo.DAUGHTER_SESSION = user.isDaughter();
                return user;
            }
        }
        return null;
    }
}

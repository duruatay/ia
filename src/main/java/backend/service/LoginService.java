package backend.service;

import backend.config.SessionInfo;
import backend.model.User;
import backend.repository.UserRepository;

import java.util.List;


public class LoginService {
    private UserRepository userRepository;
    private List<User> users;


    public LoginService() {
        userRepository = new UserRepository();

        userRepository.createUser(new User("daughter", "admin", true));
        userRepository.createUser(new User("father", "admin", false));

        users = userRepository.getAllUsers();
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

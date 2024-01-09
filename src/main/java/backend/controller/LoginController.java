package backend.controller;

import backend.model.User;
import backend.service.LoginService;


public class LoginController {
    private final LoginService loginService;


    public LoginController() {
        this.loginService = new LoginService();
    }


    public User login(String username, String password) {
        return loginService.login(username, password);
    }
}

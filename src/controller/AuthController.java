package controller;

import entity.User;
import service.AuthService;

public class AuthController {
    private final AuthService authService = new AuthService();

    public User handleLogin(String username, String password) throws Exception {
        try {
            return authService.authenticate(username, password);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
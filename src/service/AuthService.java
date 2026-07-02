package service;

import entity.User;
import repository.UserRepository;
import java.sql.SQLException;

public class AuthService {
    private final UserRepository userRepository = new UserRepository();

    public User authenticate(String username, String password) throws SQLException {
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Username dan Password tidak boleh kosong!");
        }
        return userRepository.login(username, password);
    }
}
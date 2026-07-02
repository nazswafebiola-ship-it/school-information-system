package repository;

import database.DatabaseConnection;
import entity.User;
import entity.UserRole;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    public User login(String username, String password) throws SQLException {
        String query = "SELECT id, username, role FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        UserRole.valueOf(rs.getString("role"))
                    );
                }
            }
        }
        return null;
    }
}
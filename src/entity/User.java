package entity;

public class User {
    private int id;
    private String username;
    private UserRole role;

    public User(int id, String username, UserRole role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public UserRole getRole() { return role; }
}
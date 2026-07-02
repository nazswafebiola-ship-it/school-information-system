package view;

import controller.AuthController;
import entity.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {
    private JTextField txtUsername = new JTextField(20);
    private JPasswordField txtPassword = new JPasswordField(20);
    private JButton btnLogin = new JButton("Login");
    private AuthController authController = new AuthController();

    public LoginFrame() {
        setTitle("Login Sistem Informasi Sekolah");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("  Username:")); add(txtUsername);
        add(new JLabel("  Password:")); add(txtPassword);
        add(new JLabel("")); add(btnLogin);

        // Event saat tombol login diklik
        btnLogin.addActionListener((ActionEvent e) -> {
            actionLogin();
        });
    }

    private void actionLogin() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        try {
            // Memanggil controller, tidak akses DB langsung di JFrame (Aturan Dosen!)
            User user = authController.handleLogin(username, password);
            
            if (user != null) {
                JOptionPane.showMessageDialog(this, "Login Berhasil sebagai " + user.getRole());
                this.dispose(); // Tutup halaman login
                new MainDashboard(user).setVisible(true); // Buka Dashboard Utama
            } else {
                JOptionPane.showMessageDialog(this, "Username atau Password salah!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            // Exception handling yang baik agar aplikasi tidak crash (Aturan Dosen!)
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error Input / Koneksi", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}
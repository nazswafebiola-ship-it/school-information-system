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
        setSize(400, 220); 
        setResizable(false); // Mengunci ukuran agar tidak melar otomatis
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Membuat jendela muncul di tengah layar

        // Menggunakan FlowLayout agar komponen tidak melar dipaksa penuh
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15)); 

        // Membuat panel bungkus kecil di dalam agar form tersusun rapi
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 10));
        panel.add(new JLabel("Username:")); panel.add(txtUsername);
        panel.add(new JLabel("Password:")); panel.add(txtPassword);
        panel.add(new JLabel(""));         panel.add(btnLogin);
        
        add(panel);

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
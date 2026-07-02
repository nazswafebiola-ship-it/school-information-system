package view;

import entity.User;
import javax.swing.*;
import java.awt.*;

public class MainDashboard extends JFrame {
    private User loggedInUser;

    // Komponen Menu/Tombol
    private JButton btnCrudGuru = new JButton("CRUD Guru (Admin Only)");
    private JButton btnCrudSiswa = new JButton("CRUD Siswa (Admin Only)");
    private JButton btnInputNilai = new JButton("Input Nilai (Guru Only)");
    private JButton btnLihatJadwal = new JButton("Lihat Jadwal (Guru & Siswa)");
    private JButton btnLihatNilai = new JButton("Lihat Nilai (Siswa Only)");

    public MainDashboard(User user) {
        this.loggedInUser = user;
        setTitle("School Information System - Dashboard");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1, 10, 10));

        JLabel lblWelcome = new JLabel("Selamat Datang, " + user.getUsername() + " [" + user.getRole() + "]", SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblWelcome);

        // Tambahkan semua tombol ke layar
        add(btnCrudGuru);
        add(btnCrudSiswa);
        add(btnInputNilai);
        add(btnLihatJadwal);
        add(btnLihatNilai);

        // JALANKAN PERMISSION CHECKER / RBAC (Sesuai Tantangan Dosen)
        applyRoleBasedAccess();
    }

    private void applyRoleBasedAccess() {
        // Default sembunyikan semua tombol dulu
        btnCrudGuru.setVisible(false);
        btnCrudSiswa.setVisible(false);
        btnInputNilai.setVisible(false);
        btnLihatJadwal.setVisible(false);
        btnLihatNilai.setVisible(false);

        // Aturan Hak Akses berdasarkan Enum UserRole
        switch (loggedInUser.getRole()) {
            case ADMIN:
                btnCrudGuru.setVisible(true);
                btnCrudSiswa.setVisible(true);
                break;
            case TEACHER:
                btnInputNilai.setVisible(true);
                btnLihatJadwal.setVisible(true);
                break;
            case STUDENT:
                btnLihatNilai.setVisible(true);
                btnLihatJadwal.setVisible(true);
                break;
        }
    }
}
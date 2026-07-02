package view;

import entity.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AcademicFrame extends JFrame {
    private User loggedInUser;
    private String modulName;

    private JTextField txtField1 = new JTextField(15);
    private JTextField txtField2 = new JTextField(15);
    private JButton btnSimpan = new JButton("Simpan Data");

    public AcademicFrame(User user, String modulName) {
        this.loggedInUser = user;
        this.modulName = modulName;

        setTitle("Kelola Modul: " + modulName);
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        JLabel lbl1 = new JLabel("  Nama / Input 1:");
        JLabel lbl2 = new JLabel("  Keterangan / Input 2:");
        
        if (modulName.contains("Nilai")) {
            lbl1.setText("  Nama Siswa:");
            lbl2.setText("  Nilai Angka (0-100):");
        } else if (modulName.contains("Guru")) {
            lbl1.setText("  Nama Guru:");
            lbl2.setText("  Mata Pelajaran:");
        } else if (modulName.contains("Siswa")) {
            lbl1.setText("  Nama Siswa:");
            lbl2.setText("  Kelas:");
        }

        add(lbl1); add(txtField1);
        add(lbl2); add(txtField2);
        add(new JLabel("")); add(btnSimpan);

        btnSimpan.addActionListener((ActionEvent e) -> {
            prosesSimpan();
        });
    }

    private void prosesSimpan() {
        String input1 = txtField1.getText().trim();
        String input2 = txtField2.getText().trim();

        try {
            if (input1.isEmpty() || input2.isEmpty()) {
                throw new IllegalArgumentException("Semua kolom input wajib diisi!");
            }

            if (modulName.contains("Nilai")) {
                try {
                    int nilai = Integer.parseInt(input2);
                    if (nilai < 0 || nilai > 100) {
                        throw new IllegalArgumentException("Nilai harus berada di rentang 0 sampai 100!");
                    }
                } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException("Input Nilai harus berupa ANGKA saja!");
                }
            }

            JOptionPane.showMessageDialog(this, "Data " + modulName + " Berhasil Disimpan!\n" + input1 + " - " + input2, "Sukses", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Kesalahan Input", JOptionPane.WARNING_MESSAGE);
        }
    }
}
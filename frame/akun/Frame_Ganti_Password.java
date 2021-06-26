/**
 * Property of KELOMPOK-5 PL Kelas 2A
 * Author1: Fauzan Farhan Antoro
 * Author2: Alfanisa Safvira
 * Author3: Daffa Fawwaz Syadad
 */

package frame.akun;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import koneksi.Con_Admin;
import saved_authentication.Akun;

public class Frame_Ganti_Password extends JFrame implements ActionListener{
    JPanel jPanel_Content = new JPanel();

    JLabel jLabel_Judul = new JLabel();
    JLabel jLabel_PasswordLama = new JLabel();
    JLabel jLabel_PasswordBaru = new JLabel();
    JLabel jLabel_PasswordBaruKonfirm = new JLabel();
    
    JPasswordField passwordField_PwLama = new JPasswordField();
    JPasswordField passwordField_PwBaru = new JPasswordField();
    JPasswordField passwordField_PwBaruKonfirm = new JPasswordField();

    JButton button_Ubah = new JButton();
    JButton button_Refresh = new JButton();

    ArrayList<String> data_Yang_Kosong = new ArrayList<>();

    public Frame_Ganti_Password(){
        // Setting frame
        this.setSize(415, 220);
        this.setTitle("Ganti Password");
        this.getContentPane().setBackground(new Color(60, 63, 65));
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setIconImage(new ImageIcon(getClass().getResource("/assets/icons8-database-50.png")).getImage());
        
        // Panel Content
        jPanel_Content.setLayout(null);
        jPanel_Content.setBackground(new Color(24, 40, 44));
        jPanel_Content.setBounds(10, 10, 380, 160);
        this.getContentPane().add(jPanel_Content);

        // Label Judul
        jLabel_Judul.setForeground(Color.WHITE);
        jLabel_Judul.setFont((new Font("Segoe UI", Font.BOLD, 18)));
        jLabel_Judul.setText("Ganti Password");
        jLabel_Judul.setBounds(125, 2, 150, 30);
        jPanel_Content.add(jLabel_Judul);

        // Lalbel pw lama
        jLabel_PasswordLama.setForeground(new Color(187, 187, 187));
        jLabel_PasswordLama.setBounds(10, 30, 110, 30);
        jLabel_PasswordLama.setText("Password Lama");
        jLabel_PasswordLama.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        jPanel_Content.add(jLabel_PasswordLama);

        // Label PasswordBaru
        jLabel_PasswordBaru.setForeground(new Color(187, 187, 187));
        jLabel_PasswordBaru.setBounds(10, 60, 110, 30);
        jLabel_PasswordBaru.setText("Password Baru");
        jLabel_PasswordBaru.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        jPanel_Content.add(jLabel_PasswordBaru);

        // Label konfirmasi
        jLabel_PasswordBaruKonfirm.setForeground(new Color(187, 187, 187));
        jLabel_PasswordBaruKonfirm.setBounds(10, 90, 110, 30);
        jLabel_PasswordBaruKonfirm.setText("Konfirmasi");
        jLabel_PasswordBaruKonfirm.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        jPanel_Content.add(jLabel_PasswordBaruKonfirm);

        // Passwordfield pw lama
        passwordField_PwLama.setBackground(new Color(60, 63, 65));
        passwordField_PwLama.setForeground(new Color(187, 187, 187));
        passwordField_PwLama.setBounds(100, 33, 270, 25);
        passwordField_PwLama.setText("");
        passwordField_PwLama.setCaretColor(Color.WHITE);
        jPanel_Content.add(passwordField_PwLama);

        // Passwordfield pw baru
        passwordField_PwBaru.setBackground(new Color(60, 63, 65));
        passwordField_PwBaru.setForeground(new Color(187, 187, 187));
        passwordField_PwBaru.setBounds(100, 63, 270, 25);
        passwordField_PwBaru.setText("");
        passwordField_PwBaru.setCaretColor(Color.WHITE);
        jPanel_Content.add(passwordField_PwBaru);

        // Passwordfield konfirmasi
        passwordField_PwBaruKonfirm.setBackground(new Color(60, 63, 65));
        passwordField_PwBaruKonfirm.setForeground(new Color(187, 187, 187));
        passwordField_PwBaruKonfirm.setBounds(100, 93, 270, 25);
        passwordField_PwBaruKonfirm.setText("");
        passwordField_PwBaruKonfirm.setCaretColor(Color.WHITE);
        jPanel_Content.add(passwordField_PwBaruKonfirm);

        // Button login
        button_Ubah.setBackground(new Color(60, 63, 65));
        button_Ubah.setForeground(new Color(187, 187, 187));
        button_Ubah.setBounds(100, 125, 115, 20);
        button_Ubah.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        button_Ubah.setText("Ubah Password");
        jPanel_Content.add(button_Ubah);

        // Refresh
        button_Refresh.setBackground(new Color(60, 63, 65));
        button_Refresh.setForeground(new Color(187, 187, 187));
        button_Refresh.setBounds(260, 125, 110, 20);
        button_Refresh.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        button_Refresh.setText("Refresh");
        jPanel_Content.add(button_Refresh);

        // Listener untuk button login
        button_Ubah.addActionListener(this);
        button_Refresh.addActionListener(this);

        // Set frame visible
        this.setVisible(true);
    }

    public void refresh() {
        passwordField_PwLama.setText("");
        passwordField_PwBaru.setText("");
        passwordField_PwBaruKonfirm.setText("");
    }

    // Cek kosong
    public boolean kosong(){
        boolean kosong = false;
        for (int i = data_Yang_Kosong.size() - 1; i >= 0; i--) {
            data_Yang_Kosong.remove(i);
        }

        if(passwordField_PwLama.getPassword().length == 0){
            kosong = true;
            data_Yang_Kosong.add("Password Lama");
        } 
        if(passwordField_PwBaru.getPassword().length == 0){
            kosong = true;
            data_Yang_Kosong.add("Password Baru");
        }
        if(passwordField_PwBaruKonfirm.getPassword().length == 0){
            kosong = true;
            data_Yang_Kosong.add("Konfirmasi Password Baru");
        }

        return kosong;
    }

    // Popup untuk yang kosong
    public void kosongPopup(){
        int jumlahKosong = data_Yang_Kosong.size();
        int last = data_Yang_Kosong.size() - 1;

        if(jumlahKosong == 1) {
            JOptionPane.showMessageDialog( 
                null, 
                String.join(", ", data_Yang_Kosong) + " harus diisi!", 
                "Input Error",                
                JOptionPane.ERROR_MESSAGE);
        } else
        if(jumlahKosong == 2) {
            JOptionPane.showMessageDialog( 
                null, 
                String.join(" dan ", 
                String.join(", ", data_Yang_Kosong.subList(0, last)),
                data_Yang_Kosong.get(last)) + " harus diisi!", 
                "Input Error",                
                JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog( 
                null, 
                String.join(", dan ", 
                String.join(", ", data_Yang_Kosong.subList(0, last)),
                data_Yang_Kosong.get(last)) + " harus diisi!", 
                "Input Error",                
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public boolean pass_Sama(){
        boolean pass_Sama;

        if(new String(passwordField_PwBaru.getPassword())
            .equals(new String(passwordField_PwBaruKonfirm.getPassword()))
          ){
            pass_Sama = true;
        } else {
            pass_Sama = false;
        }

        return pass_Sama;
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource().equals(button_Refresh)){
            refresh();
        } else
        if(ae.getSource().equals(button_Ubah)) {
            if(kosong()){
                kosongPopup();
            } else {
                String passLama = new String(passwordField_PwLama.getPassword());
                Boolean loginSukses = new Con_Admin().login(Akun.ID_Admin, passLama);
    
                if(loginSukses){
                    if(pass_Sama()){ // Jika pass sama
                        String PasswordBaru = new String(passwordField_PwBaru.getPassword());

                        // Validasi panjang Password
                        if(PasswordBaru.length() < 6 || PasswordBaru.length() > 20) {
                            JOptionPane.showMessageDialog( 
                                null, 
                                "Panjang password minimal 6 sampai 20 huruf!", 
                                "Password invalid",                
                                JOptionPane.WARNING_MESSAGE);
    
                            return;
                        }

                        // Ganti Password
                        String gantiPW = new Con_Admin().ganti_Password(PasswordBaru, Akun.ID_Admin, Akun.Password);
                        if(gantiPW.equals("Password berhasil diganti!")){ // Jika berhasil diubah
                            Akun.Password = PasswordBaru;
                            JOptionPane.showMessageDialog( 
                                null, 
                                gantiPW, 
                                "Ganti Password Berhasil",         
                                JOptionPane.INFORMATION_MESSAGE);
                            
                            refresh();
                        } else { // Jika gagal diubah
                            JOptionPane.showMessageDialog(null, gantiPW, "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else { // Jika password baru dan konfirmasinya tidak sama
                        JOptionPane.showMessageDialog( 
                            null, 
                            "Password baru dan konfirmasi password baru tidak sama!", 
                            "Password tidak sama",                
                            JOptionPane.WARNING_MESSAGE);
                    }
                } else { // Jika password lama salah
                    JOptionPane.showMessageDialog( 
                        null, 
                        "Password Lama Yang Dimasukkan Salah!", 
                        "Ganti Password Gagal",                
                        JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
}

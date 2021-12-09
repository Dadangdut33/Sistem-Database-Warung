
package frame.authentication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import koneksi.Con_Admin;
import saved_authentication.Akun;
import frame.menu_utama.Frame_Menu;

import java.util.Timer;
import java.util.TimerTask;

public class Frame_Login extends JFrame implements ActionListener{
    JPanel jPanel_Header = new JPanel();
    JPanel jPanel_Content = new JPanel();
    
    JLabel jLabel_Judul = new JLabel();
    JLabel icon_User = new JLabel();
    JLabel icon_Password = new JLabel();

    static JTextField textField_User = new JTextField();
    static JPasswordField passwordField = new JPasswordField();

    JButton button_Login = new JButton();

    JLabel jLabel_Tanya = new JLabel();
    JLabel jLabel_Daftar = new JLabel();

    ArrayList<String> data_Yang_Kosong = new ArrayList<>();

    // Untuk attempt nya
    int attempt = 0;
    boolean attemptOnCooldown = false;

    // Untuk timer nunggu login ulang
    static int seconds;
    static int minutes;
    static Timer timer;

    JPanel jPanel_Cooldown = new JPanel();
    JLabel jLabel_CooldownJudul = new JLabel();
    JLabel jLabel_CooldownTimer = new JLabel();

    // Bikin objek frame login yg sifatnya global
    public static Frame_Login loginForm;

    public Frame_Login(){
        // Setting frame
        this.setSize(315, 470);
        this.setTitle("Login");
        this.getContentPane().setBackground(new Color(60, 63, 65));
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon(getClass().getResource("/assets/icons8-database-50.png")).getImage());

        // Setting panel cooldown untuk nanti apabila user gagal login 3x
        jPanel_Cooldown.setLayout(new GridLayout(2,1));
        jPanel_Cooldown.add(jLabel_CooldownJudul);
        jPanel_Cooldown.add(jLabel_CooldownTimer);

        // Panel Header
        jPanel_Header.setLayout(null);
        jPanel_Header.setBackground(new Color(18, 18, 18));
        jPanel_Header.setBounds(10, 10, 280, 50);
        jPanel_Header.setVisible(true);
        this.getContentPane().add(jPanel_Header);
        
        // Panel Content
        jPanel_Content.setLayout(null);
        jPanel_Content.setBackground(new Color(24, 40, 44));
        jPanel_Content.setBounds(10, 70, 280, 340);
        this.getContentPane().add(jPanel_Content);

        // Label Judul
        jLabel_Judul.setForeground(new Color(187, 187, 187));
        jLabel_Judul.setFont((new Font("Segoe UI", Font.BOLD, 18)));
        jLabel_Judul.setText("LOGIN");
        jLabel_Judul.setBounds(105, 10, 80, 30);
        jPanel_Header.add(jLabel_Judul);

        // Icon user
        icon_User.setIcon(new ImageIcon(getClass().getResource("/assets/icons8-account-2-30.png")));
        icon_User.setBounds(10, 20, 40, 30);
        jPanel_Content.add(icon_User);

        // Icon Password
        icon_Password.setIcon(new ImageIcon(getClass().getResource("/assets/icons8-lock-30.png")));
        icon_Password.setBounds(10, 70, 40, 30);
        jPanel_Content.add(icon_Password);

        // Textfield user
        textField_User.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        textField_User.setBackground(new Color(60, 63, 65));
        textField_User.setForeground(new Color(187, 187, 187));
        textField_User.setBounds(50, 22, 215, 25);
        textField_User.setText("");
        textField_User.setCaretColor(Color.WHITE);
        jPanel_Content.add(textField_User);

        // Passwordfield password
        passwordField.setBackground(new Color(60, 63, 65));
        passwordField.setForeground(new Color(187, 187, 187));
        passwordField.setBounds(50, 72, 215, 25);
        passwordField.setText("");
        passwordField.setCaretColor(Color.WHITE);
        jPanel_Content.add(passwordField);

        // Button login
        button_Login.setBackground(new Color(60, 63, 65));
        button_Login.setForeground(new Color(187, 187, 187));
        button_Login.setBounds(50, 115, 215, 22);
        button_Login.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        button_Login.setText("Sign In");
        jPanel_Content.add(button_Login);

        // Label tanya yang dibawah
        jLabel_Tanya.setForeground(new Color(187, 187, 187));
        jLabel_Tanya.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        jLabel_Tanya.setText("Belum punya akun?");
        jLabel_Tanya.setBounds(90, 290, 110, 22);
        jPanel_Content.add(jLabel_Tanya);

        // Label daftar dibawhnya lagi
        jLabel_Daftar.setForeground(new Color(187, 187, 187));
        jLabel_Daftar.setFont((new Font("Segoe UI", Font.BOLD, 12)));
        jLabel_Daftar.setText("Daftar sekarang");
        jLabel_Daftar.setBounds(96, 310, 110, 22);
        
        // Listener untuk label daftar, agar saat dipencet akan membawa ke frame daftar
        jLabel_Daftar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                daftar();
            }
        });
        jPanel_Content.add(jLabel_Daftar);

        // Listener untuk button login
        button_Login.addActionListener(this);

        // Set frame visible
        this.setVisible(true);
    }

    // Function daftar
    void daftar() {
        this.setVisible(false);
        new Frame_Daftar();
    }

    // Cek kosong
    boolean kosong(){
        boolean kosong = false;
        for (int i = data_Yang_Kosong.size() - 1; i >= 0; i--) {
            data_Yang_Kosong.remove(i);
        }

        if(textField_User.getText().trim().equals("")){
            kosong = true;
            data_Yang_Kosong.add("User ID");
        } 
        if(passwordField.getPassword().length == 0){
            kosong = true;
            data_Yang_Kosong.add("Password");
        }

        return kosong;
    }

    // Popup untuk yang kosong
    void kosongPopup(){
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
        }
    }

    public static void refresh(){
        textField_User.setText("");
        passwordField.setText("");
        loginForm.repaint();
        loginForm.revalidate();
    }

    // Function untuk cooldown
    void startCD(){
        // Delay dan period setiap 1 detik
        int delay = 1000;
        int period = 1000;

        // Timernya
        timer = new Timer();
        
        // Lamanya cooldown
        seconds = 0;
        minutes = 5;

        // Set Label Cooldown
        jLabel_CooldownJudul.setText("Anda Telah Gagal Login Sebanyak 3 Kali!");
        jLabel_CooldownTimer.setText("Harap Tunggu Selama " + minutes + ":" + seconds + " Menit");

        // Ini start timernya
        timer.scheduleAtFixedRate(new TimerTask() {
            // Run Jalan Tiap 1 detik
            @Override
            public void run() {
                setInterval();
            }
        }, delay, period);
    }

    // Untuk timernya
    void setInterval() {
        if (seconds == 0 && minutes == 0) {
            jLabel_CooldownTimer.setText("Harap Tunggu Selama " + minutes + ":" + seconds + " Menit");
            
            // Cancel Timer
            timer.cancel();

            // Pause dulu 1 detik
            try {
                Thread.sleep(1000);    
            } catch (Exception e) { /* Ignored */ }
            
            // Ganti textnya, kasih tau sudah selesai
            jLabel_CooldownJudul.setText("5 Menit Sudah Berlalu");
            jLabel_CooldownTimer.setText("Silahkan Coba Login Kembali!");

            // Reset Attempt
            attemptOnCooldown = false;
            attempt = 0;
        } else {
            // Kurang terus detiknya selama masih ada menit (0) dan detiknya juga masih ada
            if(minutes != -1 && seconds != -1){
                --seconds;
            }

            // reset detik jadi 59 dan kurangi menit sebanyak 1, apabila detik masih ada dan menit bukan 0
            if(seconds == -1 && minutes != 0) {
                seconds = 59;
                --minutes;
            }

            // Update text
            jLabel_CooldownTimer.setText("Harap Tunggu Selama " + minutes + ":" + seconds + " Menit");
        }
    }

    // Login
    void loginUser(){
        String id = textField_User.getText().trim();
        String pass = new String(passwordField.getPassword());
        
        // Cek status login
        Boolean loginSukses = new Con_Admin().login(id, pass);
        // Kalau sukses
        if(loginSukses) {
            List<Object> data = new Con_Admin().get_Akun(id, pass);

            Object[] parsedData = (Object[]) data.toArray(new Object[0]);
            
            // Isi data ke cache lokal
            Akun.ID_Admin = parsedData[0].toString();
            Akun.Password = parsedData[1].toString();
            Akun.Nama_Pemilik = parsedData[2].toString();
            Akun.Nama_Toko = parsedData[3].toString();
            Akun.Alamat_Toko = parsedData[4].toString();
            Akun.Nomor_Telepon = parsedData[5].toString();

            // Reset Attempt
            attempt = 0;
            attemptOnCooldown = false;

            // Muncul dialog login berhasil
            JOptionPane.showMessageDialog( 
                null, 
                "Login Sukses! Selamat Datang " + Akun.Nama_Pemilik, 
                "Login Berhasil",                
                JOptionPane.INFORMATION_MESSAGE);

            // Dispose frame login
            this.dispose();

            // Panggil frame menu
            new Frame_Menu();
        
        // Kalau gagal login
        } else {
            // Naikin attemptnya
            attempt++;

            // Set Cooldown jika gagal login > 3x
            if (attempt > 3){
                attemptOnCooldown = true;
                startCD();

                JOptionPane.showMessageDialog( 
                    null, 
                    jPanel_Cooldown, 
                    "Login Gagal",                
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Dialog Login Gagal
            JOptionPane.showMessageDialog( 
                null, 
                "Login gagal! ID atau Password salah!", 
                "Login Gagal",                
                JOptionPane.WARNING_MESSAGE);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource().equals(button_Login)){
            if(kosong() && !attemptOnCooldown){ // Jika kosong aja
                kosongPopup();
            } else
            if(attemptOnCooldown){ // Jika cooldown
                JOptionPane.showMessageDialog( 
                    null, 
                    jPanel_Cooldown, 
                    "Cooldown",                
                    JOptionPane.WARNING_MESSAGE);
            } else { // Jika dah ga Cooldown
                loginUser();
            }                
        }
    }
}

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
import tools.OnlyDigit;
import tools.OnlyLetter;

public class Frame_Edit_Biodata extends JFrame implements ActionListener {
    JPanel jPanel_Header = new JPanel();
    JPanel jPanel_Content = new JPanel();
    
    JLabel jLabel_Judul = new JLabel();

    JLabel jLabel_Pemilik = new JLabel();
    JLabel jLabel_Toko = new JLabel();
    JLabel jLabel_Alamat = new JLabel();
    JLabel jLabel_NomorTelepon = new JLabel();

    JTextField textField_Pemilik = new JTextField();
    JTextField textField_Toko = new JTextField();
    JTextArea textArea_Alamat = new JTextArea();
    JTextField textField_NomorTelepon = new JTextField();

    JButton button_Ubah = new JButton();
    JButton button_Refresh = new JButton();

    JScrollPane spane = new JScrollPane(textArea_Alamat, 
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    ArrayList<String> data_Yang_Kosong = new ArrayList<>();

    public Frame_Edit_Biodata(){
        // Setting frame
        this.setSize(355, 350);
        this.setTitle("Edit Biodata");
        this.getContentPane().setBackground(new Color(60, 63, 65));
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setIconImage(new ImageIcon(getClass().getResource("/assets/icons8-database-50.png")).getImage());

        // Panel Header
        jPanel_Header.setLayout(null);
        jPanel_Header.setBackground(new Color(18, 18, 18));
        jPanel_Header.setBounds(10, 10, 320, 50);
        
        // Panel Konten
        jPanel_Content.setLayout(null);
        jPanel_Content.setBackground(new Color(24, 40, 44));
        jPanel_Content.setBounds(10, 70, 320, 225);
        
        // Label Judul
        jLabel_Judul.setForeground(Color.WHITE);
        jLabel_Judul.setFont((new Font("Segoe UI", Font.BOLD, 18)));
        jLabel_Judul.setText("EDIT BIODATA");
        jLabel_Judul.setBounds(95, 10, 200, 30);
        jPanel_Header.add(jLabel_Judul);

        // Label Pemilik
        jLabel_Pemilik.setForeground(new Color(187, 187, 187));
        jLabel_Pemilik.setBounds(10, 20, 150, 30);
        jLabel_Pemilik.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        jLabel_Pemilik.setText("Pemilik Toko");
        jPanel_Content.add(jLabel_Pemilik);

        // Textfield pemilik
        textField_Pemilik.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        textField_Pemilik.setBackground(new Color(60, 63, 65));
        textField_Pemilik.setForeground(new Color(187, 187, 187));
        textField_Pemilik.setBounds(100, 22, 210, 25);
        textField_Pemilik.setDocument(new OnlyLetter().getOnlyLetter());
        textField_Pemilik.setText(Akun.Nama_Pemilik);
        textField_Pemilik.setCaretColor(Color.WHITE);
        jPanel_Content.add(textField_Pemilik);

        // Label alamat
        jLabel_Toko.setForeground(new Color(187, 187, 187));
        jLabel_Toko.setBounds(10, 50, 150, 30);
        jLabel_Toko.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        jLabel_Toko.setText("Nama Toko");
        jPanel_Content.add(jLabel_Toko);

        // Textfield alamat
        textField_Toko.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        textField_Toko.setBackground(new Color(60, 63, 65));
        textField_Toko.setForeground(new Color(187, 187, 187));
        textField_Toko.setBounds(100, 52, 210, 25);
        textField_Toko.setText(Akun.Nama_Toko);
        textField_Toko.setCaretColor(Color.WHITE);
        jPanel_Content.add(textField_Toko);

        // Label alamat
        jLabel_Alamat.setForeground(new Color(187, 187, 187));
        jLabel_Alamat.setBounds(10, 80, 150, 30);
        jLabel_Alamat.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        jLabel_Alamat.setText("Alamat Toko");
        jPanel_Content.add(jLabel_Alamat);
    
        // TextArea Alamat
        textArea_Alamat.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        textArea_Alamat.setBackground(new Color(60, 63, 65));
        textArea_Alamat.setForeground(new Color(187, 187, 187));
        textArea_Alamat.setText(Akun.Alamat_Toko);
        textArea_Alamat.setCaretColor(Color.WHITE);
        textArea_Alamat.setLineWrap(true);
        textArea_Alamat.setWrapStyleWord(true);

        // Setting spane
        spane.getVerticalScrollBar().setBackground(new Color(60, 63, 65));
        spane.getVerticalScrollBar().setForeground(new Color(187, 187, 187));
        spane.setBounds(100, 82, 210, 70);
        jPanel_Content.add(spane);

        // Label notelpon
        jLabel_NomorTelepon.setForeground(new Color(187, 187, 187));
        jLabel_NomorTelepon.setBounds(10, 155, 150, 30);
        jLabel_NomorTelepon.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        jLabel_NomorTelepon.setText("Nomor Telepon");
        jPanel_Content.add(jLabel_NomorTelepon);

        // Textfield notelpon
        textField_NomorTelepon.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        textField_NomorTelepon.setBackground(new Color(60, 63, 65));
        textField_NomorTelepon.setForeground(new Color(187, 187, 187));
        textField_NomorTelepon.setCaretColor(Color.WHITE);
        textField_NomorTelepon.setBounds(100, 157, 210, 25);
        textField_NomorTelepon.setDocument(new OnlyDigit().getOnlyDigit());
        textField_NomorTelepon.setText(Akun.Nomor_Telepon);
        jPanel_Content.add(textField_NomorTelepon);

        // Button Add
        button_Ubah.setBackground(new Color(60, 63, 65));
        button_Ubah.setForeground(new Color(187, 187, 187));
        button_Ubah.setBounds(100, 190, 90, 20);
        button_Ubah.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        button_Ubah.setText("Ubah Data");
        jPanel_Content.add(button_Ubah);

        // Button refresh
        button_Refresh.setBackground(new Color(60, 63, 65));
        button_Refresh.setForeground(new Color(187, 187, 187));
        button_Refresh.setBounds(215, 190, 90, 20);
        button_Refresh.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        button_Refresh.setText("Refresh");
        jPanel_Content.add(button_Refresh);

        // Action listener button
        button_Ubah.addActionListener(this);
        button_Refresh.addActionListener(this);

        // Add panel header dan konten
        this.add(jPanel_Header);
        this.add(jPanel_Content);

        // Setvisible
        this.setVisible(true);
    }

    public void refresh(){
        textField_Toko.setText(Akun.Nama_Toko);
        textField_Pemilik.setText(Akun.Nama_Pemilik);
        textArea_Alamat.setText(Akun.Alamat_Toko);
        textField_NomorTelepon.setText(Akun.Nomor_Telepon);
    }

    public boolean kosong(){
        boolean kosong = false;
        for (int i = data_Yang_Kosong.size() - 1; i >= 0; i--) {
            data_Yang_Kosong.remove(i);
        }

        if(textField_Pemilik.getText().trim().equals("")){
            kosong = true;
            data_Yang_Kosong.add("Nama Pemilik");
        }
        if(textField_Toko.getText().trim().equals("")){
            kosong = true;
            data_Yang_Kosong.add("Nama Toko");
        }
        if(textArea_Alamat.getText().trim().equals("")){
            kosong = true;
            data_Yang_Kosong.add("Alamat");
        }
        if(textField_NomorTelepon.getText().trim().equals("")){
            kosong = true;
            data_Yang_Kosong.add("Nomor Telepon");
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
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource().equals(button_Refresh)) {
            refresh();
        } else
        if(ae.getSource().equals(button_Ubah)) {
            // Cek kosong
            if(kosong()){
                kosongPopup();
            } else {
                // Dapatkan datanya
                String Nama_Pemilik = textField_Pemilik.getText().trim();
                String Nama_Toko = textField_Toko.getText().trim();
                String Alamat_Toko = textArea_Alamat.getText().trim();
                String Nomor_Telepon = textField_NomorTelepon.getText().trim();

                String updateStatus = new Con_Admin().edit_Biodata(Akun.ID_Admin, Akun.Password, Nama_Pemilik, Nama_Toko, Alamat_Toko, Nomor_Telepon);
                // Jika berhasil
                if(updateStatus.equals("Data berhasil diubah!")){
                    JOptionPane.showMessageDialog( 
                        null, 
                        updateStatus, 
                        "Edit Biodata Berhasil",         
                        JOptionPane.INFORMATION_MESSAGE);
                    Akun.Nama_Pemilik = Nama_Pemilik;
                    Akun.Nama_Toko = Nama_Toko;
                    Akun.Alamat_Toko = Alamat_Toko;
                    Akun.Nomor_Telepon = Nomor_Telepon;

                    refresh();
                } else { // Jika gagal edit 
                    JOptionPane.showMessageDialog(null, updateStatus, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}

/**
 * Property of KELOMPOK-5 PL Kelas 2A
 * Author1: Fauzan Farhan Antoro
 * Author2: Alfanisa Safvira
 * Author3: Daffa Fawwaz Syadad
 */

package frame.pelanggan;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import koneksi.Con_Pelanggan;
import saved_authentication.Akun;
import tools.OnlyDigit;


// IGNORE MASIH WIP BELUM DIAPA2IN
public class Frame_Add_Pelanggan extends JFrame implements ActionListener {
    JPanel jPanel_Header = new JPanel();
    JPanel jPanel_Content = new JPanel();
    
    JLabel jLabel_Judul = new JLabel();

    JLabel jLabel_NamaPelanggan = new JLabel();
    JLabel jLabel_AlamatPelanggan = new JLabel();
    JLabel jLabel_TeleponPelanggan = new JLabel();

    JTextField jTextField_NamaPelanggan = new JTextField();
    JTextArea jTextArea_AlamatPelanggan = new JTextArea();
    JTextField jTextField_TeleponPelanggan = new JTextField();

    JScrollPane spane = new JScrollPane(jTextArea_AlamatPelanggan, 
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    JButton button_Add = new JButton();
    JButton button_Refresh = new JButton();

    ArrayList<String> data_Yang_Kosong = new ArrayList<>();

    public static void main(String[] args) {
        new Frame_Add_Pelanggan();
    }

    public Frame_Add_Pelanggan(){
        // Setting frame
        this.setSize(355, 330);
        this.setTitle("Add Pelanggan");
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
        jPanel_Content.setBounds(10, 70, 320, 210);
        
        // Label Judul
        jLabel_Judul.setForeground(Color.WHITE);
        jLabel_Judul.setFont((new Font("Segoe UI", Font.BOLD, 18)));
        jLabel_Judul.setText("ADD PELANGGAN");
        jLabel_Judul.setBounds(90, 10, 250, 30);
        jPanel_Header.add(jLabel_Judul);

        // Label nama
        jLabel_NamaPelanggan.setForeground(new Color(187, 187, 187));
        jLabel_NamaPelanggan.setBounds(10, 20, 150, 30);
        jLabel_NamaPelanggan.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        jLabel_NamaPelanggan.setText("Nama");
        jPanel_Content.add(jLabel_NamaPelanggan);

        // textfield nama
        jTextField_NamaPelanggan.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        jTextField_NamaPelanggan.setBackground(new Color(60, 63, 65));
        jTextField_NamaPelanggan.setForeground(new Color(187, 187, 187));
        jTextField_NamaPelanggan.setBounds(100, 22, 210, 25);
        jTextField_NamaPelanggan.setCaretColor(Color.WHITE);
        jPanel_Content.add(jTextField_NamaPelanggan);

        // Label alamat
        jLabel_AlamatPelanggan.setForeground(new Color(187, 187, 187));
        jLabel_AlamatPelanggan.setBounds(10, 50, 150, 30);
        jLabel_AlamatPelanggan.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        jLabel_AlamatPelanggan.setText("Alamat");
        jPanel_Content.add(jLabel_AlamatPelanggan);

        // Textarea alamat
        jTextArea_AlamatPelanggan.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        jTextArea_AlamatPelanggan.setBackground(new Color(60, 63, 65));
        jTextArea_AlamatPelanggan.setForeground(new Color(187, 187, 187));
        jTextArea_AlamatPelanggan.setBounds(100, 52, 210, 80);
        jTextArea_AlamatPelanggan.setCaretColor(Color.WHITE);
        jTextArea_AlamatPelanggan.setLineWrap(true);
        jTextArea_AlamatPelanggan.setWrapStyleWord(true);

        spane.getVerticalScrollBar().setBackground(new Color(60, 63, 65));
        spane.getVerticalScrollBar().setForeground(new Color(187, 187, 187));
        spane.setBounds(100, 52, 210, 80);
        jPanel_Content.add(spane);

        // Label no telp
        jLabel_TeleponPelanggan.setForeground(new Color(187, 187, 187));
        jLabel_TeleponPelanggan.setBounds(10, 135, 150, 30);
        jLabel_TeleponPelanggan.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        jLabel_TeleponPelanggan.setText("No. Telepon");
        jPanel_Content.add(jLabel_TeleponPelanggan);
    
        // TextArea no telp
        jTextField_TeleponPelanggan.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        jTextField_TeleponPelanggan.setBackground(new Color(60, 63, 65));
        jTextField_TeleponPelanggan.setForeground(new Color(187, 187, 187));
        jTextField_TeleponPelanggan.setBounds(100, 137, 210, 25);
        jTextField_TeleponPelanggan.setDocument(new OnlyDigit().getOnlyDigit());
        jPanel_Content.add(jTextField_TeleponPelanggan);

        // Button Add
        button_Add.setBackground(new Color(60, 63, 65));
        button_Add.setForeground(new Color(187, 187, 187));
        button_Add.setBounds(100, 167, 90, 20);
        button_Add.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        button_Add.setText("Add Data");
        jPanel_Content.add(button_Add);

        // Button refresh
        button_Refresh.setBackground(new Color(60, 63, 65));
        button_Refresh.setForeground(new Color(187, 187, 187));
        button_Refresh.setBounds(215, 167, 90, 20);
        button_Refresh.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        button_Refresh.setText("Refresh");
        jPanel_Content.add(button_Refresh);

        // Action listener button
        button_Add.addActionListener(this);
        button_Refresh.addActionListener(this);

        // Add panel header dan konten
        this.add(jPanel_Header);
        this.add(jPanel_Content);

        // Setvisible
        this.setVisible(true);
    }

    void refresh(){
        jTextField_NamaPelanggan.setText("");
        jTextArea_AlamatPelanggan.setText("");
        jTextField_TeleponPelanggan.setText("");
    }

    boolean kosong(){
        boolean kosong = false;
        for (int i = data_Yang_Kosong.size() - 1; i >= 0; i--) {
            data_Yang_Kosong.remove(i);
        }

        if(jTextField_NamaPelanggan.getText().equals("")){
            kosong = true;
            data_Yang_Kosong.add("Nama Pelanggan");
        }
        if(jTextArea_AlamatPelanggan.getText().equals("")){
            kosong = true;
            data_Yang_Kosong.add("Alamat Pelanggan");
        }
        if(jTextField_TeleponPelanggan.getText().equals("")){
            kosong = true;
            data_Yang_Kosong.add("Telepon Pelanggan");
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
        if(ae.getSource().equals(button_Add)) {
            // Cek kosong
            if(kosong()){
                kosongPopup();
            } else {
                // Dapatkan datanya
                String Nama_Pelanggan = jTextField_NamaPelanggan.getText().trim();
                String Alamat_Pelanggan = jTextArea_AlamatPelanggan.getText().trim();
                String Telepon_Pelanggan = jTextField_TeleponPelanggan.getText().trim();

                String StatusAddPelanggan = new Con_Pelanggan().add_Pelanggan(Nama_Pelanggan, Alamat_Pelanggan, Telepon_Pelanggan, Akun.ID_Admin);

                // Jika berhasil
               if(StatusAddPelanggan.equals("Data Pelanggan Berhasil Ditambah!")){
                   JOptionPane.showMessageDialog( 
                    null, 
                    StatusAddPelanggan, 
                    "Add Pelanggan Berhasil",         
                    JOptionPane.INFORMATION_MESSAGE);
                    
                    refresh();
               } else { // Jika gagal
                    JOptionPane.showMessageDialog(null, StatusAddPelanggan, "Error", JOptionPane.ERROR_MESSAGE);
               }
            }
        }
    }
}

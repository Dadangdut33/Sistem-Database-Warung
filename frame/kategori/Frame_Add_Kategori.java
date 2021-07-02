/**
 * Property of KELOMPOK-5 PL Kelas 2A
 * Author1: Fauzan Farhan Antoro
 * Author2: Alfanisa Safvira
 * Author3: Daffa Fawwaz Syadad
 */

package frame.kategori;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import koneksi.Con_Kategori;
import panels.Panel_Kategori;
import saved_authentication.Akun;

import tools.OnlyLetter;

public class Frame_Add_Kategori extends JFrame implements ActionListener {
    JPanel jPanel_Header = new JPanel();
    JPanel jPanel_Content = new JPanel();
    
    JLabel jLabel_Judul = new JLabel();

    JLabel jLabel_KodeKategori = new JLabel();
    JLabel jLabel_NamaKategori = new JLabel();

    JTextField jTextField_KodeKategori = new JTextField();
    JTextField jTextField_NamaKategori = new JTextField();

    JButton button_Add = new JButton();
    JButton button_Refresh = new JButton();

    ArrayList<String> data_Yang_Kosong = new ArrayList<>();

    public Frame_Add_Kategori(){
        // Setting frame
        this.setSize(355, 250);
        this.setTitle("Add Kategori");
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
        jPanel_Content.setBounds(10, 70, 320, 130);
        
        // Label Judul
        jLabel_Judul.setForeground(Color.WHITE);
        jLabel_Judul.setFont((new Font("Segoe UI", Font.BOLD, 18)));
        jLabel_Judul.setText("ADD KATEGORI");
        jLabel_Judul.setBounds(100, 10, 250, 30);
        jPanel_Header.add(jLabel_Judul);

        // Label kode kategori
        jLabel_KodeKategori.setForeground(new Color(187, 187, 187));
        jLabel_KodeKategori.setBounds(10, 20, 150, 30);
        jLabel_KodeKategori.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        jLabel_KodeKategori.setText("Kode Kategori");
        jPanel_Content.add(jLabel_KodeKategori);

        // textfield kode kategori
        jTextField_KodeKategori.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        jTextField_KodeKategori.setBackground(new Color(60, 63, 65));
        jTextField_KodeKategori.setForeground(new Color(187, 187, 187));
        jTextField_KodeKategori.setBounds(100, 22, 210, 25);
        jTextField_KodeKategori.setCaretColor(Color.WHITE);
        jTextField_KodeKategori.setDocument(new OnlyLetter().getOnlyLetter());
        jPanel_Content.add(jTextField_KodeKategori);

        // Label nama kategori
        jLabel_NamaKategori.setForeground(new Color(187, 187, 187));
        jLabel_NamaKategori.setBounds(10, 50, 150, 30);
        jLabel_NamaKategori.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        jLabel_NamaKategori.setText("Nama Kategori");
        jPanel_Content.add(jLabel_NamaKategori);

        // textfield nama kategori
        jTextField_NamaKategori.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        jTextField_NamaKategori.setBackground(new Color(60, 63, 65));
        jTextField_NamaKategori.setForeground(new Color(187, 187, 187));
        jTextField_NamaKategori.setBounds(100, 52, 210, 25);
        jTextField_NamaKategori.setCaretColor(Color.WHITE);
        jPanel_Content.add(jTextField_NamaKategori);

        // Button Add
        button_Add.setBackground(new Color(60, 63, 65));
        button_Add.setForeground(new Color(187, 187, 187));
        button_Add.setBounds(100, 82, 90, 20);
        button_Add.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        button_Add.setText("Add Data");
        jPanel_Content.add(button_Add);

        // Button refresh
        button_Refresh.setBackground(new Color(60, 63, 65));
        button_Refresh.setForeground(new Color(187, 187, 187));
        button_Refresh.setBounds(220, 82, 90, 20);
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
        jTextField_KodeKategori.setText("");
        jTextField_NamaKategori.setText("");
    }

    boolean kosong(){
        boolean kosong = false;
        for (int i = data_Yang_Kosong.size() - 1; i >= 0; i--) {
            data_Yang_Kosong.remove(i);
        }

        if(jTextField_KodeKategori.getText().equals("")){
            kosong = true;
            data_Yang_Kosong.add("Kode Kategori");
        }
        if(jTextField_NamaKategori.getText().equals("")){
            kosong = true;
            data_Yang_Kosong.add("Nama Kategori");
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
                if(jTextField_KodeKategori.getText().length() < 3 || jTextField_KodeKategori.getText().length() > 20) {
                    JOptionPane.showMessageDialog( 
                        null,
                        "Kode Kategori minimal 3 sampai 20 huruf!", 
                        "Kode Invalid",
                        JOptionPane.WARNING_MESSAGE);

                    return;
                }

                // Dapatkan datanya
                String Kode_Kategori = jTextField_KodeKategori.getText().trim();
                String Nama_Kategori = jTextField_NamaKategori.getText().trim();

                if(new Con_Kategori().dupeCheck(Nama_Kategori, Akun.ID_Admin)){
                    JOptionPane.showMessageDialog( 
                        null,
                        "Nama Kategori Yang Dimasukkan Tidak Boleh Sama Dengan Yang Sudah Ada!", 
                        "Data Duplikat",
                        JOptionPane.WARNING_MESSAGE);

                    return;
                }

                String StatusAddKategori = new Con_Kategori().add_Kategori(Kode_Kategori, Nama_Kategori, Akun.ID_Admin);

                // Jika berhasil
               if(StatusAddKategori.equals("Data Kategori Berhasil Ditambah!")){
                   JOptionPane.showMessageDialog( 
                    null, 
                    StatusAddKategori, 
                    "Add Kategori Berhasil",         
                    JOptionPane.INFORMATION_MESSAGE);
                    
                    refresh();
                    Panel_Kategori.refreshAll();
               } else { // Jika gagal
                    JOptionPane.showMessageDialog(null, StatusAddKategori, "Error", JOptionPane.ERROR_MESSAGE);
               }
            }
        }
    }
}

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
import java.util.List;

import koneksi.Con_Kategori;
import saved_authentication.Akun;

public class Frame_Delete_Kategori extends JFrame implements ActionListener {
    JPanel jPanel_Header = new JPanel();
    JPanel jPanel_Content = new JPanel();
    
    JLabel jLabel_Judul = new JLabel();

    JLabel jLabel_KodeKategori = new JLabel();
    JLabel jLabel_NamaKategori = new JLabel();

    JComboBox<String> comboBox_KodeKategori = new JComboBox<>();
    JTextField jTextField_NamaKategori = new JTextField();

    JButton button_Delete = new JButton();
    JButton button_Refresh = new JButton();

    ArrayList<String> data_Yang_Kosong = new ArrayList<>();

    public static void main(String[] args) {
        new Frame_Delete_Kategori();
    }

    public Frame_Delete_Kategori(){
        // Setting frame
        this.setSize(355, 250);
        this.setTitle("Delete Kategori");
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
        jLabel_Judul.setText("DELETE KATEGORI");
        jLabel_Judul.setBounds(80, 10, 250, 30);
        jPanel_Header.add(jLabel_Judul);

        // Label kode kategori
        jLabel_KodeKategori.setForeground(new Color(187, 187, 187));
        jLabel_KodeKategori.setBounds(10, 20, 150, 30);
        jLabel_KodeKategori.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        jLabel_KodeKategori.setText("Kode Kategori");
        jPanel_Content.add(jLabel_KodeKategori);

        // textfield kode kategori
        comboBox_KodeKategori.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        comboBox_KodeKategori.setBackground(new Color(60, 63, 65));
        comboBox_KodeKategori.setForeground(new Color(187, 187, 187));
        comboBox_KodeKategori.setBounds(100, 22, 210, 25);
        isiCbKodeKategori();
        jPanel_Content.add(comboBox_KodeKategori);

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
        jTextField_NamaKategori.setEditable(false);
        jPanel_Content.add(jTextField_NamaKategori);

        // Button Add
        button_Delete.setBackground(new Color(60, 63, 65));
        button_Delete.setForeground(new Color(187, 187, 187));
        button_Delete.setBounds(100, 82, 95, 20);
        button_Delete.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        button_Delete.setText("Delete Data");
        jPanel_Content.add(button_Delete);

        // Button refresh
        button_Refresh.setBackground(new Color(60, 63, 65));
        button_Refresh.setForeground(new Color(187, 187, 187));
        button_Refresh.setBounds(220, 82, 90, 20);
        button_Refresh.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        button_Refresh.setText("Refresh");
        jPanel_Content.add(button_Refresh);

        // Action listener button
        button_Delete.addActionListener(this);
        button_Refresh.addActionListener(this);

        // Add panel header dan konten
        this.add(jPanel_Header);
        this.add(jPanel_Content);

        // Setvisible
        this.setVisible(true);
    }

    void isiCbKodeKategori(){
        List<Object> data = new Con_Kategori().get_All_KodeKategori(Akun.ID_Admin);
        Object[] parsedData = (Object[]) data.toArray(new Object[0]);
        
        comboBox_KodeKategori.addItem("Pilih Kode Kategori");
        // Diisi dengan semua data di convert jadi string
        for (int i = 0; i < parsedData.length; i++) {
            comboBox_KodeKategori.addItem(parsedData[i].toString());
        }
    }

    void isiData(){
        if(comboBox_KodeKategori.getSelectedIndex() == 0){
            refresh();
        } else {
            List<Object> data = new Con_Kategori().get_NamaKategoriByKode(comboBox_KodeKategori.getSelectedItem().toString(), Akun.ID_Admin);
            Object[] parsedData = (Object[]) data.toArray(new Object[0]);
            
            jTextField_NamaKategori.setText(parsedData[0].toString());
        }
    }

    void refresh(){
        for(ActionListener act : comboBox_KodeKategori.getActionListeners()){
            comboBox_KodeKategori.removeActionListener(act);
        }
        comboBox_KodeKategori.removeAllItems();
        jTextField_NamaKategori.setText("");
        isiCbKodeKategori();
        comboBox_KodeKategori.setSelectedIndex(0);
        comboBox_KodeKategori.addActionListener(this);
    }

    boolean kosong(){
        boolean kosong = false;
        for (int i = data_Yang_Kosong.size() - 1; i >= 0; i--) {
            data_Yang_Kosong.remove(i);
        }

        if(comboBox_KodeKategori.getSelectedIndex() == 0){
            kosong = true;
            data_Yang_Kosong.add("Kode Kategori");
        }
        
        return kosong;
    }

    // Popup untuk yang kosong
    void kosongPopup(){
        JOptionPane.showMessageDialog( 
            null, 
            String.join(", ", data_Yang_Kosong) + " harus dipilih!", 
            "Input Error",                
            JOptionPane.ERROR_MESSAGE);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource().equals(button_Refresh)) {
            refresh();
        } else
        if(ae.getSource().equals(button_Delete)) {
            // Cek kosong
            if(kosong()){
                kosongPopup();
            } else {
                // Dapatkan datanya
                String Kode_Kategori = comboBox_KodeKategori.getSelectedItem().toString();

                String statusHapus = new Con_Kategori().delete_Kategori(Kode_Kategori, Akun.ID_Admin);

                if(statusHapus.equals("Kategori Berhasil Dihapus!")){
                    JOptionPane.showMessageDialog( 
                        null, 
                        statusHapus, 
                        "Delete Kategori Berhasil",         
                        JOptionPane.INFORMATION_MESSAGE);
                    
                    refresh();
                } else {
                    JOptionPane.showMessageDialog(null, statusHapus, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else
        if(ae.getSource().equals(comboBox_KodeKategori)){
            isiData();
        }
    }
}

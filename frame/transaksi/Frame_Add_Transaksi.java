/**
 * Property of KELOMPOK-5 PL Kelas 2A
 * Author1: Fauzan Farhan Antoro
 * Author2: Alfanisa Safvira
 * Author3: Daffa Fawwaz Syadad
 */

package frame.transaksi;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import koneksi.Con_Barang;
import koneksi.Con_Laporan;
import koneksi.Con_Pelanggan;
import saved_authentication.Akun;

public class Frame_Add_Transaksi extends JFrame implements ActionListener {
    JPanel jPanel_Header = new JPanel();
    JPanel jPanel_Content = new JPanel();
    
    JLabel jLabel_Judul = new JLabel();

    JLabel jLabel_KodePelanggan = new JLabel();
    JLabel jLabel_KodeBarang = new JLabel();
    JLabel jLabel_JumlahPesanan = new JLabel();

    JComboBox<String> comboBox_KodePelanggan = new JComboBox<>();
    JComboBox<String> comboBox_KodeBarang = new JComboBox<>();
    
    SpinnerModel spModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
    JSpinner spinner_JumlahPesanan = new JSpinner(spModel);

    JButton button_Add = new JButton();
    JButton button_Refresh = new JButton();

    ArrayList<String> data_Yang_Kosong = new ArrayList<>();

    public Frame_Add_Transaksi(){
        // Setting frame
        this.setSize(355, 300);
        this.setTitle("Add Laporan Transaksi");
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
        jPanel_Content.setBounds(10, 70, 320, 180);
        
        // Label Judul
        jLabel_Judul.setForeground(Color.WHITE);
        jLabel_Judul.setFont((new Font("Segoe UI", Font.BOLD, 18)));
        jLabel_Judul.setText("ADD LAPORAN TRANSAKSI");
        jLabel_Judul.setBounds(40, 10, 250, 30);
        jPanel_Header.add(jLabel_Judul);

        // Label Kode pelanggan
        jLabel_KodePelanggan.setForeground(new Color(187, 187, 187));
        jLabel_KodePelanggan.setBounds(10, 20, 150, 30);
        jLabel_KodePelanggan.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        jLabel_KodePelanggan.setText("Kode Pelanggan");
        jPanel_Content.add(jLabel_KodePelanggan);

        // combobox kode pelanggan
        comboBox_KodePelanggan.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        comboBox_KodePelanggan.setBackground(new Color(60, 63, 65));
        comboBox_KodePelanggan.setForeground(new Color(187, 187, 187));
        comboBox_KodePelanggan.setBounds(100, 22, 210, 25);
        isiCbKodePelanggan();
        jPanel_Content.add(comboBox_KodePelanggan);

        // Label kode barang
        jLabel_KodeBarang.setForeground(new Color(187, 187, 187));
        jLabel_KodeBarang.setBounds(10, 50, 150, 30);
        jLabel_KodeBarang.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        jLabel_KodeBarang.setText("Kode Barang");
        jPanel_Content.add(jLabel_KodeBarang);

        // combobox kode barang
        comboBox_KodeBarang.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        comboBox_KodeBarang.setBackground(new Color(60, 63, 65));
        comboBox_KodeBarang.setForeground(new Color(187, 187, 187));
        comboBox_KodeBarang.setBounds(100, 52, 210, 25);
        isiCbKodeBarang();
        jPanel_Content.add(comboBox_KodeBarang);

        // Label alamat
        jLabel_JumlahPesanan.setForeground(new Color(187, 187, 187));
        jLabel_JumlahPesanan.setBounds(10, 80, 150, 30);
        jLabel_JumlahPesanan.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        jLabel_JumlahPesanan.setText("Jumlah Pesanan");
        jPanel_Content.add(jLabel_JumlahPesanan);
    
        // TextArea Alamat
        spinner_JumlahPesanan.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        spinner_JumlahPesanan.setBounds(100, 82, 210, 25);
        jPanel_Content.add(spinner_JumlahPesanan);

        // Button Add
        button_Add.setBackground(new Color(60, 63, 65));
        button_Add.setForeground(new Color(187, 187, 187));
        button_Add.setBounds(100, 112, 90, 20);
        button_Add.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        button_Add.setText("Add Data");
        jPanel_Content.add(button_Add);

        // Button refresh
        button_Refresh.setBackground(new Color(60, 63, 65));
        button_Refresh.setForeground(new Color(187, 187, 187));
        button_Refresh.setBounds(220, 112, 90, 20);
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

    void isiCbKodePelanggan(){
        List<Object> data = new Con_Pelanggan().get_All_Kode_Pelanggan(Akun.ID_Admin);
        Object[] parsedData = (Object[]) data.toArray(new Object[0]);
        
        comboBox_KodePelanggan.addItem("Pilih Kode Pelanggan");
        // Diisi dengan semua data di convert jadi string
        for (int i = 0; i < parsedData.length; i++) {
            comboBox_KodePelanggan.addItem(parsedData[i].toString());
        }
    }

    void isiCbKodeBarang(){
        List<Object> data = new Con_Barang().get_All_KodeBarang(Akun.ID_Admin);
        Object[] parsedData = (Object[]) data.toArray(new Object[0]);
        
        comboBox_KodeBarang.addItem("Pilih Kode Barang");
        // Diisi dengan semua data di convert jadi string
        for (int i = 0; i < parsedData.length; i++) {
            comboBox_KodeBarang.addItem(parsedData[i].toString());
        }
    }

    void refresh(){
        comboBox_KodePelanggan.removeAllItems();
        comboBox_KodeBarang.removeAllItems();
        isiCbKodePelanggan();
        isiCbKodeBarang();
        comboBox_KodePelanggan.setSelectedIndex(0);
        comboBox_KodeBarang.setSelectedIndex(0);
        spinner_JumlahPesanan.setValue(0);
    }

    boolean kosong(){
        boolean kosong = false;
        for (int i = data_Yang_Kosong.size() - 1; i >= 0; i--) {
            data_Yang_Kosong.remove(i);
        }

        if(comboBox_KodePelanggan.getSelectedIndex() == 0){
            kosong = true;
            data_Yang_Kosong.add("Kode Pelanggan");
        }
        if(comboBox_KodeBarang.getSelectedIndex() == 0){
            kosong = true;
            data_Yang_Kosong.add("Kode Barang");
        }
        if((Integer) spinner_JumlahPesanan.getValue() == 0){
            kosong = true;
            data_Yang_Kosong.add("Jumlah Pesanan");
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
                String Kode_Pelanggan = comboBox_KodePelanggan.getSelectedItem().toString();
                String Kode_Barang = comboBox_KodeBarang.getSelectedItem().toString();
                int Jumlah_Pesanan = (Integer) spinner_JumlahPesanan.getValue();

                String StatusKurangiBarang = new Con_Barang().kurang_Stok(Jumlah_Pesanan, Akun.ID_Admin, Kode_Barang);

                // Jika berhasil
                if(StatusKurangiBarang.equals("Data berhasil diubah/dikurangi!")){
                    Calendar cal = Calendar.getInstance();
                    java.sql.Timestamp sqlDate = new java.sql.Timestamp(cal.getTimeInMillis());

                    String statusAddLaporan = new Con_Laporan().add_Laporan(Kode_Pelanggan, Kode_Barang, Jumlah_Pesanan, sqlDate, Akun.ID_Admin);
                    if(statusAddLaporan.equals("Data berhasil dimasukkan!")){
                        JOptionPane.showMessageDialog( 
                            null, 
                            statusAddLaporan, 
                            "Edit Biodata Berhasil",         
                            JOptionPane.INFORMATION_MESSAGE);

                        refresh();
                    } else { // Gagal add laporan
                        JOptionPane.showMessageDialog(null, statusAddLaporan, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else { // Jika gagal kurangi barang
                    JOptionPane.showMessageDialog(null, StatusKurangiBarang, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}

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
import java.util.List;

import koneksi.Con_Laporan;
import saved_authentication.Akun;


// IGNORE MASIH WIP BELUM DIAPA2IN
public class Frame_Delete_Transaksi extends JFrame implements ActionListener {
    JPanel jPanel_Header = new JPanel();
    JPanel jPanel_Content = new JPanel();
    
    JLabel jLabel_Judul = new JLabel();

    JLabel jLabel_KodePesanan = new JLabel();
    JLabel jLabel_KodePelanggan = new JLabel();
    JLabel jLabel_KodeBarang = new JLabel();
    JLabel jLabel_TanggalPesan = new JLabel();

    JComboBox<String> comboBox_KodePesanan = new JComboBox<>();
    JComboBox<String> comboBox_KodePelanggan = new JComboBox<>();
    JComboBox<String> comboBox_KodeBarang = new JComboBox<>();
    JTextField jTextField_TanggalPesan = new JTextField();
    
    JButton button_Delete = new JButton();
    JButton button_Refresh = new JButton();

    ArrayList<String> data_Yang_Kosong = new ArrayList<>();

    public Frame_Delete_Transaksi(){
        // Setting frame
        this.setSize(355, 300);
        this.setTitle("Delete Laporan Transaksi");
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
        jLabel_Judul.setText("DELETE LAPORAN TRANSAKSI");
        jLabel_Judul.setBounds(30, 10, 280, 30);
        jPanel_Header.add(jLabel_Judul);

        // Label Kode pesanan
        jLabel_KodePesanan.setForeground(new Color(187, 187, 187));
        jLabel_KodePesanan.setBounds(10, 20, 150, 30);
        jLabel_KodePesanan.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        jLabel_KodePesanan.setText("Pilih Kode Pesanan");
        jPanel_Content.add(jLabel_KodePesanan);

        // combobox kode pesanan
        comboBox_KodePesanan.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        comboBox_KodePesanan.setBackground(new Color(60, 63, 65));
        comboBox_KodePesanan.setForeground(new Color(187, 187, 187));
        comboBox_KodePesanan.setBounds(115, 22, 195, 25);
        isiCbKodePesanan();
        jPanel_Content.add(comboBox_KodePesanan);

        // Label kode pelanggan
        jLabel_KodePelanggan.setForeground(new Color(187, 187, 187));
        jLabel_KodePelanggan.setBounds(10, 50, 150, 30);
        jLabel_KodePelanggan.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        jLabel_KodePelanggan.setText("Kode Pelanggan");
        jPanel_Content.add(jLabel_KodePelanggan);

        // combobox kode pelanggan
        comboBox_KodePelanggan.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        comboBox_KodePelanggan.setBackground(new Color(60, 63, 65));
        comboBox_KodePelanggan.setForeground(new Color(187, 187, 187));
        comboBox_KodePelanggan.setBounds(115, 52, 195, 25);
        comboBox_KodePelanggan.setOpaque(false);
        jPanel_Content.add(comboBox_KodePelanggan);

        // Label kode barang
        jLabel_KodeBarang.setForeground(new Color(187, 187, 187));
        jLabel_KodeBarang.setBounds(10, 80, 150, 30);
        jLabel_KodeBarang.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        jLabel_KodeBarang.setText("Kode Barang");
        jPanel_Content.add(jLabel_KodeBarang);
    
        // combobox kode barang
        comboBox_KodeBarang.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        comboBox_KodeBarang.setBackground(new Color(60, 63, 65));
        comboBox_KodeBarang.setForeground(new Color(187, 187, 187));
        comboBox_KodeBarang.setBounds(115, 82, 195, 25);
        comboBox_KodeBarang.setOpaque(false);
        jPanel_Content.add(comboBox_KodeBarang);

        // Label alamat
        jLabel_TanggalPesan.setForeground(new Color(187, 187, 187));
        jLabel_TanggalPesan.setBounds(10, 110, 150, 30);
        jLabel_TanggalPesan.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        jLabel_TanggalPesan.setText("Tanggal Pesanan");
        jPanel_Content.add(jLabel_TanggalPesan);

        // textfield tanggal pesanan
        jTextField_TanggalPesan.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        jTextField_TanggalPesan.setBackground(new Color(60, 63, 65));
        jTextField_TanggalPesan.setForeground(new Color(187, 187, 187));
        jTextField_TanggalPesan.setBounds(115, 112, 195, 25);
        jTextField_TanggalPesan.setEditable(false);
        jTextField_TanggalPesan.setText("");
        jPanel_Content.add(jTextField_TanggalPesan);

        // Button Add
        button_Delete.setBackground(new Color(60, 63, 65));
        button_Delete.setForeground(new Color(187, 187, 187));
        button_Delete.setBounds(115, 142, 95, 20);
        button_Delete.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        button_Delete.setText("Delete Data");
        jPanel_Content.add(button_Delete);

        // Button refresh
        button_Refresh.setBackground(new Color(60, 63, 65));
        button_Refresh.setForeground(new Color(187, 187, 187));
        button_Refresh.setBounds(235, 142, 75, 20);
        button_Refresh.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        button_Refresh.setText("Refresh");
        jPanel_Content.add(button_Refresh);

        // Action listener button
        comboBox_KodePesanan.addActionListener(this);
        button_Delete.addActionListener(this);
        button_Refresh.addActionListener(this);

        // Add panel header dan konten
        this.add(jPanel_Header);
        this.add(jPanel_Content);

        // Setvisible
        this.setVisible(true);
    }

    void isiCbKodePesanan(){
        List<Object> data = new Con_Laporan().get_All_KodePesanan(Akun.ID_Admin);
        Object[] parsedData = (Object[]) data.toArray(new Object[0]);
        
        comboBox_KodePesanan.addItem("Pilih Kode Transaksi");
        // Diisi dengan semua data di convert jadi string
        for (int i = 0; i < parsedData.length; i++) {
            comboBox_KodePesanan.addItem(parsedData[i].toString());
        }
    }

    void isiData(){
        if(comboBox_KodePesanan.getSelectedIndex() == 0){
            refresh();
        } else {
            List<Object> data = new Con_Laporan().get_LaporanPesananByKode(Akun.ID_Admin, comboBox_KodePesanan.getSelectedItem().toString());
            Object[] parsedData = (Object[]) data.toArray(new Object[0]);
            
            comboBox_KodePelanggan.removeAllItems();
            comboBox_KodeBarang.removeAllItems();

            comboBox_KodePelanggan.addItem(parsedData[0].toString());
            comboBox_KodeBarang.addItem(parsedData[1].toString());
            jTextField_TanggalPesan.setText(parsedData[2].toString());
        }
    }

    void refresh(){
        for(ActionListener act : comboBox_KodePesanan.getActionListeners()){
            comboBox_KodePesanan.removeActionListener(act);
        }
        comboBox_KodePesanan.removeAllItems();
        comboBox_KodePelanggan.removeAllItems();
        comboBox_KodeBarang.removeAllItems();
        jTextField_TanggalPesan.setText("");
        isiCbKodePesanan();
        comboBox_KodePesanan.setSelectedIndex(0);
        comboBox_KodePesanan.addActionListener(this);
    }

    boolean kosong(){
        boolean kosong = false;
        for (int i = data_Yang_Kosong.size() - 1; i >= 0; i--) {
            data_Yang_Kosong.remove(i);
        }

        if(comboBox_KodePesanan.getSelectedIndex() == 0){
            kosong = true;
            data_Yang_Kosong.add("Kode Pelanggan");
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
                String Kode_Pesanan = comboBox_KodePesanan.getSelectedItem().toString();

                String statusHapus = new Con_Laporan().delete_Laporan(Kode_Pesanan, Akun.ID_Admin);

                if(statusHapus.equals("Laporan Berhasil Dihapus!")){
                    JOptionPane.showMessageDialog( 
                        null, 
                        statusHapus, 
                        "Hapus Laporan Berhasil",         
                        JOptionPane.INFORMATION_MESSAGE);
                    
                    refresh();
                } else {
                    JOptionPane.showMessageDialog(null, statusHapus, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else
        if(ae.getSource().equals(comboBox_KodePesanan)){
            isiData();
        }
    }
}

/**
 * Property of KELOMPOK-5 PL Kelas 2A
 * Author1: Fauzan Farhan Antoro
 * Author2: Alfanisa Safvira
 * Author3: Daffa Fawwaz Syadad
 */
package panels;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;

public class Panel_LaporanTransaksi extends JPanel implements ActionListener {
    JLabel jLabel_judul = new JLabel();
    JLabel jLabel_search_TbAtas = new JLabel();
    JLabel jLabel_search_TbBawah = new JLabel();
    JLabel jLabel_kolom_TbAtas = new JLabel();
    JLabel jLabel_kolom_TbBawah = new JLabel();
    JLabel jLabel_mode_TbBawah = new JLabel();

    JTextField jField_SearchTbAtas = new JTextField();
    JTextField jField_SearchTbBawah = new JTextField();

    JComboBox<String> jBox_Kolom_TbAtas = new JComboBox<>();
    JComboBox<String> jBox_Kolom_TbBawah = new JComboBox<>();
    JComboBox<String> jBox_Mode_TbBawah = new JComboBox<>();

    // Table
    // JTable

    JScrollPane spaneTbAtas = new JScrollPane();
    JScrollPane spaneTbBawah = new JScrollPane();
    TableRowSorter<DefaultTableModel> sorterTbAtas;
    TableRowSorter<DefaultTableModel> sorterTbBawah;

    Object currentWindow = this;

    // Kolomnya apa saja
    String kolomTbAtas[] = {"Kode Pesanan", "Kode Pelanggan", "Kode Barang", "Harga Barang", "Jumlah", "Total", "Tanggal"};
    String kolomTbBawah[] = {"Tanggal", "Total Pendapatan", "Total Pesanan"};

    public Panel_LaporanTransaksi(){
        this.setLayout(null);
        this.setBounds(80, 70, 1120, 630);
        this.setBackground(new Color(24, 40, 44));
        
        jLabel_judul.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jLabel_judul.setForeground(Color.WHITE);
        jLabel_judul.setBounds(20, 20, 220, 20);
        jLabel_judul.setText("Tabel Laporan Transaksi");
        this.add(jLabel_judul);

        jLabel_search_TbAtas.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        jLabel_search_TbAtas.setForeground(Color.WHITE);
        jLabel_search_TbAtas.setBounds(22, 60, 40, 20);
        jLabel_search_TbAtas.setText("Search");
        this.add(jLabel_search_TbAtas);

        jField_SearchTbAtas.setBackground(new Color(69, 73, 74));
        jField_SearchTbAtas.setForeground(Color.WHITE);
        jField_SearchTbAtas.setBounds(70, 60, 157, 20);
        jField_SearchTbAtas.setText("");
        jField_SearchTbAtas.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        this.add(jField_SearchTbAtas);

        jLabel_kolom_TbAtas.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        jLabel_kolom_TbAtas.setForeground(Color.WHITE);
        jLabel_kolom_TbAtas.setBounds(237, 60, 40, 20);
        jLabel_kolom_TbAtas.setText("Kolom");
        this.add(jLabel_kolom_TbAtas);

        jBox_Kolom_TbAtas.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jBox_Kolom_TbAtas.setBackground(new Color(69, 73, 74));
        jBox_Kolom_TbAtas.setForeground(Color.WHITE);
        jBox_Kolom_TbAtas.setBounds(285, 60, 120, 20);
        this.add(jBox_Kolom_TbAtas);
        
        
        
    }

    @Override
    public void actionPerformed(ActionEvent ae){

    }
}

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import koneksi.Con_Laporan;
import panels.Panel_LaporanTransaksi;
import saved_authentication.Akun;
import tools.CreatePdf;

public class Frame_Export_Transaksi extends JFrame implements ActionListener {
    JPanel jPanel_Header = new JPanel();
    JPanel jPanel_Content = new JPanel();
    
    JLabel jLabel_Judul = new JLabel();

    JLabel jLabel_StartDate = new JLabel();
    JLabel jLabel_EndDate = new JLabel();

    JComboBox<String> comboBox_StartDate = new JComboBox<>();
    JComboBox<String> comboBox_EndDate = new JComboBox<>();
    
    JButton button_Export = new JButton();
    JButton button_ExportAll = new JButton();
    JButton button_Refresh = new JButton();

    ArrayList<String> data_Yang_Kosong = new ArrayList<>();

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        new Frame_Export_Transaksi();
    }

    public Frame_Export_Transaksi(){
        // Setting frame
        this.setSize(380, 255);
        this.setTitle("Export Laporan Transaksi");
        this.getContentPane().setBackground(new Color(60, 63, 65));
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setIconImage(new ImageIcon(getClass().getResource("/assets/icons8-database-50.png")).getImage());

        // Panel Header
        jPanel_Header.setLayout(null);
        jPanel_Header.setBackground(new Color(18, 18, 18));
        jPanel_Header.setBounds(10, 10, 340, 50);
        
        // Panel Konten
        jPanel_Content.setLayout(null);
        jPanel_Content.setBackground(new Color(24, 40, 44));
        jPanel_Content.setBounds(10, 70, 340, 135);
        
        // Label Judul
        jLabel_Judul.setForeground(Color.WHITE);
        jLabel_Judul.setFont((new Font("Segoe UI", Font.BOLD, 18)));
        jLabel_Judul.setText("EXPORT LAPORAN TRANSAKSI");
        jLabel_Judul.setBounds(38, 10, 280, 30);
        jPanel_Header.add(jLabel_Judul);

        // Label Kode pesanan
        jLabel_StartDate.setForeground(new Color(187, 187, 187));
        jLabel_StartDate.setBounds(10, 20, 150, 30);
        jLabel_StartDate.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        jLabel_StartDate.setText("Tanggal Awal");
        jPanel_Content.add(jLabel_StartDate);

        // combobox kode pesanan
        comboBox_StartDate.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        comboBox_StartDate.setBackground(new Color(60, 63, 65));
        comboBox_StartDate.setForeground(new Color(187, 187, 187));
        comboBox_StartDate.setBounds(115, 22, 210, 25);
        jPanel_Content.add(comboBox_StartDate);

        // Label kode pelanggan
        jLabel_EndDate.setForeground(new Color(187, 187, 187));
        jLabel_EndDate.setBounds(10, 50, 150, 30);
        jLabel_EndDate.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        jLabel_EndDate.setText("Tanggal Akhir");
        jPanel_Content.add(jLabel_EndDate);

        // combobox kode pelanggan
        comboBox_EndDate.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        comboBox_EndDate.setBackground(new Color(60, 63, 65));
        comboBox_EndDate.setForeground(new Color(187, 187, 187));
        comboBox_EndDate.setBounds(115, 52, 210, 25);
        comboBox_EndDate.setOpaque(false);
        jPanel_Content.add(comboBox_EndDate);

        // Isi cb
        isiCBTanggal();

        // Button Export
        button_Export.setBackground(new Color(60, 63, 65));
        button_Export.setForeground(new Color(187, 187, 187));
        button_Export.setBounds(115, 82, 115, 20);
        button_Export.setFont((new Font("Segoe UI", Font.PLAIN, 11)));
        button_Export.setText("Export Selected");
        jPanel_Content.add(button_Export);

        // Button Export ALL
        button_ExportAll.setBackground(new Color(60, 63, 65));
        button_ExportAll.setForeground(new Color(187, 187, 187));
        button_ExportAll.setBounds(235, 82, 90, 20);
        button_ExportAll.setFont((new Font("Segoe UI", Font.PLAIN, 11)));
        button_ExportAll.setText("Export All");
        jPanel_Content.add(button_ExportAll);

        // Button refresh
        button_Refresh.setBackground(new Color(60, 63, 65));
        button_Refresh.setForeground(new Color(187, 187, 187));
        button_Refresh.setBounds(115, 107, 210, 20);
        button_Refresh.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        button_Refresh.setText("Refresh");
        jPanel_Content.add(button_Refresh);

        // Action listener button
        button_Export.addActionListener(this);
        button_ExportAll.addActionListener(this);
        button_Refresh.addActionListener(this);

        // Add panel header dan konten
        this.add(jPanel_Header);
        this.add(jPanel_Content);

        // Setvisible
        this.setVisible(true);
    }

    void refresh(){
        comboBox_StartDate.removeAllItems();
        comboBox_EndDate.removeAllItems();
        isiCBTanggal();
        comboBox_StartDate.setSelectedIndex(0);
        comboBox_EndDate.setSelectedIndex(0);
    }

    boolean kosong(){
        boolean kosong = false;
        for (int i = data_Yang_Kosong.size() - 1; i >= 0; i--) {
            data_Yang_Kosong.remove(i);
        }

        if(comboBox_StartDate.getSelectedIndex() == 0){
            kosong = true;
            data_Yang_Kosong.add("Tanggal Awal");
        }
        if(comboBox_EndDate.getSelectedIndex() == 0){
            kosong = true;
            data_Yang_Kosong.add("Tanggal Akhir");
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
    
    void isiCBTanggal(){
        List<Object> data = new Con_Laporan().get_LaporanPendapatan_ByDateExport(Akun.ID_Admin);
        Object[] parsedData = (Object[]) data.toArray(new Object[0]);

        comboBox_StartDate.addItem("Pilih Tanggal Awal");
        comboBox_EndDate.addItem("Pilih Tanggal Akhir");
        
        for (int i = 0; i < parsedData.length; i = i + 3) {
            // Tanggal doang ini
            comboBox_StartDate.addItem(parsedData[i].toString());
            comboBox_EndDate.addItem(parsedData[i].toString());
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource().equals(button_Refresh)) {
            refresh();
        } else
        if(ae.getSource().equals(button_Export)) {
            // Cek kosong
            if(kosong()){
                kosongPopup();
            } else {
                // Listnya dari yg paling kecil tanggal ke paling besar, jadi ceknya gini
                if(comboBox_StartDate.getSelectedIndex() > comboBox_EndDate.getSelectedIndex()){
                    JOptionPane.showMessageDialog(null, "Tanggal Awal Tidak Bisa Lebih Kecil Dari Tanggal Akhir!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Dapatkan datanya
                String tgl_Awal = comboBox_StartDate.getSelectedItem().toString();
                String tgl_Akhir = comboBox_EndDate.getSelectedItem().toString();

                 // Replace itu biar ga ngebingungin periodenya, diganti dari yg - jadi /
                String periode = "(" + tgl_Awal.replaceAll("-", "/") + ") - (" + tgl_Akhir.replaceAll("-", "/") + ")";

                int opts = JOptionPane.showConfirmDialog(null, "Export Data Tabel Laporan Pendapatan & Laporan Pesanan?\n*Data Periode " + periode, "Export Confirmation", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);

                if(opts == JOptionPane.OK_OPTION){
                    String statusExport = new CreatePdf().exportSelectedDateToPdf(tgl_Awal, tgl_Akhir, periode); 

                    if(statusExport.equals("Laporan Berhasil Di-export ke PDF!")){
                        JOptionPane.showMessageDialog( 
                            null, 
                            statusExport + "\nMode: Export Periode Tertentu", 
                            "Export Laporan Berhasil",         
                            JOptionPane.INFORMATION_MESSAGE);
                        
                        refresh();
                        Panel_LaporanTransaksi.refreshAll();
                    } else {
                        JOptionPane.showMessageDialog(null, statusExport, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } else
        if(ae.getSource().equals(button_ExportAll)){
            List<Object> data = new Con_Laporan().get_LaporanPendapatan_ByDate(Akun.ID_Admin);
            Object[] parsedData = (Object[]) data.toArray(new Object[0]);
            // 0 1 2 -> Tgl awal, yg akhirnya juga ngikutin 
            String periode = "(" + parsedData[0].toString() + "/" + parsedData[1].toString() + "/" + parsedData[2].toString() + ") - (" 
                           + parsedData[parsedData.length - 5] + "/" + parsedData[parsedData.length - 4].toString() + "/" + parsedData[parsedData.length - 3].toString() + ")";
            
            int opts = JOptionPane.showConfirmDialog(null, "Export Semua Data Tabel Laporan Pendapatan & Laporan Pesanan?\n*Data Periode " + periode, "Export Confirmation", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            if(opts == JOptionPane.OK_OPTION){
                String statusExport = new CreatePdf().exportAllToPdf(periode);
                if(statusExport.equals("Laporan Berhasil Di-export ke PDF!")){
                    JOptionPane.showMessageDialog( 
                        null, 
                        statusExport + "\nMode: Export Semua Data Tabel Laporan", 
                        "Export Laporan Berhasil",         
                        JOptionPane.INFORMATION_MESSAGE);
                    
                    refresh();
                    Panel_LaporanTransaksi.refreshAll();
                } else {
                    JOptionPane.showMessageDialog(null, statusExport, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}

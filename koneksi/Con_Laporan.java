/**
 * Property of KELOMPOK-5 PL Kelas 2A
 * Author1: Fauzan Farhan Antoro
 * Author2: Alfanisa Safvira
 * Author3: Daffa Fawwaz Syadad
 */
package koneksi;

import java.sql.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Con_Laporan {
    Connection con = null;

    // CREATE
    /* Disini dicari dulu harganya abis itu diproses langsung didalam totalnya */
    public String add_Laporan(String Kode_Pelanggan, String Kode_Barang, int Jumlah_Pesanan, Date Tanggal_Pesanan, String ID_Admin){
        String status;
        try {
            con = new SQLConnect().getConSQL();

            // Get harga lalu hitung harga total
            PreparedStatement pr_GetHarga = con.prepareStatement("SELECT Harga_Barang FROM Barang WHERE ID_Admin = ? AND Kode_Barang = ?");
            pr_GetHarga.setString(1, ID_Admin);
            pr_GetHarga.setString(2, Kode_Barang);

            ResultSet rs_GetHarga = pr_GetHarga.executeQuery();
            rs_GetHarga.next();
            int harga_Barang = rs_GetHarga.getInt("Harga_Barang");
            int hargaTotal = harga_Barang * Jumlah_Pesanan;

            // Masukkan data
            String args = "INSERT INTO Laporan_Pesanan(Kode_Pelanggan,Kode_Barang,Harga_Barang_Pesanan,Jumlah_Pesanan,Total_Harga_Pesanan,Tanggal_Pesanan,ID_Admin) values(?,?,?,?,?,?,?)";
            PreparedStatement pr_LaporanPesanan = con.prepareStatement(args);
            pr_LaporanPesanan.setString(1, Kode_Pelanggan);
            pr_LaporanPesanan.setString(2, Kode_Barang);
            pr_LaporanPesanan.setInt(3, harga_Barang);
            pr_LaporanPesanan.setInt(4, Jumlah_Pesanan);
            pr_LaporanPesanan.setInt(5, hargaTotal);
            pr_LaporanPesanan.setDate(6, Tanggal_Pesanan);
            pr_LaporanPesanan.setString(7, ID_Admin);

            int statusCode = pr_LaporanPesanan.executeUpdate();
            if(statusCode == 0){
                status = "Data Gagal Disimpan!";
            } else {
                status = "Data berhasil dimasukkan!";
            }
        } catch (SQLException e) {
            if(e.getMessage().contains("INSERT statement conflicted with the FOREIGN KEY") && e.getMessage().contains("Kode_Pelanggan")){
                status = "Kode Yang Dimasukkan Tidak Ada di Kode Pelanggan!";
            } else
            if(e.getMessage().contains("INSERT statement conflicted with the FOREIGN KEY") && e.getMessage().contains("Kode_Barang")){
                status = "Kode Yang Dimasukkan Tidak Ada di Kode Barang!";
            } else {
                status = e.getMessage();
            }
        } finally {
            try { con.close(); } catch (SQLException e) { /* Ignored */ }
        }

        return status;
    }

    // READ
    /* Untuk dapat laporanPesanaan yaitu kode pesanan, kode_pelanggan, kode_barang, harga barang, jumlah pesaanan, total harga, tgl pesanan */ 
    public List<Object> get_LaporanPesanan(String ID_Admin){
        List<Object> dataList = new ArrayList<>();
        try {
            con = new SQLConnect().getConSQL();
            PreparedStatement pr = con.prepareStatement("SELECT * FROM Laporan_Pesanan WHERE ID_Admin = ?");
            pr.setString(1, ID_Admin);

            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                String Kode_Pesanan = rs.getString("Kode_Pesanan").trim();
                String Kode_Pelanggan = rs.getString("Kode_Pelanggan").trim();
                String Kode_Barang = rs.getString("Kode_Barang").trim();
                int Harga_Barang = rs.getInt("Harga_Barang_Pesanan");
                int Jumlah_Pesanan = rs.getInt("Harga_Barang_Pesanan");
                int Total_Harga_Pesanan = rs.getInt("Total_Harga_Pesanan");
                Date Tanggal_Pesanan = rs.getDate("Tanggal_Pesanan");

                Object[] dataArr = { Kode_Pesanan, Kode_Pelanggan, Kode_Barang, Harga_Barang, Jumlah_Pesanan, Total_Harga_Pesanan, Tanggal_Pesanan };
                Collections.addAll(dataList, dataArr);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try { con.close(); } catch (SQLException e) { /* Ignored */ }
        }

        return dataList;
    }

    public List<Object> get_All_KodePesanan(String ID_Admin){
        List<Object> dataList = new ArrayList<>();
        try {
            con = new SQLConnect().getConSQL();
            PreparedStatement pr = con.prepareStatement("SELECT * FROM Laporan_Pesanan WHERE ID_Admin = ?");
            pr.setString(1, ID_Admin);

            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                String Kode_Pesanan = rs.getString("Kode_Pesanan").trim();

                Object[] dataArr = { Kode_Pesanan };
                Collections.addAll(dataList, dataArr);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try { con.close(); } catch (SQLException e) { /* Ignored */ }
        }

        return dataList;
    }

    /* Untuk laporan pendapatan saja yaitu kode, total, dan tanggal */
    public List<Object> get_LaporanPendapatan(String ID_Admin){
        List<Object> dataList = new ArrayList<>();
        try {
            con = new SQLConnect().getConSQL();
            PreparedStatement pr = con.prepareStatement("SELECT * FROM Laporan_Pesanan WHERE ID_Admin = ?");
            pr.setString(1, ID_Admin);

            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                String Kode_Pesanan = rs.getString("Kode_Pesanan").trim();
                int Total_Harga = rs.getInt("Total_Harga_Pesanan");
                Date Tanggal_Pesanan = rs.getDate("Tanggal_Pesanan");

                Object[] dataArr = { Kode_Pesanan, Total_Harga, Tanggal_Pesanan };
                Collections.addAll(dataList, dataArr);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try { con.close(); } catch (SQLException e) { /* Ignored */ }
        }

        return dataList;
    }

    /* Untuk laporan pendapatan saja yang digrup dengan tanggal, jadi hanya muncul tgl pesanan dan total harganya */
    public List<Object> get_LaporanPendapatan_ByDate(String ID_Admin){
        List<Object> dataList = new ArrayList<>();
        try {
            con = new SQLConnect().getConSQL();
            PreparedStatement pr = con.prepareStatement("SELECT Tanggal_Pesanan, SUM(Total_Harga_Pesanan) Total FROM Laporan_Pesanan WHERE ID_Admin = ? GROUP BY Tanggal_Pesanan");
            pr.setString(1, ID_Admin);

            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                int Total_Harga = rs.getInt("Total_Harga_Pesanan");
                Date Tanggal_Pesanan = rs.getDate("Tanggal_Pesanan");

                Object[] dataArr = { Total_Harga, Tanggal_Pesanan };
                Collections.addAll(dataList, dataArr);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try { con.close(); } catch (SQLException e) { /* Ignored */ }
        }

        return dataList;
    }

    // UPDATE
    /* TIDAK ADA UPDATE UNTUK TRANSAKSI */

    // Delete laporan
    /* Apabila kategori, barang, atau pelanggan dihapus maka tidak berdampak pada laporan. Laporan terpisah walau tetap ada relasi
       Laporan harus dihapus sendiri terpisah dari tabel lain karena dia berisi catatan */
    public String delete_Laporan(String Kode_Pesanan, String ID_Admin){
        String status;
        try {
            PreparedStatement pr_Del_Laporan = con.prepareStatement("DELETE Laporan_Pesanan WHERE ID_Admin=? AND Kode_Pesanan=?");
            pr_Del_Laporan.setString(1, ID_Admin);
            pr_Del_Laporan.setString(2, Kode_Pesanan);
            int statusCode = pr_Del_Laporan.executeUpdate();

            if(statusCode == 0){
                status = "Laporan gagal dihapus!";
            } else {
                status = "Laporan Berhasil Dihapus!";
            }

        } catch (SQLException e) {
            // Jika error berarti gagal
            status = e.getMessage();
        } finally {
            try { con.close(); } catch (SQLException e) { /* Ignored */ }
        }

        return status;
    }
}
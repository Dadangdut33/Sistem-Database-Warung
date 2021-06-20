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

public class Con_Barang {
    Connection con = null;
    // CREATE
    public String add_Barang(String Kode_Kategori, String Nama_Barang, int Harga_Barang, int Stok_Barang, String ID_Admin){
        String status;
        try {
            con = new SQLConnect().getConSQL();
            PreparedStatement pr = con.prepareStatement("INSERT INTO Barang (Kode_Kategori,Nama_Barang,Harga_Barang,Stok_Barang,ID_Admin) values (?,?,?,?,?)");
            pr.setString(1, Kode_Kategori);
            pr.setString(2, Nama_Barang);
            pr.setInt(3, Harga_Barang);
            pr.setInt(4, Stok_Barang);
            pr.setString(5, ID_Admin);

            int i = pr.executeUpdate();
            if(i == 0){
                status = "Barang Gagal Ditambah!";
            } else {
                status = "Barang Berhasil Ditambah!";
            }
        } catch (SQLException e) {
            status = e.getMessage();
            if(status.contains("truncated")){
                status = "Data Yang Dimasukkan Terlalu Panjang!";
            }
        } finally {
            try { con.close(); } catch (SQLException e) { /* Ignored */ }
        }

        return status;
    }

    // READ
    public List<Object> get_BarangTable(String ID_Admin){
        List<Object> dataList = new ArrayList<>();
        try {
            con = new SQLConnect().getConSQL();
            PreparedStatement pr = con.prepareStatement("SELECT * FROM Barang WHERE ID_Admin = ? ORDER BY ID_Barang");
            pr.setString(1, ID_Admin);

            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                String Kode_Kategori = rs.getString("Kode_Kategori").trim();
                String Kode_Barang = rs.getString("Kode_Barang").trim();
                String Nama_Barang = rs.getString("Nama_Barang").trim();
                int Harga_Barang = rs.getInt("Harga_Barang");
                int Stok_Barang = rs.getInt("Stok_Barang");
    
                Object[] dataArr = { Kode_Kategori, Kode_Barang, Nama_Barang, Harga_Barang, Stok_Barang };
                Collections.addAll(dataList, dataArr);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try { con.close(); } catch (SQLException e) { /* Ignored */ }
        }

        return dataList;
    }

    public List<Object> get_All_KodeBarang(String ID_Admin){
        List<Object> dataList = new ArrayList<>();
        try {
            con = new SQLConnect().getConSQL();
            PreparedStatement pr = con.prepareStatement("SELECT * FROM Barang WHERE ID_Admin = ? ORDER BY ID_Barang");
            pr.setString(1, ID_Admin);

            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                String Kode_Barang = rs.getString("Kode_Barang").trim();
    
                Object[] dataArr = { Kode_Barang };
                Collections.addAll(dataList, dataArr);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try { con.close(); } catch (SQLException e) { /* Ignored */ }
        }

        return dataList;
    }

    // Dapat NamaBarang
    public List<Object> get_BarangByKode(String Kode_Barang, String ID_Admin){
        List<Object> dataList = new ArrayList<>();
        try {
            con = new SQLConnect().getConSQL();
            PreparedStatement pr = con.prepareStatement("SELECT * FROM Barang WHERE ID_Admin = ? AND Kode_Barang = ?");
            pr.setString(1, ID_Admin);
            pr.setString(2, Kode_Barang);

            ResultSet rs = pr.executeQuery();
            rs.next();
            String Nama_Barang = rs.getString("Nama_Barang").trim();
            int Harga_Barang = rs.getInt("Harga_Barang");
            int Stok_Barang = rs.getInt("Stok_Barang");
    
            Object[] dataArr = { Nama_Barang, Harga_Barang, Stok_Barang };
            Collections.addAll(dataList, dataArr);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try { con.close(); } catch (SQLException e) { /* Ignored */ }
        }

        return dataList;
    }

    // Dapat stok yang sekarang
    public int get_Stok(String ID_Admin, String Kode_Barang){
        int stok = 0;
        try {
            con = new SQLConnect().getConSQL();
            PreparedStatement pr_GetStok = con.prepareStatement("SELECT * FROM Barang WHERE ID_Admin = ? AND Kode_Barang = ?");
            pr_GetStok.setString(1, ID_Admin);
            pr_GetStok.setString(2, Kode_Barang); 

            ResultSet rs_Stok = pr_GetStok.executeQuery();
            rs_Stok.next();

            stok = rs_Stok.getInt("Stok_Barang");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try { con.close(); } catch (SQLException e) { /* Ignored */ }
        }

        return stok;
    }

    // UPDATE
    /* Proses hitung stok */
    public String kurang_Stok(int Jumlah_Pesanan, String ID_Admin, String Kode_Barang){
        String status;
        try {
            int curr_Stok = new Con_Barang().get_Stok(ID_Admin, Kode_Barang);
            if(curr_Stok < Jumlah_Pesanan) {
                status = "Stok Tidak Cukup!";
            } else {
                int Stok_Baru = curr_Stok - Jumlah_Pesanan;
                status = new Con_Barang().proses_Kurang_Stok(Stok_Baru, ID_Admin, Kode_Barang);
            }
        } catch (Exception e) {
            status = e.getMessage();
        } 

        return status;
    }

    public String proses_Kurang_Stok(int Stok_Baru, String ID_Admin, String Kode_Barang){
        String status;
        try {
            con = new SQLConnect().getConSQL();
            PreparedStatement pr_UpdateStok = con.prepareStatement("UPDATE Barang SET Stok_Barang=? WHERE ID_Admin=? and Kode_Barang=?");
            pr_UpdateStok.setInt(1, Stok_Baru);
            pr_UpdateStok.setString(2, ID_Admin);
            pr_UpdateStok.setString(3, Kode_Barang);

            int res = pr_UpdateStok.executeUpdate();
            if(res == 0){
                status = "Data gagal diubah/dikurangi!";
            } else {
                status = "Data berhasil diubah/dikurangi!";
            }
        } catch (SQLException e) {
            status = e.getMessage();
        } finally {
            try { con.close(); } catch (SQLException e) { /* Ignored */ }
        }

        return status;
    }

    public String edit_Barang(String Nama_Barang, int Harga_Barang, int Stok_Barang, String ID_Admin, String Kode_Barang){
        String status;
        try {
            con = new SQLConnect().getConSQL();
            PreparedStatement pr = con.prepareStatement("UPDATE Barang SET Nama_Barang=?, Harga_Barang=?, Stok_Barang=? WHERE ID_Admin=? and Kode_Barang=?");
            pr.setString(1, Nama_Barang);
            pr.setInt(2, Harga_Barang);
            pr.setInt(3, Stok_Barang);
            pr.setString(4, ID_Admin);
            pr.setString(5, Kode_Barang);

            int res = pr.executeUpdate();
            if(res == 0){
                status = "Barang gagal diubah!";
            } else {
                status = "Barang berhasil diubah!";
            }
        } catch (SQLException e) {
            status = e.getMessage();
            if(status.contains("truncated")){
                status = "Data Yang Dimasukkan Terlalu Panjang!";
            }
        } finally {
            try { con.close(); } catch (SQLException e) { /* Ignored */ }
        }

        return status;
    }

    // DELETE
    public String delete_Barang(String Kode_Barang, String ID_Admin){
        String status;
        try {
            con = new SQLConnect().getConSQL();
            PreparedStatement pr_Del_Pelanggan = con.prepareStatement("DELETE Barang WHERE ID_Admin=? AND Kode_Barang=?");
            pr_Del_Pelanggan.setString(1, ID_Admin);
            pr_Del_Pelanggan.setString(2, Kode_Barang);
            int statusCode = pr_Del_Pelanggan.executeUpdate();

            if(statusCode == 0){
                status = "Barang gagal dihapus!";
            } else {
                status = "Barang Berhasil Dihapus!";
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

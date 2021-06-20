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

public class Con_Kategori {
    Connection con = null;
    // CREATE
    public String add_Kategori(String Kode_Kategori, String Nama_Kategori, String ID_Admin){
        String status;
        try {
            con = new SQLConnect().getConSQL();
            PreparedStatement pr = con.prepareStatement("INSERT INTO Kategori (Kode,Nama_Kategori,ID_Admin) values (?,?,?)");
            pr.setString(1, Kode_Kategori);
            pr.setString(2, Nama_Kategori);
            pr.setString(3, ID_Admin);

            int i = pr.executeUpdate();
            if(i == 0){
                status = "Data Kategori Gagal Ditambah!";
            } else {
                status = "Data Kategori Berhasil Ditambah!";
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
    public List<Object> get_KategoriTable(String ID_Admin){
        List<Object> dataList = new ArrayList<>();
        try {
            con = new SQLConnect().getConSQL();
            PreparedStatement pr = con.prepareStatement("SELECT * FROM Kategori WHERE ID_Admin = ? ORDER BY ID_Kategori");
            pr.setString(1, ID_Admin);

            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                String Kode_Kategori = rs.getString("Kode_Kategori").trim();
                String Nama_Kategori = rs.getString("Nama_Kategori").trim();

                Object[] dataArr = { Kode_Kategori, Nama_Kategori };
                Collections.addAll(dataList, dataArr);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try { con.close(); } catch (SQLException e) { /* Ignored */ }
        }

        return dataList;
    }

    public List<Object> get_All_KodeKategori(String ID_Admin){
        List<Object> dataList = new ArrayList<>();
        try {
            con = new SQLConnect().getConSQL();
            PreparedStatement pr = con.prepareStatement("SELECT * FROM Kategori WHERE ID_Admin = ? ORDER BY ID_Kategori");
            pr.setString(1, ID_Admin);

            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                String Kode_Kategori = rs.getString("Kode_Kategori").trim();

                Object[] dataArr = { Kode_Kategori };
                Collections.addAll(dataList, dataArr);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try { con.close(); } catch (SQLException e) { /* Ignored */ }
        }

        return dataList;
    }

    public List<Object> get_NamaKategoriByKode(String Kode_Kategori, String ID_Admin){
        List<Object> dataList = new ArrayList<>();
        try {
            con = new SQLConnect().getConSQL();
            PreparedStatement pr = con.prepareStatement("SELECT * FROM Kategori WHERE ID_Admin = ? and Kode_Kategori = ?");
            pr.setString(1, ID_Admin);
            pr.setString(2, Kode_Kategori);

            ResultSet rs = pr.executeQuery();
            rs.next();
            String Nama_Kategori = rs.getString("Nama_Kategori").trim();

            Object[] dataArr = { Nama_Kategori };
            Collections.addAll(dataList, dataArr);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try { con.close(); } catch (SQLException e) { /* Ignored */ }
        }

        return dataList;
    }

    // UPDATE
    public String edit_Kategori(String Nama_Kategori, String ID_Admin, String Kode_Kategori){
        String status;
        try {
            con = new SQLConnect().getConSQL();
            PreparedStatement pr = con.prepareStatement("UPDATE Kategori SET Nama_Kategori=? WHERE ID_Admin=? and Kode_Kategori=?");
            pr.setString(1, Nama_Kategori);
            pr.setString(2, ID_Admin);
            pr.setString(3, Kode_Kategori);

            int res = pr.executeUpdate();
            if(res == 0){
                status = "Kategori gagal diubah!";
            } else {
                status = "Kategori berhasil diubah!";
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
    /* Apabila delete kategori maka barang dikategorinya juga akan terdelete */
    public String delete_Kategori(String Kode_Kategori, String ID_Admin){
        String status;
        try {
            con = new SQLConnect().getConSQL();
            PreparedStatement pr_Del_Pelanggan = con.prepareStatement("DELETE Kategori WHERE ID_Admin=? AND Kode_Kategori=?");
            pr_Del_Pelanggan.setString(1, ID_Admin);
            pr_Del_Pelanggan.setString(2, Kode_Kategori);
            int statusCode = pr_Del_Pelanggan.executeUpdate();

            if(statusCode == 0){
                status = "Kategori gagal dihapus!";
            } else {
                status = "Kategori Berhasil Dihapus!";
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

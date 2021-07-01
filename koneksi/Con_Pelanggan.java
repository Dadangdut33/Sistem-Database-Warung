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

public class Con_Pelanggan {
    Connection con = null;

    public static ArrayList<String> StoredKodePLG = new ArrayList<String>();

    public static String searchPlgArr(String kode){
        String kodeGet = "";
        for(int i = 0; i < StoredKodePLG.size(); i++) {
            // Cek kode lokal
            // Kode lokal ada dari yg paling awal, jd dia genap
            // Selang seling, kode lokal, kode di sql
            if(i % 2 == 0){
                if(kode.equals(StoredKodePLG.get(i))){
                    kodeGet = StoredKodePLG.get(i+1);
                    break;
                }
            }
        }
        return kodeGet;
    }

    // CREATE
    public String add_Pelanggan(String Nama_Pelanggan, String Alamat_Pelanggan, String Telepon_Pelanggan, String ID_Admin){
        String status;
        try {
            con = new SQLConnect().getConSQL();
            PreparedStatement pr = con.prepareStatement("INSERT INTO Pelanggan(Nama_Pelanggan,Alamat_Pelanggan,Telepon_Pelanggan,ID_Admin) values (?,?,?,?)");
            pr.setString(1, Nama_Pelanggan);
            pr.setString(2, Alamat_Pelanggan);
            pr.setString(3, Telepon_Pelanggan);
            pr.setString(4, ID_Admin);

            int i = pr.executeUpdate();
            if(i == 0){
                status = "Data Pelanggan Gagal Ditambah!";
            } else {
                status = "Data Pelanggan Berhasil Ditambah!";
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
    public List<Object> get_PelangganTable(String ID_Admin){
        List<Object> dataList = new ArrayList<>();
        try {
            int x = 1;
            StoredKodePLG.clear();
            con = new SQLConnect().getConSQL();
            PreparedStatement pr = con.prepareStatement("SELECT * FROM Pelanggan WHERE ID_Admin = ? ORDER BY ID_Pelanggan");
            pr.setString(1, ID_Admin);

            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                // KARENA DIMINTA DARI 1, JADI
                // STORE LOKAL DATANYA T_T
                String Kode_Pelanggan = "PLG-" + x;
                StoredKodePLG.add("PLG-" + x);
                StoredKodePLG.add(rs.getString("Kode_Pelanggan").trim());
                
                
                String Nama_Pelanggan = rs.getString("Nama_Pelanggan").trim();
                String Alamat_Pelanggan = rs.getString("Alamat_Pelanggan").trim();
                String Telepon_Pelanggan = rs.getString("Telepon_Pelanggan").trim();
    
                String[] dataArr = { Kode_Pelanggan, Nama_Pelanggan, Alamat_Pelanggan, Telepon_Pelanggan };
                Collections.addAll(dataList, dataArr);
                x++;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try { con.close(); } catch (SQLException e) { /* Ignored */ }
        }

        return dataList;
    }

    public boolean dupeCheck(String namaDiCek, String alamatDiCek, String teleponDiCek, String ID_Admin){
        boolean isDupe = false;

        try {
            con = new SQLConnect().getConSQL();
            PreparedStatement pr = con.prepareStatement("SELECT * FROM Pelanggan WHERE ID_Admin = ? ORDER BY ID_Pelanggan");
            pr.setString(1, ID_Admin);

            ResultSet rs = pr.executeQuery();
            while (rs.next()) {

                String Nama_Pelanggan = rs.getString("Nama_Pelanggan").trim();
                String Alamat_Pelanggan = rs.getString("Alamat_Pelanggan").trim();
                String Telepon_Pelanggan = rs.getString("Telepon_Pelanggan").trim();

                if(namaDiCek.equals(Nama_Pelanggan) && alamatDiCek.equals(Alamat_Pelanggan) && teleponDiCek.equals(Telepon_Pelanggan)){
                    isDupe = true;
                    break;
                }

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try { con.close(); } catch (SQLException e) { /* Ignored */ }
        }

        return isDupe;
    }

    public List<Object> get_All_Kode_Pelanggan(String ID_Admin){
        List<Object> dataList = new ArrayList<>();
        try {
            int x = 1;
            StoredKodePLG.clear();
            con = new SQLConnect().getConSQL();
            PreparedStatement pr = con.prepareStatement("SELECT * FROM Pelanggan WHERE ID_Admin = ? ORDER BY ID_Pelanggan");
            pr.setString(1, ID_Admin);

            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                String Kode_Pelanggan = "PLG-" + x;
                
                StoredKodePLG.add(Kode_Pelanggan);
                StoredKodePLG.add(rs.getString("Kode_Pelanggan").trim());

                String[] dataArr = { Kode_Pelanggan };
                Collections.addAll(dataList, dataArr);
                x++;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try { con.close(); } catch (SQLException e) { /* Ignored */ }
        }

        return dataList;
    }

    public List<Object> get_PelangganByKode(String Kode_Pelanggan, String ID_Admin){
        List<Object> dataList = new ArrayList<>();
        try {
            con = new SQLConnect().getConSQL();
            PreparedStatement pr = con.prepareStatement("SELECT * FROM Pelanggan WHERE ID_Admin = ? AND Kode_Pelanggan = ?");
            pr.setString(1, ID_Admin);
            pr.setString(2, searchPlgArr(Kode_Pelanggan));

            ResultSet rs = pr.executeQuery();
            rs.next();

            String Nama_Pelanggan = rs.getString("Nama_Pelanggan").trim();
            String Alamat_Pelanggan = rs.getString("Alamat_Pelanggan").trim();
            String Telepon_Pelanggan = rs.getString("Telepon_Pelanggan").trim();

            String[] dataArr = { Nama_Pelanggan, Alamat_Pelanggan, Telepon_Pelanggan };
            Collections.addAll(dataList, dataArr);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try { con.close(); } catch (SQLException e) { /* Ignored */ }
        }

        return dataList;
    }

    // UPDATE
    public String edit_Pelanggan(String Nama_Pelanggan, String Alamat_Pelanggan, String Telepon_Pelanggan, String ID_Admin, String Kode_Pelanggan){
        String status;
        try {
            con = new SQLConnect().getConSQL();
            PreparedStatement pr = con.prepareStatement("UPDATE Pelanggan SET Nama_Pelanggan=?, Alamat_Pelanggan=?, Telepon_Pelanggan=? WHERE ID_Admin=? and Kode_Pelanggan=?");
            pr.setString(1, Nama_Pelanggan);
            pr.setString(2, Alamat_Pelanggan);
            pr.setString(3, Telepon_Pelanggan);
            pr.setString(4, ID_Admin);
            pr.setString(5, searchPlgArr(Kode_Pelanggan));

            int res = pr.executeUpdate();
            if(res == 0){
                status = "Data pelanggan gagal diubah!";
            } else {
                status = "Data pelanggan berhasil diubah!";
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
    public String delete_Pelanggan(String Kode_Pelanggan, String ID_Admin){
        String status;
        try {
            con = new SQLConnect().getConSQL();
            PreparedStatement pr_Del_Pelanggan = con.prepareStatement("DELETE Pelanggan WHERE ID_Admin=? AND Kode_Pelanggan=?");
            pr_Del_Pelanggan.setString(1, ID_Admin);
            pr_Del_Pelanggan.setString(2, searchPlgArr(Kode_Pelanggan));
            int statusCode = pr_Del_Pelanggan.executeUpdate();

            if(statusCode == 0){
                status = "Pelanggan gagal dihapus!";
            } else {
                status = "Pelanggan Berhasil Dihapus!";
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

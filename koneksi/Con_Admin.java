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

public class Con_Admin {
    Connection con = null;

    // LOGIN AKUN
    public boolean login(String ID_Admin, String Password){
        boolean loginSukses;
        try {
            con = new SQLConnect().getConSQL();
            PreparedStatement pr = con.prepareStatement("SELECT * FROM User_Admin WHERE ID_Admin = ? and Password = ?");
            pr.setString(1, ID_Admin);
            pr.setString(2, Password);

            ResultSet rs = pr.executeQuery();
            rs.next();

            // Kalau login benar yaitu saat ID Admin dan Password Admin sesuai 
            // Maka seharusnya saat mengambil data dibawah ini tidak error 
            rs.getString("ID_Admin").trim();
            rs.getString("Password").trim();

            loginSukses = true;
        } catch (SQLException e) {
            // Kalau error berarti gagal login
            loginSukses = false;
        } finally {
            try { con.close(); } catch (SQLException e) { /* Ignored */ }
        }

        return loginSukses;
    }

    // CREATE
    public String add_Akun(String ID_Admin, String Password, String Nama_Pemilik, String Nama_Toko, String Alamat_Toko, String Nomor_Telepon){
        String status;
        try {
            con = new SQLConnect().getConSQL();
            PreparedStatement pr = con.prepareStatement("INSERT INTO User_Admin(ID_Admin,Password,Nama_Pemilik,Nama_Toko,Alamat_Toko,Nomor_Telepon) values (?,?,?,?,?,?)");
            pr.setString(1, ID_Admin);
            pr.setString(2, Password);
            pr.setString(3, Nama_Pemilik);
            pr.setString(4, Nama_Toko);
            pr.setString(5, Alamat_Toko);
            pr.setString(6, Nomor_Telepon);

            int i = pr.executeUpdate();
            if(i == 0){
                status = "Akun gagal dibuat!";
            } else {
                status = "Akun berhasil dibuat!";
            }
        } catch (SQLException e) {
            if(e.getMessage().contains("duplicate")) {
                status = "ID sudah diambil!";
            } else {
                status = e.getMessage();
            }
        } finally {
            try { con.close(); } catch (SQLException e) { /* Ignored */ }
        }

        return status;
    }

    // READ
    /* Dapat akun */
    public List<Object> get_Akun(String ID_Admin, String Password){
        List<Object> dataList = new ArrayList<>();
        try {
            con = new SQLConnect().getConSQL();
            PreparedStatement pr = con.prepareStatement("SELECT * FROM User_Admin WHERE ID_Admin = ? and Password = ?");
            pr.setString(1, ID_Admin);
            pr.setString(2, Password);

            ResultSet rs = pr.executeQuery();
            rs.next();

            String Admin_ID = rs.getString("ID_Admin").trim();
            String password = rs.getString("Password").trim();
            String Nama_Pemilik = rs.getString("Nama_Pemilik").trim();
            String Nama_Toko = rs.getString("Nama_Toko").trim();
            String Alamat = rs.getString("Alamat_Toko").trim();
            String no_Telp = rs.getString("Nomor_Telepon").trim();

            String[] dataArr = { Admin_ID, password, Nama_Pemilik, Nama_Toko, Alamat, no_Telp };
            Collections.addAll(dataList, dataArr);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Akun tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
            String[] dataErr = { e.getMessage(), "Error" };
            Collections.addAll(dataList, dataErr);
        } finally {
            try { con.close(); } catch (SQLException e) { /* Ignored */ }
        }

        return dataList;
    }

    // UPDATE
    /* Edit akun, untuk ganti password ntar minta konfirmasi diakhir dengan showmessagedialog */
    public String ganti_Password(String PasswordBaru, String ID_Admin, String PasswordLama){
        String status;
        try {
            con = new SQLConnect().getConSQL();
            PreparedStatement pr = con.prepareStatement("UPDATE User_Admin SET Password=? WHERE ID_Admin=? and Password=?");
            pr.setString(1, PasswordBaru);
            pr.setString(2, ID_Admin);
            pr.setString(3, PasswordLama);

            int res = pr.executeUpdate();
            if(res == 0){
                status = "Password gagal diganti!";
            } else {
                status = "Password berhasil diganti!";
            }
        } catch (SQLException e) {
            status = e.getMessage();
        } finally {
            try { con.close(); } catch (SQLException e) { /* Ignored */ }
        }

        return status;
    }
    
    public String edit_Biodata(String ID_Admin, String Password, String Nama_Pemilik, String Nama_Toko, String Alamat_Toko, String Nomor_Telepon){
        String status;
        try {
            con = new SQLConnect().getConSQL();
            PreparedStatement pr = con.prepareStatement("UPDATE User_Admin SET Nama_Pemilik=?, Nama_Toko=?, Alamat_Toko=?, Nomor_Telepon=? WHERE ID_Admin=? and Password=?");
            pr.setString(1, Nama_Pemilik);
            pr.setString(2, Nama_Toko);
            pr.setString(3, Alamat_Toko);
            pr.setString(4, Nomor_Telepon);
            pr.setString(5, ID_Admin);
            pr.setString(6, Password);

            int res = pr.executeUpdate();
            if(res == 0){
                status = "Data gagal diubah!";
            } else {
                status = "Data berhasil diubah!";
            }
        } catch (SQLException e) {
            status = e.getMessage();
        } finally {
            try { con.close(); } catch (SQLException e) { /* Ignored */ }
        }

        return status;
    }

    // DELETE
    /* Disini langsung hapus, jadi bagusnya apabilaa dipakai adalah cek login dulu baru hapus */ 
    public String delete_Akun(String ID_Admin, String Password){
        String status;
        try {
            // Hapus tabel pelanggan
            con = new SQLConnect().getConSQL();
            PreparedStatement pr_Del_Pelanggan = con.prepareStatement("DELETE Pelanggan WHERE ID_Admin=?");
            pr_Del_Pelanggan.setString(1, ID_Admin);
            int statusCode_1 = pr_Del_Pelanggan.executeUpdate();
            
            // Hapus kategori maka barang ikut terhapus
            PreparedStatement pr_Del_Kategori = con.prepareStatement("DELETE Kategori WHERE ID_Admin=?");
            pr_Del_Kategori.setString(1, ID_Admin);
            int statusCode_2 = pr_Del_Kategori.executeUpdate();

            // Hapus laporan
            PreparedStatement pr_Del_Laporan = con.prepareStatement("DELETE Laporan_Pesanan WHERE ID_Admin=?");
            pr_Del_Laporan.setString(1, ID_Admin);
            int statusCode_3 = pr_Del_Laporan.executeUpdate();

            PreparedStatement pr_Del_Admin = con.prepareStatement("DELETE User_Admin WHERE ID_Admin=? and Password=?");
            pr_Del_Admin.setString(1, ID_Admin);
            pr_Del_Admin.setString(2, Password);
            int statusCode_4 = pr_Del_Admin.executeUpdate();

            if(statusCode_1 == 0 || statusCode_2 == 0 || statusCode_3 == 0 || statusCode_4 == 0){
                status = "Akun gagal dihapus!";
            } else {
                status = "Akun Berhasil Dihapus!";
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

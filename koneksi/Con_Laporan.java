/**
 * Property of KELOMPOK-5 PL Kelas 2A
 * Author1: Fauzan Farhan Antoro
 * Author2: Alfanisa Safvira
 * Author3: Daffa Fawwaz Syadad
 */
package koneksi;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Con_Laporan {
    Connection con = null;

    public static ArrayList<String> StoredKodePSN = new ArrayList<String>();

    public static String searchPsnArr(String kode){
        String kodeGet = "";
        for(int i = 0; i < StoredKodePSN.size(); i++) {
            // Cek kode lokal
            // Kode lokal ada dari yg paling awal, jd dia genap
            // Selang seling, kode lokal, kode di sql
            if(i % 2 == 0){
                if(kode.equals(StoredKodePSN.get(i))){
                    kodeGet = StoredKodePSN.get(i+1);
                    break;
                }
            }
        }
        return kodeGet;
    }

    // CREATE
    /* Disini dicari dulu harganya abis itu diproses langsung didalam totalnya */
    public String add_Laporan(String Kode_Pelanggan, String Kode_Barang, int Jumlah_Pesanan, java.sql.Timestamp date, String ID_Admin){
        String status;
        try {
            con = new SQLConnect().getConSQL();
            // Get harga lalu hitung harga total
            PreparedStatement pr_GetHarga = con.prepareStatement("SELECT Harga_Barang FROM Barang WHERE ID_Admin = ? AND Kode_Barang = ?");
            pr_GetHarga.setString(1, ID_Admin);
            pr_GetHarga.setString(2, Con_Barang.searchBrgArr(Kode_Barang));

            ResultSet rs_GetHarga = pr_GetHarga.executeQuery();
            rs_GetHarga.next();
            int harga_Barang = rs_GetHarga.getInt("Harga_Barang");
            int hargaTotal = harga_Barang * Jumlah_Pesanan;

            // Masukkan data
            String args = "INSERT INTO Laporan_Pesanan(Nama_Pelanggan,Nama_Barang,Harga_Barang_Pesanan,Jumlah_Pesanan,Total_Harga_Pesanan,Tanggal_Pesanan,ID_Admin) values(?,?,?,?,?,?,?)";
            PreparedStatement pr_LaporanPesanan = con.prepareStatement(args);

            List<Object> data_NamaPlg = new Con_Pelanggan().get_PelangganByKode(Kode_Pelanggan, ID_Admin);
            Object[] parsedData_NamaPlg = (Object[]) data_NamaPlg.toArray(new Object[0]);
            pr_LaporanPesanan.setString(1, parsedData_NamaPlg[0].toString());
            
            List<Object> data_NamaBrg = new Con_Barang().get_BarangByKode(Kode_Barang, ID_Admin);
            Object[] parsedData_NamaBrg = (Object[]) data_NamaBrg.toArray(new Object[0]);
            pr_LaporanPesanan.setString(2, parsedData_NamaBrg[0].toString());
            
            pr_LaporanPesanan.setInt(3, harga_Barang);
            pr_LaporanPesanan.setInt(4, Jumlah_Pesanan);
            pr_LaporanPesanan.setInt(5, hargaTotal);
            pr_LaporanPesanan.setTimestamp(6, date);
            pr_LaporanPesanan.setString(7, ID_Admin);

            int statusCode = pr_LaporanPesanan.executeUpdate();
            if(statusCode == 0){
                status = "Data Gagal Disimpan!";
            } else {
                status = "Data berhasil dimasukkan!";
            }
        } catch (SQLException e) {
            status = e.getMessage();
        } finally {
            try { con.close(); } catch (SQLException e) { /* Ignored */ }
        }

        return status;
    }

    // READ
    public List<Object> get_LaporanPesanan(String ID_Admin){
        List<Object> dataList = new ArrayList<>();
        try {
            int x = 1;
            StoredKodePSN.clear();
            con = new SQLConnect().getConSQL();
            PreparedStatement pr = con.prepareStatement("SELECT * FROM Laporan_Pesanan WHERE ID_Admin = ? ORDER BY ID_Pesanan");
            pr.setString(1, ID_Admin);

            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                String Kode_Pesanan = "PSN-" + x;

                StoredKodePSN.add(Kode_Pesanan);
                StoredKodePSN.add(rs.getString("Kode_Pesanan").trim());

                String Nama_Pelanggan = rs.getString("Nama_Pelanggan").trim();
                String Nama_Barang = rs.getString("Nama_Barang").trim();
                int Harga_Barang = rs.getInt("Harga_Barang_Pesanan");
                int Jumlah_Pesanan = rs.getInt("Jumlah_Pesanan");
                int Total_Harga_Pesanan = rs.getInt("Total_Harga_Pesanan");
                Date Tanggal_Pesanan = rs.getTimestamp("Tanggal_Pesanan");

                Object[] dataArr = { Kode_Pesanan, Nama_Pelanggan, Nama_Barang, Harga_Barang, Jumlah_Pesanan, Total_Harga_Pesanan, Tanggal_Pesanan };
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

    public List<Object> get_LaporanPesananExport(String ID_Admin){
        List<Object> dataList = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd - (HH:mm)");
        try {
            int x = 1;
            StoredKodePSN.clear();
            con = new SQLConnect().getConSQL();
            PreparedStatement pr = con.prepareStatement("SELECT * FROM Laporan_Pesanan WHERE ID_Admin = ? ORDER BY ID_Pesanan");
            pr.setString(1, ID_Admin);

            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                String Kode_Pesanan = "PSN-" + x;

                StoredKodePSN.add(Kode_Pesanan);
                StoredKodePSN.add(rs.getString("Kode_Pesanan").trim());

                String Nama_Pelanggan = rs.getString("Nama_Pelanggan").trim();
                String Nama_Barang = rs.getString("Nama_Barang").trim();
                int Harga_Barang = rs.getInt("Harga_Barang_Pesanan");
                int Jumlah_Pesanan = rs.getInt("Jumlah_Pesanan");
                int Total_Harga_Pesanan = rs.getInt("Total_Harga_Pesanan");
                Date Tanggal = rs.getTimestamp("Tanggal_Pesanan");

                String Tanggal_Pesanan = dateFormat.format(Tanggal) + "\n  ";

                Object[] dataArr = { Kode_Pesanan, Nama_Pelanggan, Nama_Barang, Harga_Barang, Jumlah_Pesanan, Total_Harga_Pesanan, Tanggal_Pesanan };
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

    public List<Object> get_LaporanPesananPeriodExport(String ID_Admin, String startDate, String endDate){
        List<Object> dataList = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd - (HH:mm)");
        try {
            int x = 1;
            // boolean tanggalnya = false;
            StoredKodePSN.clear();
            Date d_start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(startDate + " 00:00:00.000");
            Date d_end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(endDate + " 23:59:59.999");

            con = new SQLConnect().getConSQL();
            PreparedStatement pr = con.prepareStatement("SELECT * FROM Laporan_Pesanan WHERE ID_Admin = ? ORDER BY ID_Pesanan");
            pr.setString(1, ID_Admin);

            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                
                String Kode_Pesanan = "PSN-" + x;

                StoredKodePSN.add(Kode_Pesanan);
                StoredKodePSN.add(rs.getString("Kode_Pesanan").trim());

                String Nama_Pelanggan = rs.getString("Nama_Pelanggan").trim();
                String Nama_Barang = rs.getString("Nama_Barang").trim();
                int Harga_Barang = rs.getInt("Harga_Barang_Pesanan");
                int Jumlah_Pesanan = rs.getInt("Jumlah_Pesanan");
                int Total_Harga_Pesanan = rs.getInt("Total_Harga_Pesanan");
                Date Tanggal = rs.getTimestamp("Tanggal_Pesanan");

                String Tanggal_Pesanan = dateFormat.format(Tanggal) + "\n  ";

                if(Tanggal.compareTo(d_start) >= 0 && Tanggal.compareTo(d_end) <= 0){
                    Object[] dataArr = { Kode_Pesanan, Nama_Pelanggan, Nama_Barang, Harga_Barang, Jumlah_Pesanan, Total_Harga_Pesanan, Tanggal_Pesanan };
                    Collections.addAll(dataList, dataArr);
                }
                
                x++;
            }
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } 
        catch(ParseException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } 
        finally {
            try { con.close(); } catch (SQLException e) { /* Ignored */ }
        }

        return dataList;
    }

    public List<Object> get_All_KodePesanan(String ID_Admin){
        List<Object> dataList = new ArrayList<>();
        try {
            int x = 1;
            StoredKodePSN.clear();
            con = new SQLConnect().getConSQL();
            PreparedStatement pr = con.prepareStatement("SELECT * FROM Laporan_Pesanan WHERE ID_Admin = ? ORDER BY ID_Pesanan");
            pr.setString(1, ID_Admin);

            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                String Kode_Pesanan = "PSN-" + x;

                StoredKodePSN.add(Kode_Pesanan);
                StoredKodePSN.add(rs.getString("Kode_Pesanan").trim());

                Object[] dataArr = { Kode_Pesanan };
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

    public List<Object> get_LaporanPesananByKode(String ID_Admin, String Kode_Pesanan){
        List<Object> dataList = new ArrayList<>();
        try {
            con = new SQLConnect().getConSQL();
            PreparedStatement pr = con.prepareStatement("SELECT * FROM Laporan_Pesanan WHERE ID_Admin = ? AND Kode_Pesanan = ? ORDER BY ID_Pesanan");
            pr.setString(1, ID_Admin);
            pr.setString(2, searchPsnArr(Kode_Pesanan));

            ResultSet rs = pr.executeQuery();
            rs.next();

            String Nama_Pelanggan = rs.getString("Nama_Pelanggan").trim();
            String Nama_Barang = rs.getString("Nama_Barang").trim();
            Date Tanggal_Pesanan = rs.getTimestamp("Tanggal_Pesanan");

            Object[] dataArr = { Nama_Pelanggan, Nama_Barang, Tanggal_Pesanan };
            Collections.addAll(dataList, dataArr);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try { con.close(); } catch (SQLException e) { /* Ignored */ }
        }

        return dataList;
    }

    /* Untuk laporan pendapatan saja yaitu kode, jumlah, total, dan tanggal */
    public List<Object> get_LaporanPendapatan_ByDate(String ID_Admin){
        List<Object> dataList = new ArrayList<>();
        try {
            con = new SQLConnect().getConSQL();
            String str = "SELECT datepart(yyyy, Tanggal_Pesanan) as [tahun],  datepart(m, Tanggal_Pesanan) as [bulan], datepart(dd, Tanggal_Pesanan) as [tgl], SUM(Total_Harga_Pesanan) as Total, COUNT(*) as banyak_Pesanan";
            String str2  = "FROM Laporan_Pesanan WHERE ID_Admin = ? GROUP BY datepart(dd, Tanggal_Pesanan), datepart(m, Tanggal_Pesanan), datepart(yyyy, Tanggal_Pesanan) ORDER BY [tgl]";
            PreparedStatement pr = con.prepareStatement(str + " " + str2);
            pr.setString(1, ID_Admin);

            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                int tahun = rs.getInt("tahun");
                int bulan = rs.getInt("bulan");
                int tgl = rs.getInt("tgl");
                int Total = rs.getInt("Total");
                int banyak_pesanan = rs.getInt("banyak_pesanan");

                Object[] dataArr = { tahun, bulan, tgl, Total, banyak_pesanan };
                Collections.addAll(dataList, dataArr);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try { con.close(); } catch (SQLException e) { /* Ignored */ }
        }

        return dataList;
    }

    public List<Object> get_LaporanPendapatan_ByDateExport(String ID_Admin){
        List<Object> dataList = new ArrayList<>();
        try {
            con = new SQLConnect().getConSQL();
            String str = "SELECT datepart(yyyy, Tanggal_Pesanan) as [tahun],  datepart(m, Tanggal_Pesanan) as [bulan], datepart(dd, Tanggal_Pesanan) as [tgl], SUM(Total_Harga_Pesanan) as Total, COUNT(*) as banyak_Pesanan";
            String str2  = "FROM Laporan_Pesanan WHERE ID_Admin = ? GROUP BY datepart(dd, Tanggal_Pesanan), datepart(m, Tanggal_Pesanan), datepart(yyyy, Tanggal_Pesanan) ORDER BY [tgl]";
            PreparedStatement pr = con.prepareStatement(str + " " + str2);
            pr.setString(1, ID_Admin);

            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                int tahun = rs.getInt("tahun");
                int bulan = rs.getInt("bulan");
                int tgl = rs.getInt("tgl");
                int Total = rs.getInt("Total");
                int banyak_pesanan = rs.getInt("banyak_pesanan");

                String tanggal = tahun + "-" + bulan + "-" + tgl ;

                Object[] dataArr = { tanggal, Total, banyak_pesanan };
                Collections.addAll(dataList, dataArr);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try { con.close(); } catch (SQLException e) { /* Ignored */ }
        }

        return dataList;
    }

    public List<Object> get_LaporanPendapatan_ByDatePeriodExport(String ID_Admin, String startDate, String endDate){
        List<Object> dataList = new ArrayList<>();
        try {
            Date d_1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(startDate + " 00:00:00.000");
            Date d_2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(endDate + " 23:59:59.999");
            java.sql.Timestamp dateStart = new java.sql.Timestamp(d_1.getTime());
            java.sql.Timestamp dateEnd = new java.sql.Timestamp(d_2.getTime());

            con = new SQLConnect().getConSQL();
            String str = "SELECT datepart(yyyy, Tanggal_Pesanan) as [tahun],  datepart(m, Tanggal_Pesanan) as [bulan], datepart(dd, Tanggal_Pesanan) as [tgl], SUM(Total_Harga_Pesanan) as Total, COUNT(*) as banyak_Pesanan";
            String str2  = "FROM Laporan_Pesanan WHERE ID_Admin = ? AND Tanggal_Pesanan BETWEEN ? AND ? GROUP BY datepart(dd, Tanggal_Pesanan), datepart(m, Tanggal_Pesanan), datepart(yyyy, Tanggal_Pesanan) ORDER BY [tgl]";
            PreparedStatement pr = con.prepareStatement(str + " " + str2);
            pr.setString(1, ID_Admin);
            pr.setTimestamp(2, dateStart);
            pr.setTimestamp(3, dateEnd);

            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                int tahun = rs.getInt("tahun");
                int bulan = rs.getInt("bulan");
                int tgl = rs.getInt("tgl");
                int Total = rs.getInt("Total");
                int banyak_pesanan = rs.getInt("banyak_pesanan");

                String tanggal = tahun + "-" + bulan + "-" + tgl ;

                Object[] dataArr = { tanggal, Total, banyak_pesanan };
                Collections.addAll(dataList, dataArr);
            }
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try { con.close(); } catch (SQLException e) { /* Ignored */ }
        }

        return dataList;
    }

    public List<Object> get_LaporanPendapatan_ByMonth(String ID_Admin){
        List<Object> dataList = new ArrayList<>();
        try {
            con = new SQLConnect().getConSQL();
            String str = "SELECT datepart(yyyy, Tanggal_Pesanan) as [tahun], datepart(m, Tanggal_Pesanan) as [bulan], SUM(Total_Harga_Pesanan) as Total, COUNT(*) as banyak_Pesanan";
            String str2  = "FROM Laporan_Pesanan WHERE ID_Admin = ? GROUP BY datepart(mm, Tanggal_Pesanan), datepart(yyyy, Tanggal_Pesanan) ORDER BY [bulan];";
            PreparedStatement pr = con.prepareStatement(str + " " + str2);
            pr.setString(1, ID_Admin);

            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                int tahun = rs.getInt("tahun");
                int bulan = rs.getInt("bulan");
                int Total = rs.getInt("Total");
                int banyak_pesanan = rs.getInt("banyak_pesanan");

                Object[] dataArr = { tahun, bulan, Total, banyak_pesanan };
                Collections.addAll(dataList, dataArr);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try { con.close(); } catch (SQLException e) { /* Ignored */ }
        }

        return dataList;
    }

    public List<Object> get_LaporanPendapatan_ByMonthExport(String ID_Admin){
        List<Object> dataList = new ArrayList<>();
        try {
            con = new SQLConnect().getConSQL();
            String str = "SELECT datepart(yyyy, Tanggal_Pesanan) as [tahun], datepart(m, Tanggal_Pesanan) as [bulan], SUM(Total_Harga_Pesanan) as Total, COUNT(*) as banyak_Pesanan";
            String str2  = "FROM Laporan_Pesanan WHERE ID_Admin = ? GROUP BY datepart(mm, Tanggal_Pesanan), datepart(yyyy, Tanggal_Pesanan) ORDER BY [bulan];";
            PreparedStatement pr = con.prepareStatement(str + " " + str2);
            pr.setString(1, ID_Admin);

            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                int tahun = rs.getInt("tahun");
                int bulan = rs.getInt("bulan");
                int Total = rs.getInt("Total");
                int banyak_pesanan = rs.getInt("banyak_pesanan");

                String tanggal = tahun + "-" + bulan;

                Object[] dataArr = { tanggal, Total, banyak_pesanan };
                Collections.addAll(dataList, dataArr);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try { con.close(); } catch (SQLException e) { /* Ignored */ }
        }

        return dataList;
    }

    public List<Object> get_LaporanPendapatan_ByMonthPeriodExport(String ID_Admin, String startDate, String endDate){
        List<Object> dataList = new ArrayList<>();
        try {
            Date d_1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(startDate + " 00:00:00.000");
            Date d_2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(endDate + " 23:59:59.999");
            java.sql.Timestamp dateStart = new java.sql.Timestamp(d_1.getTime());
            java.sql.Timestamp dateEnd = new java.sql.Timestamp(d_2.getTime());

            con = new SQLConnect().getConSQL();
            String str = "SELECT datepart(yyyy, Tanggal_Pesanan) as [tahun], datepart(m, Tanggal_Pesanan) as [bulan], SUM(Total_Harga_Pesanan) as Total, COUNT(*) as banyak_Pesanan";
            String str2  = "FROM Laporan_Pesanan WHERE ID_Admin = ? AND Tanggal_Pesanan BETWEEN ? AND ? GROUP BY datepart(mm, Tanggal_Pesanan), datepart(yyyy, Tanggal_Pesanan) ORDER BY [bulan];";
            PreparedStatement pr = con.prepareStatement(str + " " + str2);
            pr.setString(1, ID_Admin);
            pr.setTimestamp(2, dateStart);
            pr.setTimestamp(3, dateEnd);

            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                int tahun = rs.getInt("tahun");
                int bulan = rs.getInt("bulan");
                int Total = rs.getInt("Total");
                int banyak_pesanan = rs.getInt("banyak_pesanan");

                String tanggal = tahun + "-" + bulan;

                Object[] dataArr = { tanggal, Total, banyak_pesanan };
                Collections.addAll(dataList, dataArr);
            }
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try { con.close(); } catch (SQLException e) { /* Ignored */ }
        }

        return dataList;
    }

    public List<Object> get_LaporanPendapatan_ByYear(String ID_Admin){
        List<Object> dataList = new ArrayList<>();
        try {
            con = new SQLConnect().getConSQL();
            String str = "SELECT datepart(yyyy, Tanggal_Pesanan) as [tahun], SUM(Total_Harga_Pesanan) as Total, count(*) as banyak_Pesanan";
            String str2  = "FROM Laporan_Pesanan WHERE ID_Admin = ? GROUP BY datepart(yyyy, Tanggal_Pesanan) ORDER BY [tahun];";
            PreparedStatement pr = con.prepareStatement(str + " " + str2);
            pr.setString(1, ID_Admin);

            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                int tahun = rs.getInt("tahun");
                int Total = rs.getInt("Total");
                int banyak_pesanan = rs.getInt("banyak_pesanan");

                Object[] dataArr = { tahun, Total, banyak_pesanan };
                Collections.addAll(dataList, dataArr);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try { con.close(); } catch (SQLException e) { /* Ignored */ }
        }

        return dataList;
    }

    public List<Object> get_LaporanPendapatan_ByYearPeriodExport(String ID_Admin, String startDate, String endDate){
        List<Object> dataList = new ArrayList<>();
        try {
            Date d_1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(startDate + " 00:00:00.000");
            Date d_2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(endDate + " 23:59:59.999");
            java.sql.Timestamp dateStart = new java.sql.Timestamp(d_1.getTime());
            java.sql.Timestamp dateEnd = new java.sql.Timestamp(d_2.getTime());

            con = new SQLConnect().getConSQL();
            String str = "SELECT datepart(yyyy, Tanggal_Pesanan) as [tahun], SUM(Total_Harga_Pesanan) as Total, count(*) as banyak_Pesanan";
            String str2  = "FROM Laporan_Pesanan WHERE ID_Admin = ? AND Tanggal_Pesanan BETWEEN ? AND ? GROUP BY datepart(yyyy, Tanggal_Pesanan) ORDER BY [tahun];";
            PreparedStatement pr = con.prepareStatement(str + " " + str2);
            pr.setString(1, ID_Admin);
            pr.setTimestamp(2, dateStart);
            pr.setTimestamp(3, dateEnd);

            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                int tahun = rs.getInt("tahun");
                int Total = rs.getInt("Total");
                int banyak_pesanan = rs.getInt("banyak_pesanan");

                Object[] dataArr = { tahun, Total, banyak_pesanan };
                Collections.addAll(dataList, dataArr);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException e) {
            System.out.println(e);
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
            con = new SQLConnect().getConSQL();
            PreparedStatement pr_Del_Laporan = con.prepareStatement("DELETE Laporan_Pesanan WHERE ID_Admin=? AND Kode_Pesanan=?");
            pr_Del_Laporan.setString(1, ID_Admin);
            pr_Del_Laporan.setString(2, searchPsnArr(Kode_Pesanan));
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

/**
 * Property of KELOMPOK-5 PL Kelas 2A
 * Author1: Fauzan Farhan Antoro
 * Author2: Alfanisa Safvira
 * Author3: Daffa Fawwaz Syadad
 */
package koneksi;

// import java.sql.*;
// import javax.swing.*;
// import java.util.ArrayList;
// import java.util.Collections;
import java.util.List;

public class Debug {
    // Testing zone
    public static void main(String[] args) {
        String username = "Kelompok5";
        String passWord = "12345678";
        
        Boolean login = new Con_Admin().login(username, passWord);
        // System.out.println(login);

        // if(login){
        //     List<Object> data = new Con_Admin().get_Akun("Kelompok5-1", passWord);
        //     System.out.println(data);

        //     String[] parsedData = (String[]) data.toArray(new String[0]);
        //     for (String string : parsedData) {
        //         System.out.println(string);
        //     }
        // }

        // if (login){
        //     List<Object> data = new Con_Laporan().get_LaporanPendapatan_ByDate(username);
        //     System.out.println(data);

        //     Object[] parsedData = (Object[]) data.toArray(new Object[0]);
        //     for (int i = 0; i < parsedData.length; i++) {
        //         System.out.println(parsedData[i]);
        //     }

        // }

        // Hapus
        // if(login){
        //     String res = new DB_Data().hapus_Akun(akun, passWord);

        //     System.out.println(res);
        // }

        if(login){
            List<Object> data = new Con_Laporan().get_LaporanPesanan(username);
            Object[] parsedData = (Object[]) data.toArray(new Object[0]);
            
            // Diisi dengan semua data di convert jadi string
            for (int i = 0; i < parsedData.length; i = i + 7) {
                String[] isi = { parsedData[i].toString(), parsedData[i+1].toString(), parsedData[i+2].toString(), parsedData[i+3].toString(), 
                                 parsedData[i+4].toString(), parsedData[i+5].toString(), parsedData[i+6].toString() };

                for (int j = 0; j < isi.length; j++) {
                    System.out.println(isi[j]);
                }
                // tModelTbAtas.addRow(isi);
            }
        }
    }
}

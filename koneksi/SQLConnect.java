/**
 * Property of KELOMPOK-5 PL Kelas 2A
 * Author1: Fauzan Farhan Antoro
 * Author2: Alfanisa Safvira
 * Author3: Daffa Fawwaz Syadad
 */
package koneksi;
import java.sql.*;

// Konek menggunakan jdbc 4.0 ke database bernama db_Warung
public class SQLConnect {
    Connection conn;
    public Connection getConSQL(){
        try{ 
            String url = "jdbc:sqlserver://localhost:1433;"
                    + "databaseName=db_Warung;user=sa;password=12345678"; 
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return conn;
    }
}

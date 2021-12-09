
package saved_authentication;

public class Akun {
    // Static agar data itu statis dan dapat menyimpan autentikasi
    // Yang sifatnya nanti hanya disimpan saat runtime
    // Jadi class ini sebagai cache lokal
    public static String ID_Admin, Password, Nama_Pemilik, Nama_Toko, Alamat_Toko, Nomor_Telepon;

    public void reset(){
        ID_Admin = null;
        Password = null;
        Nama_Pemilik = null;
        Nama_Toko = null;
        Alamat_Toko = null;
        Nomor_Telepon = null;
    }
}

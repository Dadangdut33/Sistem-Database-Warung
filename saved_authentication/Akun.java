package saved_authentication;

public class Akun {
    private String ID_Admin, Password, Nama_Pemilik, Nama_Toko, Alamat_Toko, Nomor_Telepon;

    public void reset(){
        this.ID_Admin = null;
        this.Password = null;
        this.Nama_Pemilik = null;
        this.Nama_Toko = null;
        this.Alamat_Toko = null;
        this.Nomor_Telepon = null;
    }

    public void setID_Admin(String ID_Admin) {
        this.ID_Admin = ID_Admin;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setNama_Pemilik(String Nama_Pemilik) {
        this.Nama_Pemilik = Nama_Pemilik;
    }

    public void setNama_Toko(String Nama_Toko) {
        this.Nama_Toko = Nama_Toko;
    }

    public void setAlamat_Toko(String Alamat_Toko) {
        this.Alamat_Toko = Alamat_Toko;
    }

    public void setNomor_Telepon(String Nomor_Telepon) {
        this.Nomor_Telepon = Nomor_Telepon;
    }

    public String getID_Admin() {
        return this.ID_Admin;
    }

    public String getPassword() {
        return this.Password;
    }

    public String getNama_Pemilik() {
        return this.Nama_Pemilik;
    }

    public String getNama_Toko() {
        return this.Nama_Toko;
    }

    public String getAlamat_Toko() {
        return this.Alamat_Toko;
    }

    public String getNomor_Telepon() {
        return this.Nomor_Telepon;
    }
}

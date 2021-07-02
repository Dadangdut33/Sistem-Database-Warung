/**
 * Property of KELOMPOK-5 PL Kelas 2A
 * Author1: Fauzan Farhan Antoro
 * Author2: Alfanisa Safvira
 * Author3: Daffa Fawwaz Syadad
 */

package frame.barang;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import koneksi.Con_Barang;
import panels.Panel_Barang;
import saved_authentication.Akun;

public class Frame_Edit_Barang extends JFrame implements ActionListener {
    JPanel jPanel_Header = new JPanel();
    JPanel jPanel_Content = new JPanel();
    
    JLabel jLabel_Judul = new JLabel();

    JLabel jLabel_KodeBarang = new JLabel();
    JLabel jLabel_NamaBarang = new JLabel();
    JLabel jLabel_HargaBarang = new JLabel();
    JLabel jLabel_JumlahStok = new JLabel();

    JComboBox<String> comboBox_KodeBarang = new JComboBox<>();
    JTextField jTextField_NamaBarang = new JTextField();
    
    SpinnerModel spModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1000);
    JSpinner spinner_HargaBarang = new JSpinner(spModel);

    SpinnerModel spModel_2 = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
    JSpinner spinner_JumlahStok = new JSpinner(spModel_2);

    JButton button_Edit = new JButton();
    JButton button_Refresh = new JButton();

    ArrayList<String> data_Yang_Kosong = new ArrayList<>();

    String namaAwal;

    public Frame_Edit_Barang(){
        // Setting frame
        this.setSize(355, 300);
        this.setTitle("Edit Barang");
        this.getContentPane().setBackground(new Color(60, 63, 65));
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setIconImage(new ImageIcon(getClass().getResource("/assets/icons8-database-50.png")).getImage());

        // Panel Header
        jPanel_Header.setLayout(null);
        jPanel_Header.setBackground(new Color(18, 18, 18));
        jPanel_Header.setBounds(10, 10, 320, 50);
        
        // Panel Konten
        jPanel_Content.setLayout(null);
        jPanel_Content.setBackground(new Color(24, 40, 44));
        jPanel_Content.setBounds(10, 70, 320, 180);
        
        // Label Judul
        jLabel_Judul.setForeground(Color.WHITE);
        jLabel_Judul.setFont((new Font("Segoe UI", Font.BOLD, 18)));
        jLabel_Judul.setText("EDIT BARANG");
        jLabel_Judul.setBounds(95, 10, 250, 30);
        jPanel_Header.add(jLabel_Judul);

        // Label Kode barang
        jLabel_KodeBarang.setForeground(new Color(187, 187, 187));
        jLabel_KodeBarang.setBounds(10, 20, 150, 30);
        jLabel_KodeBarang.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        jLabel_KodeBarang.setText("Kode Barang");
        jPanel_Content.add(jLabel_KodeBarang);

        // combobox kode barang
        comboBox_KodeBarang.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        comboBox_KodeBarang.setBackground(new Color(60, 63, 65));
        comboBox_KodeBarang.setForeground(new Color(187, 187, 187));
        comboBox_KodeBarang.setBounds(100, 22, 210, 25);
        isiCbKodeBarang();
        jPanel_Content.add(comboBox_KodeBarang);

        // Label Nama barang
        jLabel_NamaBarang.setForeground(new Color(187, 187, 187));
        jLabel_NamaBarang.setBounds(10, 50, 150, 30);
        jLabel_NamaBarang.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        jLabel_NamaBarang.setText("Nama Barang");
        jPanel_Content.add(jLabel_NamaBarang);

        // textfield nama barang
        jTextField_NamaBarang.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        jTextField_NamaBarang.setBackground(new Color(60, 63, 65));
        jTextField_NamaBarang.setForeground(new Color(187, 187, 187));
        jTextField_NamaBarang.setBounds(100, 52, 210, 25);
        jTextField_NamaBarang.setCaretColor(Color.WHITE);
        jPanel_Content.add(jTextField_NamaBarang);

        // Label harga barang
        jLabel_HargaBarang.setForeground(new Color(187, 187, 187));
        jLabel_HargaBarang.setBounds(10, 80, 150, 30);
        jLabel_HargaBarang.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        jLabel_HargaBarang.setText("Harga Barang");
        jPanel_Content.add(jLabel_HargaBarang);
    
        // spinner harga barang
        spinner_HargaBarang.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        spinner_HargaBarang.setBounds(100, 82, 210, 25);
        jPanel_Content.add(spinner_HargaBarang);

        // Label harga barang
        jLabel_JumlahStok.setForeground(new Color(187, 187, 187));
        jLabel_JumlahStok.setBounds(10, 110, 150, 30);
        jLabel_JumlahStok.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        jLabel_JumlahStok.setText("Jumlah Stok");
        jPanel_Content.add(jLabel_JumlahStok);
    
        // spinner harga barang
        spinner_JumlahStok.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        spinner_JumlahStok.setBounds(100, 112, 210, 25);
        jPanel_Content.add(spinner_JumlahStok);
        
        // Button Add
        button_Edit.setBackground(new Color(60, 63, 65));
        button_Edit.setForeground(new Color(187, 187, 187));
        button_Edit.setBounds(100, 142, 90, 20);
        button_Edit.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        button_Edit.setText("Edit Data");
        jPanel_Content.add(button_Edit);

        // Button refresh
        button_Refresh.setBackground(new Color(60, 63, 65));
        button_Refresh.setForeground(new Color(187, 187, 187));
        button_Refresh.setBounds(220, 142, 90, 20);
        button_Refresh.setFont((new Font("Segoe UI", Font.PLAIN, 12)));
        button_Refresh.setText("Refresh");
        jPanel_Content.add(button_Refresh);

        // Action listener button
        comboBox_KodeBarang.addActionListener(this);
        button_Edit.addActionListener(this);
        button_Refresh.addActionListener(this);

        // Add panel header dan konten
        this.add(jPanel_Header);
        this.add(jPanel_Content);

        // Setvisible
        this.setVisible(true);
    }

    void isiCbKodeBarang(){
        List<Object> data = new Con_Barang().get_All_KodeBarang(Akun.ID_Admin);
        Object[] parsedData = (Object[]) data.toArray(new Object[0]);
        
        comboBox_KodeBarang.addItem("Pilih Kode Barang");
        // Diisi dengan semua data di convert jadi string
        for (int i = 0; i < parsedData.length; i++) {
            comboBox_KodeBarang.addItem(parsedData[i].toString());
        }
    }

    void isiData(){
        if(comboBox_KodeBarang.getSelectedIndex() == 0) {
            refresh();
        } else {
            List<Object> data = new Con_Barang().get_BarangByKode(comboBox_KodeBarang.getSelectedItem().toString(), Akun.ID_Admin);
            Object[] parsedData = (Object[]) data.toArray(new Object[0]);

            namaAwal = parsedData[0].toString();

            jTextField_NamaBarang.setText(parsedData[0].toString());
            spinner_HargaBarang.setValue((Integer) parsedData[1]);
            spinner_JumlahStok.setValue((Integer) parsedData[2]);
        }
    }

    void refresh(){
        for(ActionListener act : comboBox_KodeBarang.getActionListeners()){
            comboBox_KodeBarang.removeActionListener(act);
        }
        comboBox_KodeBarang.removeAllItems();
        jTextField_NamaBarang.setText("");
        comboBox_KodeBarang.addActionListener(this);
        spinner_HargaBarang.setValue(0);
        spinner_JumlahStok.setValue(0);
        isiCbKodeBarang();
        comboBox_KodeBarang.setSelectedIndex(0);
    }

    boolean kosong(){
        boolean kosong = false;
        for (int i = data_Yang_Kosong.size() - 1; i >= 0; i--) {
            data_Yang_Kosong.remove(i);
        }

        if(comboBox_KodeBarang.getSelectedIndex() == 0){
            kosong = true;
            data_Yang_Kosong.add("Kode Kategori");
        }
        if(jTextField_NamaBarang.getText().equals("")){
            kosong = true;
            data_Yang_Kosong.add("Nama Barang");
        }
        if((Integer) spinner_HargaBarang.getValue() == 0){
            kosong = true;
            data_Yang_Kosong.add("Harga Barang");
        }

        // Stok tidak di cek karena mungkin saja user hanya memasukan barang ke katalog
        // Namun stoknya kosong
        
        return kosong;
    }

    // Popup untuk yang kosong
    void kosongPopup(){
        int jumlahKosong = data_Yang_Kosong.size();
        int last = data_Yang_Kosong.size() - 1;

        if(jumlahKosong == 1) {
            JOptionPane.showMessageDialog( 
                null, 
                String.join(", ", data_Yang_Kosong) + " harus diisi!", 
                "Input Error",                
                JOptionPane.ERROR_MESSAGE);
        } else
        if(jumlahKosong == 2) {
            JOptionPane.showMessageDialog( 
                null, 
                String.join(" dan ", 
                String.join(", ", data_Yang_Kosong.subList(0, last)),
                data_Yang_Kosong.get(last)) + " harus diisi!", 
                "Input Error",                
                JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog( 
                null, 
                String.join(", dan ", 
                String.join(", ", data_Yang_Kosong.subList(0, last)),
                data_Yang_Kosong.get(last)) + " harus diisi!", 
                "Input Error",                
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource().equals(button_Refresh)) {
            refresh();
        } else
        if(ae.getSource().equals(button_Edit)) {
            // Cek kosong
            if(kosong()){
                kosongPopup();
            } else {
                // Dapatkan datanya
                String Kode_Barang = comboBox_KodeBarang.getSelectedItem().toString();
                String Nama_Barang = jTextField_NamaBarang.getText().trim();
                int Harga_Barang = (Integer) spinner_HargaBarang.getValue();
                int Stok_Barang = (Integer) spinner_JumlahStok.getValue();

                // Disini pertama di cek ada yg sama apa ga
                // setelah itu di cek nama barang yg diinput sama atau ga kaya nama awalnya, jd misalkan
                // dia ga ngedit nama jd gpp
                if(new Con_Barang().dupeCheck(Nama_Barang, Akun.ID_Admin) && !Nama_Barang.equals(namaAwal)){
                    JOptionPane.showMessageDialog( 
                        null,
                        "Nama Barang Yang Dimasukkan Tidak Boleh Sama Dengan Yang Sudah Ada!", 
                        "Data Duplikat",
                        JOptionPane.WARNING_MESSAGE);

                    return;
                }

                String StatusEditBarang = new Con_Barang().edit_Barang(Nama_Barang, Harga_Barang, Stok_Barang, Akun.ID_Admin, Kode_Barang);

                // Jika berhasil
                if(StatusEditBarang.equals("Barang berhasil diubah!")){
                    JOptionPane.showMessageDialog( 
                        null, 
                        StatusEditBarang, 
                        "Edit Barang Berhasil",         
                        JOptionPane.INFORMATION_MESSAGE);

                        refresh();
                        Panel_Barang.refreshAll();
                } else { // Jika gagal kurangi barang
                    JOptionPane.showMessageDialog(null, StatusEditBarang, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else 
        if(ae.getSource().equals(comboBox_KodeBarang)){
            isiData();
        }
    }
}

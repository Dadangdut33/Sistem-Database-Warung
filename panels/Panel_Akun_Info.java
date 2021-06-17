/**
 * Property of KELOMPOK-5 PL Kelas 2A
 * Author1: Fauzan Farhan Antoro
 * Author2: Alfanisa Safvira
 * Author3: Daffa Fawwaz Syadad
 */
package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import frame.akun.*;
import frame.authentication.Frame_Login;
import frame.menu_utama.Frame_Menu;
import koneksi.Con_Admin;
import saved_authentication.Akun;

public class Panel_Akun_Info extends JPanel implements ActionListener {
    // Panel
    JPanel panel_Belakang = new JPanel();
    JPanel panel_Depan = new JPanel();
    
    // Label
    JLabel jLabel_NamaToko = new JLabel();
    JLabel jLabel_AlamatToko = new JLabel();
    JLabel jLabel_NomorTelepon = new JLabel();
    JLabel jLabel_PemilikToko = new JLabel();
    JLabel jLabel_UbahPass = new JLabel();
    JLabel jLabel_UbahBiodata = new JLabel();
    JLabel jLabel_DeleteAkun = new JLabel();

    // Button
    JButton jButton_UbahPass = new JButton();
    JButton jButton_UbahBiodata = new JButton();
    JButton jButton_DeleteAkun = new JButton();

    // Static for rgb
    public static boolean exitPanelAkun;

    Object currentWindow = this;

    public Panel_Akun_Info(){
        this.setLayout(null);
        this.setBounds(80, 70, 1120, 630);
        this.setBackground(new Color(24, 40, 44));
        
        panel_Belakang.setBackground(new Color(24, 40, 44));
        panel_Belakang.setLayout(null);
        panel_Belakang.setBounds(100, 30, 890, 200);
        this.add(panel_Belakang);

        panel_Depan.setBackground(new Color(24, 40, 44));
        panel_Depan.setLayout(null);
        panel_Depan.setBounds(10, 10, 870, 180);
        panel_Belakang.add(panel_Depan);

        exitPanelAkun = false;
        rgb_PanelBelakang();

        // Label nama toko
        jLabel_NamaToko.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel_NamaToko.setForeground(Color.WHITE);
        jLabel_NamaToko.setBounds(20, 10, 830, 50);
        jLabel_NamaToko.setText("Database " + Akun.Nama_Toko);
        jLabel_NamaToko.setHorizontalAlignment(SwingConstants.CENTER);
        panel_Depan.add(jLabel_NamaToko);

        // Label alamat toko
        jLabel_AlamatToko.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        jLabel_AlamatToko.setForeground(Color.WHITE);
        jLabel_AlamatToko.setBounds(60, 70, 730, 26);
        jLabel_AlamatToko.setText(Akun.Alamat_Toko);
        jLabel_AlamatToko.setIcon(new ImageIcon(getClass().getResource("/assets/icons8-address-26.png")));
        panel_Depan.add(jLabel_AlamatToko);

        // Label Nomor Telepon Toko 
        jLabel_NomorTelepon.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        jLabel_NomorTelepon.setForeground(Color.WHITE);
        jLabel_NomorTelepon.setBounds(60, 100, 730, 26);
        jLabel_NomorTelepon.setText(Akun.Nomor_Telepon);
        jLabel_NomorTelepon.setIcon(new ImageIcon(getClass().getResource("/assets/icons8-telephone-26.png")));
        panel_Depan.add(jLabel_NomorTelepon);

        // Label Nama Pemilik
        jLabel_PemilikToko.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        jLabel_PemilikToko.setForeground(Color.WHITE);
        jLabel_PemilikToko.setBounds(60, 130, 730, 26);
        jLabel_PemilikToko.setText(Akun.Nama_Pemilik);
        jLabel_PemilikToko.setIcon(new ImageIcon(getClass().getResource("/assets/icons8-account-2-30.png")));
        panel_Depan.add(jLabel_PemilikToko);

        // Button Edit password
        jButton_UbahPass.setBackground(new Color(40, 40, 44));
        jButton_UbahPass.setOpaque(false);
        jButton_UbahPass.setContentAreaFilled(false);
        jButton_UbahPass.setFocusPainted(false);
        jButton_UbahPass.setBorderPainted(false);
        jButton_UbahPass.setIcon(new ImageIcon(getClass().getResource("/assets/icons8-password-2-50.png")));
        jButton_UbahPass.setToolTipText("Ubah Password");
        jButton_UbahPass.setBounds(300, 500, 50, 50);
        this.add(jButton_UbahPass);

        // Button Edit biodata
        jButton_UbahBiodata.setBackground(new Color(40, 40, 44));
        jButton_UbahBiodata.setOpaque(false);
        jButton_UbahBiodata.setContentAreaFilled(false);
        jButton_UbahBiodata.setFocusPainted(false);
        jButton_UbahBiodata.setBorderPainted(false);
        jButton_UbahBiodata.setIcon(new ImageIcon(getClass().getResource("/assets/icons8-edit-account-50.png")));
        jButton_UbahBiodata.setToolTipText("Ubah Biodata");
        jButton_UbahBiodata.setBounds(500, 500, 50, 50);
        this.add(jButton_UbahBiodata);

        // Button Hapus
        jButton_DeleteAkun.setBackground(new Color(40, 40, 44));
        jButton_DeleteAkun.setOpaque(false);
        jButton_DeleteAkun.setContentAreaFilled(false);
        jButton_DeleteAkun.setFocusPainted(false);
        jButton_DeleteAkun.setBorderPainted(false);
        jButton_DeleteAkun.setIcon(new ImageIcon(getClass().getResource("/assets/icons8-denied-50.png")));
        jButton_DeleteAkun.setToolTipText("Delete Akun");
        jButton_DeleteAkun.setBounds(700, 500, 50, 50);
        this.add(jButton_DeleteAkun);

        // Add Action Listener
        jButton_UbahPass.addActionListener(this);
        jButton_UbahBiodata.addActionListener(this);
        jButton_DeleteAkun.addActionListener(this);

        this.setEnabled(false);
    }

    void refreshAll(){
        jLabel_NamaToko.setText("Database " + Akun.Nama_Toko);
        jLabel_AlamatToko.setText(Akun.Alamat_Toko);
        jLabel_NomorTelepon.setText(Akun.Nomor_Telepon);
        jLabel_PemilikToko.setText(Akun.Nama_Pemilik);
    }

    void rgb_PanelBelakang(){
        Thread rgbPanel = new Thread() {
            @Override
            public void run() {
                try {
                    while(!exitPanelAkun){
                        panel_Belakang.setBackground(new Color((int)(Math.random() * 0x1000000)));
    
                        sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        // Start threadnya
        rgbPanel.start();
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource().equals(jButton_UbahPass)){
            new Ganti_Password().addWindowListener(new WindowAdapter(){
                @Override
                public void windowClosing(WindowEvent e) {
                    refreshAll();
                    Frame_Menu.anotherFrameIsOpen = 0;
                }
            });
            Frame_Menu.anotherFrameIsOpen = 1;
        } else 
        if(ae.getSource().equals(jButton_UbahBiodata)){
            new Ubah_Biodata().addWindowListener(new WindowAdapter(){
                @Override
                public void windowClosing(WindowEvent e) {
                    refreshAll();
                    Frame_Menu.anotherFrameIsOpen = 0;
                }
            });
            Frame_Menu.anotherFrameIsOpen = 1;
        } else 
        if(ae.getSource().equals(jButton_DeleteAkun)){
            int confirmed = JOptionPane.showConfirmDialog(null, 
                    "Apakah anda yakin ingin menghapus akun anda?\nWARNING: SEMUA DATA YANG DISIMPAN DI DATABASE JUGA AKAN DIHAPUS APABILA ANDA MENGHAPUS AKUN!", "Delete Confirmation",
                    JOptionPane.YES_NO_OPTION);
            
            if (confirmed == JOptionPane.YES_OPTION) {
                JPanel panelDel = new JPanel();
                panelDel.setLayout(new BoxLayout(panelDel, BoxLayout.PAGE_AXIS));
                JLabel label = new JLabel("Masukkan Password Akun Anda Untuk Konfirmasi");
                JPasswordField passKonfirmasi = new JPasswordField(20);
                panelDel.add(label);
                panelDel.add(passKonfirmasi);
                
                String[] options = new String[]{"HAPUS", "CANCEL"};

                int option;
                String passGet;
                String currPaswordCache = Akun.Password;

                do {
                    option = JOptionPane.showOptionDialog(null, panelDel, "Konfirmasi Hapus",
                    JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[1]);

                    passGet = new String(passKonfirmasi.getPassword());

                    // JIka yg dipilih tombol Hapus
                    if(option != 1) {
                        if(passGet.equals(Akun.Password)){

                            // Hapus akun dari database
                            String deleteStatus = new Con_Admin().delete_Akun(Akun.ID_Admin, Akun.Password);

                            if(deleteStatus.equals("Akun Berhasil Dihapus!")){
                                JOptionPane.showMessageDialog( 
                                    null, 
                                    "Akun Berhasil Dihapus! Program akan keluar!",
                                    "Hapus Akun Berhasil",
                                    JOptionPane.INFORMATION_MESSAGE);
                                // Reset Akun
                                new Akun().reset();
                                
                                // Dispose Main Frame
                                Frame_Menu.closeTheMainFrame = 1;

                                // Call frame login
                                new Frame_Login();
                            } else {
                                option = 1;
                                JOptionPane.showMessageDialog( 
                                    null, 
                                    deleteStatus, 
                                    "ERROR",                
                                    JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog( 
                                null, 
                                "Password Yang Dimasukkan Salah!", 
                                "Hapus Akun Gagal",                
                                JOptionPane.WARNING_MESSAGE);
                        }
                    }
                } while (option != 1 && !passGet.equals(currPaswordCache));
                // Di loop apabila password salah, keluar apabila user pencet CANCEL
            } 
        }
    }
}

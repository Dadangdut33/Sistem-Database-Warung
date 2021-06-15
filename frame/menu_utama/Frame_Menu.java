package frame.menu_utama;
/**
 * Property of KELOMPOK-5 PL Kelas 2A
 * Author1: Fauzan Farhan Antoro
 * Author2: Alfanisa Safvira
 * Author3: Daffa Fawwaz Syadad
 */


import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import panels.*;
import saved_authentication.*;


public class Frame_Menu extends JFrame implements ActionListener {
    JButton jButton_Akun = new JButton();
    JButton jButton_Logout = new JButton();
    JButton jButton_Kategori = new JButton();
    JButton jButton_Barang = new JButton();
    JButton jButton_Pelanggan = new JButton();
    JButton jButton_Transaksi = new JButton();
    JButton jButton_LaporanTransaksi = new JButton();

    JLabel jLabel_Date = new JLabel();
    JLabel jLabel_Time = new JLabel();

    JPanel jPanel_Side = new JPanel();
    JPanel jPanel_Header = new JPanel();
    JPanel jPanel_Curr_Panel = new JPanel();

    public Frame_Menu(){
        // Pertama Panggil Frame login dulu

        
        // Frame menu settingnya
        this.getContentPane().setLayout(null);
        // this.setTitle("Database Warung " + new Akun().getNama_Pemilik());
        this.setTitle("Database Warung ");
        this.setResizable(false);
        // this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon(getClass().getResource("/assets/icons8-database-50.png")).getImage());
        this.setSize(1200, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(24, 40, 44));

        // PANEL KIRI
        jPanel_Side.setBackground(new Color(21, 25, 28));
        jPanel_Side.setBounds(0, 0, 89, 700);
        jPanel_Side.setLayout(null);
        this.getContentPane().add(jPanel_Side);
        
        // PANEL ATAS
        jPanel_Header.setBackground(new Color(21, 25, 28));
        jPanel_Header.setBounds(80, 0, 1120, 80);
        jPanel_Header.setLayout(null);
        this.getContentPane().add(jPanel_Header);

        // Jam 
        jLabel_Time.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel_Time.setBounds(20, 33, 220, 30);
        jLabel_Time.setForeground(Color.WHITE);

        // Tanggal
        jLabel_Date.setFont(new Font("Segoe UI", 1, 14));
        jLabel_Date.setBounds(20, 10, 230, 20);
        jLabel_Date.setForeground(Color.WHITE);

        dateAndTime();

        // PANEL KONTEN diawal adalah panel admin
        jPanel_Curr_Panel = new Panel_Akun_Info();
        this.getContentPane().add(jPanel_Curr_Panel);

        // Button Akun
        jButton_Akun.setBackground(new Color(40, 40, 44));
        jButton_Akun.setOpaque(false);
        jButton_Akun.setContentAreaFilled(false);
        jButton_Akun.setFocusPainted(false);
        jButton_Akun.setBorderPainted(true);
        jButton_Akun.setIcon(new ImageIcon(getClass().getResource("/assets/icons8-account-35.png")));
        jButton_Akun.setToolTipText("Menu Akun");
        jButton_Akun.setBounds(20, 18, 50, 50);

        // Button Logout
        jButton_Logout.setBackground(new Color(40, 40, 44));
        jButton_Logout.setOpaque(false);
        jButton_Logout.setContentAreaFilled(false);
        jButton_Logout.setFocusPainted(false);
        jButton_Logout.setBorderPainted(true);
        jButton_Logout.setIcon(new ImageIcon(getClass().getResource("/assets/icons8-export-35.png")));
        jButton_Logout.setToolTipText("Logout");
        jButton_Logout.setBounds(1040, 18, 50, 50);

        // Button Kategori
        jButton_Kategori.setBackground(new Color(40, 40, 44));
        jButton_Kategori.setOpaque(false);
        jButton_Kategori.setContentAreaFilled(false);
        jButton_Kategori.setFocusPainted(false);
        jButton_Kategori.setBorderPainted(true);
        jButton_Kategori.setIcon(new ImageIcon(getClass().getResource("/assets/icons8-in-inventory-35.png")));
        jButton_Kategori.setToolTipText("Menu Kategori");
        jButton_Kategori.setBounds(20, 190, 50, 50);

        // Button Barang
        jButton_Barang.setBackground(new Color(40, 40, 44));
        jButton_Barang.setOpaque(false);
        jButton_Barang.setContentAreaFilled(false);
        jButton_Barang.setFocusPainted(false);
        jButton_Barang.setBorderPainted(true);
        jButton_Barang.setIcon(new ImageIcon(getClass().getResource("/assets/icons8-warehouse-35.png")));
        jButton_Barang.setToolTipText("Menu Barang");
        jButton_Barang.setBounds(20, 260, 50, 50);

        // Button Pelanggan
        jButton_Pelanggan.setBackground(new Color(40, 40, 44));
        jButton_Pelanggan.setOpaque(false);
        jButton_Pelanggan.setContentAreaFilled(false);
        jButton_Pelanggan.setFocusPainted(false);
        jButton_Pelanggan.setBorderPainted(true);
        jButton_Pelanggan.setIcon(new ImageIcon(getClass().getResource("/assets/icons8-customer-insight-35.png")));
        jButton_Pelanggan.setToolTipText("Menu Pelanggan");
        jButton_Pelanggan.setBounds(20, 330, 50, 50);

        // Button Transaksi
        jButton_Transaksi.setBackground(new Color(40, 40, 44));
        jButton_Transaksi.setOpaque(false);
        jButton_Transaksi.setContentAreaFilled(false);
        jButton_Transaksi.setFocusPainted(false);
        jButton_Transaksi.setBorderPainted(true);
        jButton_Transaksi.setIcon(new ImageIcon(getClass().getResource("/assets/icons8-transaction-35.png")));
        jButton_Transaksi.setToolTipText("Menu Transaksi");
        jButton_Transaksi.setBounds(20, 400, 50, 50);

        // Button Laporan_Transaksi
        jButton_LaporanTransaksi.setBackground(new Color(40, 40, 44));
        jButton_LaporanTransaksi.setOpaque(false);
        jButton_LaporanTransaksi.setContentAreaFilled(false);
        jButton_LaporanTransaksi.setFocusPainted(false);
        jButton_LaporanTransaksi.setBorderPainted(true);
        jButton_LaporanTransaksi.setIcon(new ImageIcon(getClass().getResource("/assets/icons8-business-report-35.png")));
        jButton_LaporanTransaksi.setToolTipText("Menu Laporan Transaksi");
        jButton_LaporanTransaksi.setBounds(20, 470, 50, 50);

        // Add button ke panel atas
        jPanel_Header.add(jButton_Logout);
        jPanel_Header.add(jLabel_Time);
        jPanel_Header.add(jLabel_Date);

        // Add button ke panel kiri
        jPanel_Side.add(jButton_Akun);
        jPanel_Side.add(jButton_Kategori);
        jPanel_Side.add(jButton_Barang);
        jPanel_Side.add(jButton_Pelanggan);
        jPanel_Side.add(jButton_Transaksi);
        jPanel_Side.add(jButton_LaporanTransaksi);

        // Add action listener untuk tiap tombol
        jButton_Akun.addActionListener(this);
        jButton_Logout.addActionListener(this);
        jButton_Kategori.addActionListener(this);
        jButton_Barang.addActionListener(this);
        jButton_Pelanggan.addActionListener(this);
        jButton_Transaksi.addActionListener(this);
        jButton_LaporanTransaksi.addActionListener(this);

        // Set Visible
        this.setVisible(true);
    }

    public void login(){
        // Frame_Login LOGIN = new Frame_Login();
    }

    public void dateAndTime(){
        // Buat thread jadi dia jalan setiap 1 detik
        Thread clock = new Thread() {
            public void run() {
                try {
                    for(;;){
                        Calendar cal = new GregorianCalendar();
                        int day = cal.get(Calendar.DAY_OF_MONTH);
                        int month = cal.get(Calendar.MONTH);
                        int year = cal.get(Calendar.YEAR);

                        String Am_Pm;
                        if(cal.get(Calendar.AM_PM) == Calendar.PM) {
                            Am_Pm = "PM";
                        } else {
                            Am_Pm = "AM";
                        }
                
                        int second = cal.get(Calendar.SECOND);
                        int minute = cal.get(Calendar.MINUTE);
                        int hour = cal.get(Calendar.HOUR);
                
                        jLabel_Date.setText("Tanggal " + year + "/" + month + "/" + day);
                        jLabel_Time.setText(hour + ":" + minute + ":" + second + " " + Am_Pm);
    
                        sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        // Start threadnya
        clock.start();
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource().equals(jButton_Logout)){
            this.dispose();
            login();
            // FRAME DISPOSE
            // OPEN LOGIN
            // RESET STATUS SAVVVED AUTHENTICATION
            // ULANG LG PROSES YG AWAL2 DARI FRAME LOGIN
        } else   
        if(ae.getSource().equals(jButton_Akun)){
            this.getContentPane().remove(jPanel_Curr_Panel);
            jPanel_Curr_Panel = new Panel_Akun_Info();

            this.getContentPane().add(jPanel_Curr_Panel);
            repaint(); revalidate();
        } else
        if(ae.getSource().equals(jButton_Kategori)){
            this.getContentPane().remove(jPanel_Curr_Panel);
            jPanel_Curr_Panel = new Panel_Kategori();

            this.getContentPane().add(jPanel_Curr_Panel);
            repaint(); revalidate();
        } else
        if(ae.getSource().equals(jButton_Barang)){
            this.getContentPane().remove(jPanel_Curr_Panel);
            jPanel_Curr_Panel = new Panel_Barang();

            this.getContentPane().add(jPanel_Curr_Panel);
            repaint(); revalidate();
        } else
        if(ae.getSource().equals(jButton_Pelanggan)){
            this.getContentPane().remove(jPanel_Curr_Panel);
            jPanel_Curr_Panel = new Panel_Pelanggan();

            this.getContentPane().add(jPanel_Curr_Panel);
            repaint(); revalidate();
        } else
        if(ae.getSource().equals(jButton_Transaksi)){
            this.getContentPane().remove(jPanel_Curr_Panel);
            jPanel_Curr_Panel = new Panel_Transaksi();

            this.getContentPane().add(jPanel_Curr_Panel);
            repaint(); revalidate();
        } else
        if(ae.getSource().equals(jButton_LaporanTransaksi)){
            this.getContentPane().remove(jPanel_Curr_Panel);
            jPanel_Curr_Panel = new Panel_Transaksi_Laporan();

            this.getContentPane().add(jPanel_Curr_Panel);
            repaint(); revalidate();
        }
    }    
}

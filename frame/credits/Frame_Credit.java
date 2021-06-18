/**
 * Property of KELOMPOK-5 PL Kelas 2A
 * Author1: Fauzan Farhan Antoro
 * Author2: Alfanisa Safvira
 * Author3: Daffa Fawwaz Syadad
 */
package frame.credits;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Frame_Credit extends JFrame  {
    JLabel info_1 = new JLabel("Untuk Memenuhi Tugas Akhir PL");
    JLabel info_2 = new JLabel("UIN Syarif Hidayatullah Jakarta");
    JLabel kelompok = new JLabel("Kelompok 5 Kelas 2A");
    JLabel author_1 = new JLabel("Author 1: Fauzan Farhan Antoro    - 11200910000004");
    JLabel author_2 = new JLabel("Author 2: Alfanisa Safvira                - 11200910000002");
    JLabel author_3 = new JLabel("Author 3: Daffa Fawwaz Syaddad  - 11200910000003");
    JLabel repoLink = new JLabel("Repo Link: https://github.com/Dadangdut33/Sistem-Database-Warung");
    JLabel icon_Credits = new JLabel("ALL of the icons used are taken from ©icons8.com");

    JPanel panelBelakang = new JPanel();

    public Frame_Credit(){
        this.setLayout(null);
        this.setTitle("Credit");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(60, 63, 65));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setIconImage(new ImageIcon(getClass().getResource("/assets/icons8-database-50.png")).getImage());
        this.setSize(387, 297);

        // Panel Belakang
        panelBelakang.setLayout(null);
        panelBelakang.setBackground(new Color(24, 24, 24));
        panelBelakang.setBounds(5, 5, 360, 250);
        this.getContentPane().add(panelBelakang);

        // info 1
        info_1.setForeground(new Color(200,200,200));
        info_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
        info_1.setBounds(25, 10, 350, 30);
        panelBelakang.add(info_1);
        
        // info 2
        info_2.setForeground(new Color(200,200,200));
        info_2.setFont(new Font("Segoe UI", Font.BOLD, 20));
        info_2.setBounds(30, 30, 350, 30);
        panelBelakang.add(info_2);

        // Kelompok
        kelompok.setForeground(new Color(187,187,187));
        kelompok.setFont(new Font("Segoe UI", Font.BOLD, 20));
        kelompok.setBounds(70, 70, 350, 30);
        panelBelakang.add(kelompok);

        // author 1
        author_1.setForeground(new Color(187,187,187));
        author_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
        author_1.setBounds(30, 100, 350, 30);
        panelBelakang.add(author_1);        

        // author 2
        author_2.setForeground(new Color(187,187,187));
        author_2.setFont(new Font("Segoe UI", Font.BOLD, 12));
        author_2.setBounds(30, 130, 350, 30);
        panelBelakang.add(author_2);  

        // author 3
        author_3.setForeground(new Color(187,187,187));
        author_3.setFont(new Font("Segoe UI", Font.BOLD, 12));
        author_3.setBounds(30, 160, 350, 30);
        panelBelakang.add(author_3);  

        repoLink.setForeground(Color.WHITE);
        repoLink.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        repoLink.setBounds(25, 200, 350, 30);
        repoLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openRepoLink();
            }
        });
        panelBelakang.add(repoLink);  

        // icon credits
        icon_Credits.setForeground(Color.WHITE);
        icon_Credits.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        icon_Credits.setBounds(40, 220, 350, 30);
        icon_Credits.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openIcons8Links();
            }
        });
        panelBelakang.add(icon_Credits);  

        this.setVisible(true);
    }

    void openIcons8Links(){
        try {
            Desktop.getDesktop().browse(new URI("https://icons8.com/"));
        } catch (IOException | URISyntaxException e1) { /* Ignored */ }
    }

    void openRepoLink(){
        try {
            Desktop.getDesktop().browse(new URI("https://github.com/Dadangdut33/Sistem-Database-Warung"));
        } catch (IOException | URISyntaxException e1) { /* Ignored */ }
    }
}

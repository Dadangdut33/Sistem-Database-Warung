/**
 * Property of KELOMPOK-5 PL Kelas 2A
 * Author1: Fauzan Farhan Antoro
 * Author2: Alfanisa Safvira
 * Author3: Daffa Fawwaz Syadad
 * Original splashscreen from: https://stackoverflow.com/questions/11399971/make-splash-screen-with-progress-bar-like-eclipse
 */

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.*;
import javax.swing.UIManager.*;

import frame.authentication.Frame_Login;

public class Run extends JWindow {

    static boolean isRegistered;
    private static JProgressBar progressBar = new JProgressBar();
    private static Run execute;
    private static int count;
    private static Timer timer1;
    private static int x = 0;
    JLabel label = new JLabel();
    JLabel label_2 = new JLabel();
    int randomLoadNum = ThreadLocalRandom.current().nextInt(50, 60 + 1);

    public Run() {
        Container container = getContentPane();
        container.setLayout(null);

        // Inisiasi nimbus
        initiateNimbus();

        // Set Icon image
        this.setIconImage(new ImageIcon(getClass().getResource("/assets/icons8-database-50.png")).getImage());

        // Panel
        JPanel panel = new JPanel();
        panel.setBorder(new javax.swing.border.EtchedBorder());
        panel.setBackground(new Color(21, 25, 28));
        panel.setBounds(10, 10, 348, 150);
        panel.setLayout(null);
        container.add(panel);

        // Label
        label.setText("Loading Program");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Verdana", Font.BOLD, 18));
        label.setBounds(80, 40, 280, 30);
        label_2.setText("Harap Menunggu");
        label_2.setForeground(Color.WHITE);
        label_2.setFont(new Font("Verdana", Font.BOLD, 18));
        label_2.setBounds(70, 80, 280, 30);
        
        // Tambahkan label ke panel
        panel.add(label);
        panel.add(label_2);

        // Proggressbar
        progressBar.setMaximum(60);
        progressBar.setBounds(55, 180, 250, 15);
        container.add(progressBar);
        container.setBackground(new Color(24, 40, 44));
        loadProgressBar();
        setSize(370, 215);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadProgressBar() {
        ActionListener al = new ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                count++;
                
                if(count % 3 == 0){
                    switch (x) {
                        case 0:
                            label_2.setText("Harap Menunggu"); 
                            x++;
                            break;
                        case 1:
                            label_2.setText("Harap Menunggu."); 
                            x++;
                            break;
                        case 2:
                            label_2.setText("Harap Menunggu.."); 
                            x++;
                            break;
                        case 3:
                            label_2.setText("Harap Menunggu..."); 
                            x++;
                            break;
                        default:
                            x = 0;
                            break;
                    }
                    
                }

                progressBar.setValue(count);

                if (count == randomLoadNum) {
                    // Loading selesai, panggil frame login
                    new Frame_Login();

                    // Stop timer
                    timer1.stop();

                    // Dispose splashscreen
                    execute.dispose();
                }
            }
        };
        timer1 = new Timer(50, al);
        timer1.start();
    }

    // Inisiasi nimbus (tema agar lebih rounded icon2nya)
    private void initiateNimbus(){
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) { /* Ignored */ }
    }

    public static void main(String[] args) {
        execute = new Run();
    }
};
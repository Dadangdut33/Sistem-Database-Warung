

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.UIManager.*;

import frame.authentication.Frame_Login;

public class Run extends JWindow {

    static boolean isRegistered;
    private static JProgressBar progressBar = new JProgressBar();
    private static Run execute;
    private static int count;
    private static Timer timer1;

    public Run() {
        Container container = getContentPane();
        container.setLayout(null);

        initiateNimbus();
        JPanel panel = new JPanel();
        panel.setBorder(new javax.swing.border.EtchedBorder());
        panel.setBackground(new Color(21, 25, 28));
        panel.setBounds(10, 10, 348, 150);
        panel.setLayout(null);
        container.add(panel);

        JLabel label = new JLabel("Loading Program");
        JLabel label_2 = new JLabel("Harap Mengunggu...");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Verdana", Font.BOLD, 18));
        label.setBounds(80, 40, 280, 30);
        label_2.setForeground(Color.WHITE);
        label_2.setFont(new Font("Verdana", Font.BOLD, 18));
        label_2.setBounds(70, 80, 280, 30);
        panel.add(label);
        panel.add(label_2);

        progressBar.setMaximum(50);
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

                progressBar.setValue(count);

                if (count == 50) {
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
/**
 * Property of KELOMPOK-5 PL Kelas 2A
 * Author1: Fauzan Farhan Antoro
 * Author2: Alfanisa Safvira
 * Author3: Daffa Fawwaz Syadad
 */
package panels;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class Panel_Transaksi extends JPanel implements ActionListener{
    public Panel_Transaksi(){
        this.setLayout(null);
        this.setBounds(80, 70, 1120, 630);
        this.setBackground(new Color(80, 40, 44));
        // this.setBackground(new Color(24, 40, 44));
        System.out.println("TRANSAKSI LAPORAN");

    }

    @Override
    public void actionPerformed(ActionEvent ae){

    }
}

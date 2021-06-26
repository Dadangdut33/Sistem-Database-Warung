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
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.table.*;

import frame.transaksi.*;
import koneksi.Con_Laporan;

import java.util.List;

import saved_authentication.Akun;

public class Panel_LaporanTransaksi extends JPanel implements ActionListener {
    JLabel jLabel_judul = new JLabel();
    JLabel jLabel_search_TbAtas = new JLabel();
    JLabel jLabel_search_TbBawah = new JLabel();
    JLabel jLabel_kolom_TbAtas = new JLabel();
    JLabel jLabel_kolom_TbBawah = new JLabel();
    JLabel jLabel_mode_TbBawah = new JLabel();

    JTextField jField_SearchTbAtas = new JTextField();
    JTextField jField_SearchTbBawah = new JTextField();

    JComboBox<String> jBox_Kolom_TbAtas = new JComboBox<>();
    JComboBox<String> jBox_Kolom_TbBawah = new JComboBox<>();
    JComboBox<String> jBox_Mode_TbBawah = new JComboBox<>();

    // Formatter waktu
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd - (HH:mm)");

    // Kolom tabel
    String kolomTbAtas[] = {"Kode Pesanan", "Kode Pelanggan", "Kode Barang", "Harga Barang", "Jumlah", "Total", "Tanggal"};
    String kolomTbBawah[] = {"Tanggal", "Total Pendapatan", "Total Pesanan"};

    // Table
    DefaultTableModel tModelTbAtas;
    DefaultTableModel tModelTbBawah;

    JTable tbAtas = new JTable();
    JTable tbBawah = new JTable();

    JScrollPane spaneTbAtas = new JScrollPane();
    JScrollPane spaneTbBawah = new JScrollPane();
    
    TableRowSorter<DefaultTableModel> sorterTbAtas;
    TableRowSorter<DefaultTableModel> sorterTbBawah;

    // Button Hapus
    JButton jButton_Delete = new JButton();
    JButton jButton_Add = new JButton();

    // Get currentWindow
    Object currentWindow = this;

    // Current sort mode
    int sorterModeAtas = 0;
    int sorterModeBawah = 0;

    public Panel_LaporanTransaksi(){
        this.setLayout(null);
        this.setBounds(80, 70, 1120, 630);
        this.setBackground(new Color(24, 40, 44));
        
        Panel_Akun_Info.exitPanelAkun = true;
        tModelTbAtas = new DefaultTableModel(null, kolomTbAtas) {
            @Override
            public boolean isCellEditable(int row, int column) { // Disable cell editing agar tidak bisa diedit2 datanya
                return false;
            }
        };
    
        tModelTbBawah = new DefaultTableModel(null, kolomTbBawah) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        isiTabelAtas();
        isiTabelBawahByDate();

        // Judul panel
        jLabel_judul.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jLabel_judul.setForeground(Color.WHITE);
        jLabel_judul.setBounds(20, 20, 220, 25);
        jLabel_judul.setText("Tabel Laporan Transaksi");
        this.add(jLabel_judul);

        // Label untuk search tabel atas
        jLabel_search_TbAtas.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        jLabel_search_TbAtas.setForeground(Color.WHITE);
        jLabel_search_TbAtas.setBounds(22, 60, 40, 20);
        jLabel_search_TbAtas.setText("Search");
        this.add(jLabel_search_TbAtas);

        // Textfield untuk search tabel atas
        jField_SearchTbAtas.setBackground(new Color(69, 73, 74));
        jField_SearchTbAtas.setForeground(Color.WHITE);
        jField_SearchTbAtas.setBounds(70, 60, 157, 22);
        jField_SearchTbAtas.setText("");
        jField_SearchTbAtas.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jField_SearchTbAtas.setCaretColor(Color.WHITE);
        this.add(jField_SearchTbAtas);

        // Label kolom atas
        jLabel_kolom_TbAtas.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        jLabel_kolom_TbAtas.setForeground(Color.WHITE);
        jLabel_kolom_TbAtas.setBounds(237, 60, 40, 20);
        jLabel_kolom_TbAtas.setText("Kolom");
        this.add(jLabel_kolom_TbAtas);

        // Kombobox untuk search kolom atas
        jBox_Kolom_TbAtas.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jBox_Kolom_TbAtas.setBackground(new Color(69, 73, 74));
        jBox_Kolom_TbAtas.setForeground(Color.WHITE);
        jBox_Kolom_TbAtas.setBounds(285, 60, 120, 22);
        jBox_Kolom_TbAtas.setModel(new DefaultComboBoxModel<String>(kolomTbAtas));
        this.add(jBox_Kolom_TbAtas);
        
        // Tabel atas
        tbAtas.setModel(tModelTbAtas);
        tbAtas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbAtas.getTableHeader().setReorderingAllowed(false);
        tbAtas.setBackground(new Color(69, 73, 74));
        tbAtas.setForeground(new Color(210, 210, 210));
        tbAtas.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tbAtas.setGridColor(new Color(76, 81, 82));
        tbAtas.setShowHorizontalLines(false);
        tbAtas.setSelectionBackground(new Color(13, 41, 62));
        tbAtas.setSelectionForeground(new Color(187, 187, 187));
        sorterTbAtas = new TableRowSorter<DefaultTableModel>(tModelTbAtas);
        tbAtas.setRowSorter(sorterTbAtas);

        // Spane atas
        spaneTbAtas.setBackground(new Color(69, 73, 74));
        spaneTbAtas.setForeground(new Color(187, 187, 187));
        spaneTbAtas.setBounds(20, 90, 1070, 240);
        spaneTbAtas.setBackground(new Color(69, 73, 74));
        spaneTbAtas.getViewport().add(tbAtas, null);
        this.add(spaneTbAtas);
        
        // Listener untuk search tb atas
        jField_SearchTbAtas.getDocument().addDocumentListener(new DocumentListener() {
            // Setiap berubah ini jalanin function search
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(jField_SearchTbAtas.getText());
            }
            @Override
            public void removeUpdate(DocumentEvent e) { 
                search(jField_SearchTbAtas.getText());
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                search(jField_SearchTbAtas.getText());
            }
            public void search(String str) { // fungsi untuk search tabel
                if (str.length() == 0) {
                    sorterTbAtas.setRowFilter(null); // Kalau tidak search apa2 jadiin null
                } else {
                    sorterTbAtas.setRowFilter(RowFilter.regexFilter(str, sorterModeAtas));
                }
            }
        });

        // Label search bawah
        jLabel_search_TbBawah.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        jLabel_search_TbBawah.setForeground(Color.WHITE);
        jLabel_search_TbBawah.setBounds(22, 340, 40, 20);
        jLabel_search_TbBawah.setText("Search");
        this.add(jLabel_search_TbBawah);

        // Textfield search bawah
        jField_SearchTbBawah.setBackground(new Color(69, 73, 74));
        jField_SearchTbBawah.setForeground(Color.WHITE);
        jField_SearchTbBawah.setBounds(70, 340, 157, 22);
        jField_SearchTbBawah.setText("");
        jField_SearchTbBawah.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jField_SearchTbBawah.setCaretColor(Color.WHITE);
        this.add(jField_SearchTbBawah);

        // Label kolom bawah
        jLabel_kolom_TbBawah.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        jLabel_kolom_TbBawah.setForeground(Color.WHITE);
        jLabel_kolom_TbBawah.setBounds(237, 340, 40, 20);
        jLabel_kolom_TbBawah.setText("Kolom");
        this.add(jLabel_kolom_TbBawah);

        // Combobox search kolom bawah
        jBox_Kolom_TbBawah.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jBox_Kolom_TbBawah.setBackground(new Color(69, 73, 74));
        jBox_Kolom_TbBawah.setForeground(Color.WHITE);
        jBox_Kolom_TbBawah.setBounds(285, 340, 120, 22);
        jBox_Kolom_TbBawah.setModel(new DefaultComboBoxModel<String>(kolomTbBawah));
        this.add(jBox_Kolom_TbBawah);

        // Label mode bawah
        jLabel_mode_TbBawah.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        jLabel_mode_TbBawah.setForeground(Color.WHITE);
        jLabel_mode_TbBawah.setBounds(415, 340, 40, 20);
        jLabel_mode_TbBawah.setText("Mode");
        this.add(jLabel_mode_TbBawah);

        // Combobox mode bawah
        jBox_Mode_TbBawah.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jBox_Mode_TbBawah.setBackground(new Color(69, 73, 74));
        jBox_Mode_TbBawah.setForeground(Color.WHITE);
        jBox_Mode_TbBawah.setBounds(460, 340, 120, 22);
        jBox_Mode_TbBawah.addItem("Group by Date");
        jBox_Mode_TbBawah.addItem("Group by Month");
        jBox_Mode_TbBawah.addItem("Group by Year");
        this.add(jBox_Mode_TbBawah);

        // Tabel bawah
        tbBawah.setModel(tModelTbBawah);
        tbBawah.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbBawah.getTableHeader().setReorderingAllowed(false);
        tbBawah.setBackground(new Color(69, 73, 74));
        tbBawah.setForeground(new Color(210, 210, 210));
        tbBawah.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tbBawah.setGridColor(new Color(76, 81, 82));
        tbBawah.setShowHorizontalLines(false);
        tbBawah.setSelectionBackground(new Color(13, 41, 62));
        tbBawah.setSelectionForeground(new Color(187, 187, 187));
        sorterTbBawah = new TableRowSorter<DefaultTableModel>(tModelTbBawah);
        tbBawah.setRowSorter(sorterTbBawah);

        // Spane bawah
        spaneTbBawah.setBackground(new Color(69, 73, 74));
        spaneTbBawah.setForeground(new Color(187, 187, 187));
        spaneTbBawah.setBounds(20, 370, 1070, 200);
        spaneTbBawah.getViewport().add(tbBawah, null);
        this.add(spaneTbBawah);

        // Add action listener untuk search bawah
        jField_SearchTbBawah.getDocument().addDocumentListener(new DocumentListener() {
            // Setiap berubah ini jalanin function search
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(jField_SearchTbBawah.getText());
            }
            @Override
            public void removeUpdate(DocumentEvent e) { 
                search(jField_SearchTbBawah.getText());
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                search(jField_SearchTbBawah.getText());
            }
            public void search(String str) { // fungsi untuk search tabel
                if (str.length() == 0) {
                    sorterTbBawah.setRowFilter(null); // Kalau tidak search apa2 jadiin null
                } else {
                    sorterTbBawah.setRowFilter(RowFilter.regexFilter(str, sorterModeBawah));
                }
            }
        });

        // Button Edit
        jButton_Add.setBackground(new Color(40, 40, 44));
        jButton_Add.setOpaque(false);
        jButton_Add.setContentAreaFilled(false);
        jButton_Add.setFocusPainted(false);
        jButton_Add.setBorderPainted(false);
        jButton_Add.setIcon(new ImageIcon(getClass().getResource("/assets/icons8-add-row-30.png")));
        jButton_Add.setToolTipText("Add Laporan");
        jButton_Add.setBounds(975, 40, 50, 50);
        this.add(jButton_Add);
        
        // Button Hapus
        jButton_Delete.setBackground(new Color(40, 40, 44));
        jButton_Delete.setOpaque(false);
        jButton_Delete.setContentAreaFilled(false);
        jButton_Delete.setFocusPainted(false);
        jButton_Delete.setBorderPainted(false);
        jButton_Delete.setIcon(new ImageIcon(getClass().getResource("/assets/icons8-delete-row-30.png")));
        jButton_Delete.setToolTipText("Hapus Laporan");
        jButton_Delete.setBounds(1035, 40, 50, 50);
        this.add(jButton_Delete);

        // Add Action Listener
        jButton_Add.addActionListener(this);
        jButton_Delete.addActionListener(this);
        jBox_Kolom_TbAtas.addActionListener(this);
        jBox_Kolom_TbBawah.addActionListener(this);
        jBox_Mode_TbBawah.addActionListener(this);
    }

    void refreshAll(){
        clearTabelAtas();
        clearTabelBawah();
        isiTabelAtas();
        isiTabelBawahByDate();
        jField_SearchTbAtas.setText("");
        jField_SearchTbBawah.setText("");
        jBox_Kolom_TbAtas.setSelectedIndex(0);
        jBox_Kolom_TbBawah.setSelectedIndex(0);
        jBox_Mode_TbBawah.setSelectedIndex(0);
    }

    void clearTabelAtas(){
        int barisTbAtas = tModelTbAtas.getRowCount();
        for(int i = 0; i < barisTbAtas; i++){
            tModelTbAtas.removeRow(0);
        }
    }

    void clearTabelBawah(){
        int barisTbBawah = tModelTbBawah.getRowCount();
        for(int i = 0; i < barisTbBawah; i++){
            tModelTbBawah.removeRow(0);
        }
    }

    void isiTabelAtas(){
        List<Object> data = new Con_Laporan().get_LaporanPesanan(Akun.ID_Admin);
        Object[] parsedData = (Object[]) data.toArray(new Object[0]);
        
        // Diisi dengan semua data di convert jadi string
        for (int i = 0; i < parsedData.length; i = i + 7) {
            String[] isi = { parsedData[i].toString(), parsedData[i+1].toString(), parsedData[i+2].toString(), parsedData[i+3].toString(), 
                             parsedData[i+4].toString(), parsedData[i+5].toString(), dateFormat.format(parsedData[i+6]) };

            tModelTbAtas.addRow(isi);
        }
    }

    void isiTabelBawahByDate(){
        List<Object> data = new Con_Laporan().get_LaporanPendapatan_ByDate(Akun.ID_Admin);
        Object[] parsedData = (Object[]) data.toArray(new Object[0]);
        
        for (int i = 0; i < parsedData.length; i = i + 5) {
            // Tanggal isinya tahun-bulan-tanggal
            String[] isi = { parsedData[i].toString() + "-" + parsedData[i+1].toString() + "-" + parsedData[i+2].toString(),
                             parsedData[i+3].toString(), parsedData[i+4].toString()};
            tModelTbBawah.addRow(isi);
        }
    }

    void isiTabelBawahByMonth(){
        List<Object> data = new Con_Laporan().get_LaporanPendapatan_ByMonth(Akun.ID_Admin);
        Object[] parsedData = (Object[]) data.toArray(new Object[0]);
        
        for (int i = 0; i < parsedData.length; i = i + 4) {
            // Tgl isinya tahun-bulan
            String[] isi = { parsedData[i].toString() + "-" + parsedData[i+1].toString(), parsedData[i+2].toString(),
                             parsedData[i+3].toString()};
            tModelTbBawah.addRow(isi);
        }
    }

    void isiTabelBawahByYear(){
        List<Object> data = new Con_Laporan().get_LaporanPendapatan_ByYear(Akun.ID_Admin);
        Object[] parsedData = (Object[]) data.toArray(new Object[0]);
        
        for (int i = 0; i < parsedData.length; i = i + 3) {
            // Tgl isinya tahun
            String[] isi = { parsedData[i].toString(), parsedData[i+1].toString(), parsedData[i+2].toString()};
            tModelTbBawah.addRow(isi);
        }
    }

    void changeModeTabelBawah(){
        int mode = jBox_Mode_TbBawah.getSelectedIndex();

        switch (mode) {
            case 0:
                clearTabelBawah();
                isiTabelBawahByDate();
                break;
            case 1:
                clearTabelBawah();
                isiTabelBawahByMonth();
                break;
            case 2:
                clearTabelBawah();
                isiTabelBawahByYear();
                break;
            default:
                break;
        }
    }

    void openFrame(JFrame openedFrame){
        JFrame mainFrame = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this);
        openedFrame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                refreshAll();
                mainFrame.setEnabled(true);
            }
        });
        mainFrame.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource().equals(jBox_Kolom_TbAtas)){
            sorterModeAtas = jBox_Kolom_TbAtas.getSelectedIndex();
        } else
        if(ae.getSource().equals(jBox_Kolom_TbBawah)){
            sorterModeBawah = jBox_Kolom_TbBawah.getSelectedIndex();
        } else
        if(ae.getSource().equals(jBox_Mode_TbBawah)){
            changeModeTabelBawah();
        } else 
        if(ae.getSource().equals(jButton_Add)){
            openFrame(new Frame_Add_Transaksi());
        } else 
        if(ae.getSource().equals(jButton_Delete)){
            openFrame(new Frame_Delete_Transaksi());
        }
    }
}

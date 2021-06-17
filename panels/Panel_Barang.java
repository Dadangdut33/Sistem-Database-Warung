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
import javax.swing.table.*;

import frame.barang.*;
import frame.menu_utama.Frame_Menu;
import koneksi.Con_Barang;

import java.util.List;

import saved_authentication.Akun;

public class Panel_Barang extends JPanel implements ActionListener {
    // Label
    JLabel jLabel_judul = new JLabel();
    JLabel jLabel_search_TbAtas = new JLabel();
    JLabel jLabel_kolom_TbAtas = new JLabel();

    // Textfield
    JTextField jField_SearchTbAtas = new JTextField();

    JComboBox<String> jBox_Kolom_TbAtas = new JComboBox<>();

    // Kolom tabel
    String kolomTbAtas[] = {"Kode Kategori", "Kode Barang", "Nama Barang", "Harga Barang", "Stok Barang"};

    // Table
    DefaultTableModel tModelTbAtas;
    DefaultTableModel tModelTbBawah;

    JTable tbAtas = new JTable();

    JScrollPane spaneTbAtas = new JScrollPane();
    
    TableRowSorter<DefaultTableModel> sorterTbAtas;

    // Button
    JButton jButton_Add = new JButton();
    JButton jButton_Edit = new JButton();
    JButton jButton_Delete = new JButton();

    // Get currentWindow
    Object currentWindow = this;

    // Current sort mode
    int sorterModeAtas = 0;

    public Panel_Barang(){
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
    
        isiTabelAtas();

        // Judul panel
        jLabel_judul.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jLabel_judul.setForeground(Color.WHITE);
        jLabel_judul.setBounds(20, 20, 270, 25);
        jLabel_judul.setText("Tabel Pelanggan/Customer");
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
        spaneTbAtas.setBounds(20, 90, 1070, 480);
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

        // Button Hapus
        jButton_Add.setBackground(new Color(40, 40, 44));
        jButton_Add.setOpaque(false);
        jButton_Add.setContentAreaFilled(false);
        jButton_Add.setFocusPainted(false);
        jButton_Add.setBorderPainted(false);
        jButton_Add.setIcon(new ImageIcon(getClass().getResource("/assets/icons8-add-row-30.png")));
        jButton_Add.setToolTipText("Add Kategori");
        jButton_Add.setBounds(915, 40, 50, 50);
        this.add(jButton_Add);

        // Button Hapus
        jButton_Edit.setBackground(new Color(40, 40, 44));
        jButton_Edit.setOpaque(false);
        jButton_Edit.setContentAreaFilled(false);
        jButton_Edit.setFocusPainted(false);
        jButton_Edit.setBorderPainted(false);
        jButton_Edit.setIcon(new ImageIcon(getClass().getResource("/assets/icons8-edit-row-30.png")));
        jButton_Edit.setToolTipText("Edit Kategori");
        jButton_Edit.setBounds(975, 40, 50, 50);
        this.add(jButton_Edit);

        // Button Hapus
        jButton_Delete.setBackground(new Color(40, 40, 44));
        jButton_Delete.setOpaque(false);
        jButton_Delete.setContentAreaFilled(false);
        jButton_Delete.setFocusPainted(false);
        jButton_Delete.setBorderPainted(false);
        jButton_Delete.setIcon(new ImageIcon(getClass().getResource("/assets/icons8-delete-row-30.png")));
        jButton_Delete.setToolTipText("Delete Kategori");
        jButton_Delete.setBounds(1035, 40, 50, 50);
        this.add(jButton_Delete);

        // Add Action Listener
        jButton_Add.addActionListener(this);
        jButton_Edit.addActionListener(this);
        jButton_Delete.addActionListener(this);
        jBox_Kolom_TbAtas.addActionListener(this);
    }

    void refreshAll(){
        clearTabelAtas();
        isiTabelAtas();
        jField_SearchTbAtas.setText("");
        jBox_Kolom_TbAtas.setSelectedIndex(0);
    }

    void clearTabelAtas(){
        int barisTbAtas = tModelTbAtas.getRowCount();
        for(int i = 0; i < barisTbAtas; i++){
            tModelTbAtas.removeRow(0);
        }
    }

    void isiTabelAtas(){
        List<Object> data = new Con_Barang().get_BarangTable(Akun.ID_Admin);
        Object[] parsedData = (Object[]) data.toArray(new Object[0]);
        
        // Diisi dengan semua data di convert jadi string
        for (int i = 0; i < parsedData.length; i = i + 5) {
            String[] isi = { parsedData[i].toString(), parsedData[i+1].toString(), String.valueOf(parsedData[i+2]),
                             String.valueOf(parsedData[i+3]), parsedData[i+4].toString() };
            tModelTbAtas.addRow(isi);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource().equals(jBox_Kolom_TbAtas)){
            sorterModeAtas = jBox_Kolom_TbAtas.getSelectedIndex();
        } else 
        if(ae.getSource().equals(jButton_Add)){
            new Frame_Add_Barang().addWindowListener(new WindowAdapter(){
                @Override
                public void windowClosing(WindowEvent e) {
                    refreshAll();
                    Frame_Menu.anotherFrameIsOpen = 0;
                }
            });
            Frame_Menu.anotherFrameIsOpen = 1;
        } else 
        if(ae.getSource().equals(jButton_Edit)){
            new Frame_Edit_Barang().addWindowListener(new WindowAdapter(){
                @Override
                public void windowClosing(WindowEvent e) {
                    refreshAll();
                    Frame_Menu.anotherFrameIsOpen = 0;
                }
            });
            Frame_Menu.anotherFrameIsOpen = 1;
        } else 
        if(ae.getSource().equals(jButton_Delete)){
            new Frame_Delete_Barang().addWindowListener(new WindowAdapter(){
                @Override
                public void windowClosing(WindowEvent e) { 
                    refreshAll();
                    Frame_Menu.anotherFrameIsOpen = 0;
                }
            });
            Frame_Menu.anotherFrameIsOpen = 1;
        }
    }
}

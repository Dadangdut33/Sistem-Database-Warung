/**
 * Property of KELOMPOK-5 PL Kelas 2A
 * Author1: Fauzan Farhan Antoro
 * Author2: Alfanisa Safvira
 * Author3: Daffa Fawwaz Syadad
 */
package tools;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import koneksi.Con_Laporan;
import saved_authentication.Akun;


public class CreatePdf {
    Document doc = new Document();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd (HH_mm_ss)");
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd - (HH:mm)");

    Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL, BaseColor.RED);
    Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
    Font subberFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    Font smallNormal = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
    Font smallerNormal = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL);
    PdfWriter writer;

    public String exportSelectedDateToPdf(String startDate, String endDate, String periode){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int mode = 2;
        String status;
        try {
            writer = PdfWriter.getInstance(doc, new FileOutputStream("export/Laporan_id_" + Akun.ID_Admin.substring(0, 3).toUpperCase() + "_tanggal_" + sdf.format(timestamp) + ".pdf"));
            doc.open();

            // Add Meta data
            addMetada(periode);

            // Ada title page
            infoEksport(periode, mode);

            // Isi tabel
            dataTabelLaporanPesanan(mode, startDate, endDate);

            // Close writer and docs
            doc.close();
            writer.close();

            // Set Status
            status = "Laporan Berhasil Di-export ke PDF!";
        } catch(DocumentException ex){
            ex.printStackTrace();
            status = ex.getMessage();
        } catch(FileNotFoundException ex){
            ex.printStackTrace();
            status = ex.getMessage();
        } catch(Exception ex){
            ex.printStackTrace();
            status = ex.getMessage();
        }

        return status;
    }

    public String exportAllToPdf(String periode){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int mode = 1;
        String startDate = "", endDate = "";
        String status;
        try {
            writer = PdfWriter.getInstance(doc, new FileOutputStream("export/Laporan_id_" + Akun.ID_Admin.substring(0, 3).toUpperCase() + "_tanggal_" + sdf.format(timestamp) + ".pdf"));
            doc.open();
            
            // Add Metadata
            addMetada(periode);

            // Add Title Page
            infoEksport(periode, mode);

            // Isi Tabel
            dataTabelLaporanPesanan(mode, startDate, endDate);
            
            // Close writer and docs
            doc.close();
            writer.close();

            // Set Status
            status = "Laporan Berhasil Di-export ke PDF!";

        } catch(DocumentException ex){
            ex.printStackTrace();
            status = ex.getMessage();
        } catch(FileNotFoundException ex){
            ex.printStackTrace();
            status = ex.getMessage();
        }

        return status;
    }

    void infoEksport(String periode, int mode) throws DocumentException {
        Paragraph preface = new Paragraph();
        
        // Add Empty Line
        addEmptyLine(preface, 1);
        
        // Header (big)
        Paragraph title = new Paragraph("Laporan Pesanan & Pendapatan\n\"" + Akun.Nama_Toko + "\"", catFont);
        title.setAlignment(Element.ALIGN_CENTER);
        preface.add(title);

        addEmptyLine(preface, 1);

        // Mode 1 all 2 periode
        String modeString = "";
        if(mode == 1) {
            modeString = "Export Semua Data";
        } else
        if (mode == 2) {
            modeString = "Export Data Di Periode Tertentu";
        }

        preface.add(new Paragraph("Detail Export", subFont));
        preface.add(new Paragraph(
                "Laporan Di Export Dari User  : " + Akun.ID_Admin +
                "\nDi Komputer Milik                  : " + System.getProperty("user.name") + 
                "\nPada Tanggal                           : " + 
                new Date() + 
                "\nMode Export                            : " + modeString,
                smallNormal));
        addEmptyLine(preface, 1);

        preface.add(new Paragraph("Detail Toko", subFont));
        preface.add(new Paragraph(
            "Pemilik               : " + Akun.Nama_Pemilik +
            "\nAlamat                : " + Akun.Alamat_Toko +
            "\nNomor Telepon  : " + Akun.Nomor_Telepon,
            smallNormal));
        addEmptyLine(preface, 1);

        preface.add(new Paragraph(
                "*NOTE:\nHasil Eksport PDF ini berisi data dari laporan pesanan dan laporan pendapatan periode " + periode,
                redFont));

        addEmptyLine(preface, 1);

        doc.add(preface);
    }

    void dataTabelLaporanPesanan(int mode, String startDate, String endDate) throws DocumentException {
        Paragraph contentLaporanPesanan = new Paragraph();
        
        // Judul Tabel
        contentLaporanPesanan.add(new Paragraph("1. Tabel Data Laporan Pesanan", subFont));

        // Isi Tabel lalu beri spasi
        addEmptyLine(contentLaporanPesanan, 1);
        tableLaporanPesanan(mode, startDate, endDate, contentLaporanPesanan);
        addEmptyLine(contentLaporanPesanan, 1);

        // Judul Tabel
        contentLaporanPesanan.add(new Paragraph("2. Tabel Data Laporan Pendapatan", subFont));
        contentLaporanPesanan.add(new Paragraph("2.1 Laporan Pendapatan Berdasarkan Hari", subberFont));

        // Tabel pendapatan hari
        addEmptyLine(contentLaporanPesanan, 1);
        tableLaporanPendapatanByDate(mode, startDate, endDate, contentLaporanPesanan);
        addEmptyLine(contentLaporanPesanan, 1);

        // Judul
        contentLaporanPesanan.add(new Paragraph("2.2 Laporan Pendapatan Berdasarkan Bulan", subberFont));

        // Tabel Pendapatan Bulan
        addEmptyLine(contentLaporanPesanan, 1);
        tableLaporanPendapatanByMonth(mode, startDate, endDate, contentLaporanPesanan);
        addEmptyLine(contentLaporanPesanan, 1);

        // Judul
        contentLaporanPesanan.add(new Paragraph("2.3 Laporan Pendapatan Berdasarkan Tahun", subberFont));

        // Tabel Pendapatan Tahun
        addEmptyLine(contentLaporanPesanan, 1);
        tableLaporanPendapatanByYear(mode, startDate, endDate, contentLaporanPesanan);
        addEmptyLine(contentLaporanPesanan, 1);

        doc.add(contentLaporanPesanan);
    }

    void tableLaporanPesanan(int mode, String startDate, String endDate, Paragraph paragraph) throws DocumentException {
        // 1 = all
        // 2 = periode
        PdfPTable table = new PdfPTable(7);

        // Cek kepotong atau tidak
        checkPageForTable(table);

        String[] headerKolom = {"Kode Pesanan", "Nama Pelanggan", "Nama Barang", "Harga Barang", "Jumlah", "Total", "Tanggal"};
        
        PdfPCell c1;

        for (int i = 0; i < headerKolom.length; i++) {
            Phrase headerCell = new Phrase();
            headerCell.setFont(smallNormal);
            headerCell.add(headerKolom[i]);
    
            c1 = new PdfPCell(headerCell);
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
        }

        table.setHeaderRows(1);
        table.setTotalWidth(580);
        table.setLockedWidth(true);

        table.setWidths(new int[] {3, 7, 7, 5, 3, 5, 8});

        if (mode == 1) {
            List<Object> data = new Con_Laporan().get_LaporanPesananExport(Akun.ID_Admin);
            Object[] parsedData = (Object[]) data.toArray(new Object[0]);
            
            // Diisi dengan semua data di convert jadi string
            for (int i = 0; i < parsedData.length; i++) {
                Phrase isiTabel = new Phrase();
                isiTabel.setFont(smallerNormal);
                isiTabel.add(parsedData[i].toString() + "\n \u00a0"); // Whitespace diakhir
                
                c1 = new PdfPCell(isiTabel);
                c1.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(c1);
            }
        } else
        if (mode == 2) {
            List<Object> data = new Con_Laporan().get_LaporanPesananPeriodExport(Akun.ID_Admin, startDate, endDate);
            Object[] parsedData = (Object[]) data.toArray(new Object[0]);
            
            // Diisi dengan semua data di convert jadi string
            for (int i = 0; i < parsedData.length; i++) {
                parsedData[i].toString();
                Phrase isiTabel = new Phrase();
                isiTabel.setFont(smallerNormal);
                isiTabel.add(parsedData[i].toString() + "\n \u00a0"); // Whitespace diakhir
                
                c1 = new PdfPCell(isiTabel);
                c1.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(c1);
            }
        }

        paragraph.add(table);
    }

    void tableLaporanPendapatanByDate(int mode, String startDate, String endDate, Paragraph paragraph) throws DocumentException {
        // 1 = all
        // 2 = periode
        PdfPTable table = new PdfPTable(3);

        // Cek tabel kepotong atau tidak
        checkPageForTable(table);

        String[] headerKolom = {"Tanggal", "Total Pendapatan", "Total Pesanan"};
        
        PdfPCell c1;

        for (int i = 0; i < headerKolom.length; i++) {
            Phrase headerCell = new Phrase();
            headerCell.setFont(smallNormal);
            headerCell.add(headerKolom[i]);
    
            c1 = new PdfPCell(headerCell);
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
        }

        table.setHeaderRows(1);
        table.setTotalWidth(580);
        table.setLockedWidth(true);

        // table.setWidths(new int[] {3, 7, 7, 5, 3, 5, 8});

        if (mode == 1) {
            List<Object> data = new Con_Laporan().get_LaporanPendapatan_ByDateExport(Akun.ID_Admin);
            Object[] parsedData = (Object[]) data.toArray(new Object[0]);
            
            // Diisi dengan semua data di convert jadi string
            for (int i = 0; i < parsedData.length; i++) {
                Phrase isiTabel = new Phrase();
                isiTabel.setFont(smallerNormal);
                isiTabel.add(parsedData[i].toString() + "\n \u00a0"); // Whitespace diakhir
                
                c1 = new PdfPCell(isiTabel);
                c1.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(c1);
            }
        } else
        if (mode == 2) {
            List<Object> data = new Con_Laporan().get_LaporanPendapatan_ByDatePeriodExport(Akun.ID_Admin, startDate, endDate);
            Object[] parsedData = (Object[]) data.toArray(new Object[0]);
            
            // Diisi dengan semua data di convert jadi string
            for (int i = 0; i < parsedData.length; i++) {
                parsedData[i].toString();

                Phrase isiTabel = new Phrase();
                isiTabel.setFont(smallerNormal);
                isiTabel.add(parsedData[i].toString() + "\n \u00a0"); // Whitespace diakhir
                
                c1 = new PdfPCell(isiTabel);
                c1.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(c1);
            }
        }

        paragraph.add(table);
    }

    void tableLaporanPendapatanByMonth(int mode, String startDate, String endDate, Paragraph paragraph) throws DocumentException {
        // 1 = all
        // 2 = periode
        PdfPTable table = new PdfPTable(3);

        // Cek tabel kepotong atau tidak
        checkPageForTable(table);

        String[] headerKolom = {"Tanggal", "Total Pendapatan", "Total Pesanan"};
        
        PdfPCell c1;

        for (int i = 0; i < headerKolom.length; i++) {
            Phrase headerCell = new Phrase();
            headerCell.setFont(smallNormal);
            headerCell.add(headerKolom[i]);
    
            c1 = new PdfPCell(headerCell);
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
        }

        table.setHeaderRows(1);
        table.setTotalWidth(580);
        table.setLockedWidth(true);

        // table.setWidths(new int[] {3, 7, 7, 5, 3, 5, 8});

        if (mode == 1) {
            List<Object> data = new Con_Laporan().get_LaporanPendapatan_ByMonthExport(Akun.ID_Admin);
            Object[] parsedData = (Object[]) data.toArray(new Object[0]);
            
            // Diisi dengan semua data di convert jadi string
            for (int i = 0; i < parsedData.length; i++) {
                Phrase isiTabel = new Phrase();
                isiTabel.setFont(smallerNormal);
                isiTabel.add(parsedData[i].toString() + "\n \u00a0"); // Whitespace diakhir

                c1 = new PdfPCell(isiTabel);
                c1.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(c1);
            }
        } else
        if (mode == 2) {
            List<Object> data = new Con_Laporan().get_LaporanPendapatan_ByMonthPeriodExport(Akun.ID_Admin, startDate, endDate);
            Object[] parsedData = (Object[]) data.toArray(new Object[0]);
            
            // Diisi dengan semua data di convert jadi string
            for (int i = 0; i < parsedData.length; i++) {
                Phrase isiTabel = new Phrase();
                isiTabel.setFont(smallerNormal);
                isiTabel.add(parsedData[i].toString() + "\n \u00a0"); // Whitespace diakhir
                
                c1 = new PdfPCell(isiTabel);
                c1.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(c1);
            }
        }

        paragraph.add(table);
    }

    void tableLaporanPendapatanByYear(int mode, String startDate, String endDate, Paragraph paragraph) throws DocumentException {
        // 1 = all
        // 2 = periode
        PdfPTable table = new PdfPTable(3);

        // Cek tabel kepotong atau tidak
        checkPageForTable(table);

        String[] headerKolom = {"Tanggal", "Total Pendapatan", "Total Pesanan"};
        
        PdfPCell c1;

        for (int i = 0; i < headerKolom.length; i++) {
            Phrase headerCell = new Phrase();
            headerCell.setFont(smallNormal);
            headerCell.add(headerKolom[i]);
    
            c1 = new PdfPCell(headerCell);
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
        }

        table.setHeaderRows(1);
        table.setTotalWidth(580);
        table.setLockedWidth(true);

        // table.setWidths(new int[] {3, 7, 7, 5, 3, 5, 8});

        if (mode == 1) {
            List<Object> data = new Con_Laporan().get_LaporanPendapatan_ByYear(Akun.ID_Admin);
            Object[] parsedData = (Object[]) data.toArray(new Object[0]);
            
            // Diisi dengan semua data di convert jadi string
            for (int i = 0; i < parsedData.length; i++) {
                Phrase isiTabel = new Phrase();
                isiTabel.setFont(smallerNormal);
                isiTabel.add(parsedData[i].toString() + "\n \u00a0"); // Whitespace diakhir
                
                c1 = new PdfPCell(isiTabel);
                c1.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(c1);
            }
        } else
        if (mode == 2) {
            List<Object> data = new Con_Laporan().get_LaporanPendapatan_ByYearPeriodExport(Akun.ID_Admin, startDate, endDate);
            Object[] parsedData = (Object[]) data.toArray(new Object[0]);
            
            // Diisi dengan semua data di convert jadi string
            for (int i = 0; i < parsedData.length; i++) {
                Phrase isiTabel = new Phrase();
                isiTabel.setFont(smallerNormal);
                isiTabel.add(parsedData[i].toString() + "\n \u00a0"); // Whitespace diakhir
                
                c1 = new PdfPCell(isiTabel);
                c1.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(c1);
            }
        }

        paragraph.add(table);
    }

    void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    void checkPageForTable(PdfPTable table){
        // Cek tabel kepotong atau tidak
        if (writer.getVerticalPosition(true) - table.getRowHeight(0) - table.getRowHeight(1) < doc.bottom()) {
            doc.newPage();
        }
    }

    void addMetada(String periode){
        doc.addTitle("Laporan " + Akun.Nama_Toko);
        doc.addSubject("Laporan Pesanan dan Pendapatan Periode " + periode);
        doc.addKeywords("Laporan, PDF");
        doc.addAuthor(Akun.ID_Admin + " AKA " + Akun.Nama_Pemilik);
        doc.addCreator(Akun.ID_Admin + " AKA " + Akun.Nama_Pemilik);
    }
}

package tools;



import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import saved_authentication.Akun;


public class CreatePdf {

    public static void main(String[] args) {
        new CreatePdf().exportToPdf();
    }

    Document doc = new Document();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd (HH_mm_ss)");

    void exportToPdf(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        
        try {
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("export/Laporan " + sdf.format(timestamp) + ".pdf"));
            doc.open();

            
            // addMetada(periode);

            doc.add(new Paragraph("Laporan "));



            doc.close();
            writer.close();
        } catch(DocumentException ex){
            ex.printStackTrace();
        } catch(FileNotFoundException ex){
            ex.printStackTrace();
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

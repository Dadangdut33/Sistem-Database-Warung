/**
 * Property of KELOMPOK-5 PL Kelas 2A
 * Author1: Fauzan Farhan Antoro
 * Author2: Alfanisa Safvira
 * Author3: Daffa Fawwaz Syadad
 */
package tools;
import javax.swing.text.*;

public class OnlyDigit {
    public PlainDocument getOnlyDigit(){
        PlainDocument filterDigit = new PlainDocument(){
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException
            {
                StringBuffer buffer = new StringBuffer();
                int s = 0;
                char[] dataInput = str.toCharArray();
                int len = dataInput.length;

                // Memeriksa semua data yang dimasukkan
                for (int i = 0; i < len; i++){
                    // Menyaring apakah data masukkan berupa DIGIT
                    boolean isOnlyDigit = Character.isDigit(dataInput[i]);
                    if (isOnlyDigit == true){
                        dataInput[s] = dataInput[i];
                        s++;
                    }
                }
                buffer.append(dataInput,0,s);
                super.insertString(offs, new String(buffer), a);
            }
        };		
        return filterDigit;
    }
}

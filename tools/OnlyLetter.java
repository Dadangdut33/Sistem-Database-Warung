/**
 * Property of KELOMPOK-5 PL Kelas 2A
 * Author1: Fauzan Farhan Antoro
 * Author2: Alfanisa Safvira
 * Author3: Daffa Fawwaz Syadad
 */
package tools;
import javax.swing.text.*;

public class OnlyLetter {
    public PlainDocument getOnlyLetter(){
        PlainDocument filterLetter = new PlainDocument(){
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException{
                StringBuffer buffer = new StringBuffer();
                int s = 0;
                char[] dataInput = str.toCharArray();

                // Memeriksa semua data yang dimasukkan
                for (int i = 0; i < dataInput.length; i++){
                    boolean isOnlyLetter = Character.isLetter(dataInput[i]);
                    boolean isSpace = Character.isSpaceChar(dataInput[i]);
                    if (isOnlyLetter == true || isSpace == true){
                        dataInput[s]=dataInput[i];
                        s++;
                    }
                }
                buffer.append(dataInput,0,s);
                super.insertString(offs, new String(buffer), a);
            }
        };
        return filterLetter;
    }
}

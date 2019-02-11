/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoo.pkg2011.q1;

/**
 *
 * @author Cole
 */
public class ECOO2011Q1 {

    static void printCharArray(char[] str) {

        StringBuilder sb = new StringBuilder();

        sb.append(String.valueOf(str));
        System.out.println(sb.toString());
    }
    
    static char[] spaceBetweenLetters(char[] str){
        char[] c = new char[str.length * 2 + 1];
        for (int i = 0; i < c.length; i++){
            if (i % 2 == 0) {
                c[i] = ' ';
            } else {
                if ((i - 1) / 2 < str.length) {
                    c[i] = str[(i - 1) / 2];
                }
            }
        }
        return c;
    }
    
    static String appendInnerFrame(String str){
        for (int i = 0; i < str.length(); i++){
            
        }
    }

    static void frame(String str) {

        char[] string = new char[str.length() * 2 + 3];
        char[] c = str.toCharArray();

        string[0] = '*';
        string[string.length - 1] = '*';
        c = spaceBetweenLetters(c);
        
        for (int i = 1; i < c.length + 1; i++){
            string[i] = c[i - 1];
        }
        
        printCharArray(string);

        for (int q = 0; q < str.length(); q++) {
            for (int r = str.length(); r > 0 ; r--) {
                System.out.println(str.charAt(r) + appendInnerFrame() + str.charAt(q));
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        frame("MAPLE");
    }

}

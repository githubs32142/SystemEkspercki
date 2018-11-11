/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Model;

public class EqualString {

    /**
     ** Metoda porównująca dwa łańcuchy znaków uwzgledniając jedynie znaki w
     * kodzie ASCI.
     *
     * @param s łańcuch znaków
     * @param s2 łańcuch znaków
     * @return true jezeli oba znaki są takie same
     */
    public static boolean equals(String s, String s2) {
        StringBuilder sTmp = new StringBuilder();
        StringBuilder s2Tmp = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (containsSign(s, i)) {
                sTmp.append(s.charAt(i));
            }
        }
        for (int i = 0; i < s2.length(); i++) {
            if (containsSign(s2, i)) {
                s2Tmp.append(s2.charAt(i));
            }
        }
        if (isOtherLenght(s2Tmp, sTmp)) {
            return false;
        } else {
            for (int i = 0; i < sTmp.length(); i++) {
                if (isOtherSign(sTmp, i, s2Tmp)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isOtherLenght(StringBuilder s2Tmp, StringBuilder sTmp) {
        return s2Tmp.length() != sTmp.length();
    }

    private static boolean isOtherSign(StringBuilder sTmp, int i, StringBuilder s2Tmp) {
        return sTmp.charAt(i) != s2Tmp.charAt(i);
    }

    private static boolean containsSign(String s, int i) {
        return ((int) s.charAt(i) > 64 && (int) s.charAt(i) <= 90) || ((int) s.charAt(i) >= 97 && (int) s.charAt(i) <= 122);
    }

    public static String removeChar(String s) {
        StringBuilder sTmp = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (containsSign(s, i)) {
                sTmp.append(s.charAt(i));
            }
        }
        return sTmp.toString();
    }

}

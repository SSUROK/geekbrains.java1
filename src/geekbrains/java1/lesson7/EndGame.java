package geekbrains.java1.lesson7;

import javax.swing.*;

public class EndGame {

    private static final String DOT = "X";
    private static final int win = 3;

    public static boolean checkEnd(JPanel jp, JButton[][] b){
        if (checkDraw(b)){
            return true;
        }
        if (checkWin(DOT, win, b)){
            return true;
        }
        return false;
    }

    private static boolean checkWin(String DOT, int winLength, JButton[][] b) {
        int k = 0;
        for (int i = 0; i < b.length; i++) {
            for (int y = 0; y < b[0].length; y++) {
                if (b[i][y].getText().equals(DOT)) {
                    if (i + winLength < b.length) {
                        for (int ii = i + 1; ii < winLength + i; ii++) {
                            if (b[ii][y].getText().equals(DOT)) {
                                k++;
                            } else {
                                k = 0;
                            }
                        }
                        return k == 2;
                    }
                }
            }
        }
        return false;
    }
    private static boolean checkDraw(JButton[][] b){
        for (int i = 0; i < b[0].length; i++) {
            for (int y = 0; y < b.length; y++) {
                if (b[i][y].isEnabled()) return false;
            }
        }
        return true;
    }
}

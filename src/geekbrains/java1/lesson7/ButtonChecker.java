package geekbrains.java1.lesson7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonChecker {
    public static void ButtonChanger(JButton[][] b, JPanel jp) {
        for (int i = 0; i < b.length; i++) {
            for (int y = 0; y < b.length; y++) {
                int j = i;
                int k = y;
                b[i][y].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        b[j][k].setText("X");
                        b[j][k].setForeground(Color.BLACK);
                        b[j][k].setBackground(Color.DARK_GRAY);
                        b[j][k].setEnabled(false);
                        if (EndGame.checkEnd(jp, b))
                            System.out.print("Game End");
                        }
                });
            }
        }
    }
}

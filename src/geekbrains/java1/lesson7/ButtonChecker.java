package geekbrains.java1.lesson7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonChecker{
    public static void ButtonChanger(JPanel center,JButton[] b) {
        for (int i = 0; i < b.length; i++) {
            int j = i;
            b[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    b[j].setText("X");
                    b[j].setBackground(Color.DARK_GRAY);
                    b[j].setEnabled(false);
                }
            });
        }
    }
}

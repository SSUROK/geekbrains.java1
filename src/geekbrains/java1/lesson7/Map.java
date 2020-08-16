package geekbrains.java1.lesson7;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Map extends JPanel {
    public static final int MODE_HVH = 0;
    public static final int MODE_HVA = 1;
    private static int currentSizeX = 0;
    private  static int currentSizeY = 0;
    private static final int WIN_WIDTH = 507;
    private static final int WIN_HEIGHT = 555;

    Map() {
        setBackground(Color.DARK_GRAY);
        if (currentSizeX > 0){
            JPanel panelCenter = new JPanel();
            panelCenter.setLayout(new GridLayout(currentSizeX, currentSizeY));
            for (int i = 0; i < currentSizeX; i++) {
                for (int y = 0; y < currentSizeY; y++) {
                    JButton b = new JButton("Place Here") ;
                    b.setPreferredSize(new Dimension(WIN_WIDTH / currentSizeX-5, WIN_HEIGHT / currentSizeY-20));
                    b.setBackground(Color.GRAY);
                    b.setForeground(Color.BLACK);
                    Border border = new LineBorder(Color.BLACK, 2);
                    b.setBorder(border);
                    panelCenter.add(b);
                }
            }
            add(panelCenter);
        }
    }


    void startNewGame(int mode, int fieldSizeX, int fieldSizeY, int winLength) {
        System.out.printf("mode: %d, size: %d, len: %d\n", mode, fieldSizeX, winLength);
        currentSizeX = fieldSizeX;
        currentSizeY = fieldSizeY;
        new GameWindow();
    }
}
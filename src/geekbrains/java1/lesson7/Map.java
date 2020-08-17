package geekbrains.java1.lesson7;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Map extends JPanel {
    public static final int MODE_HVH = 0;
    public static final int MODE_HVA = 1;
    private static int currentSizeX = 0;
    private static int currentSizeY = 0;
    private static final int WIN_WIDTH = 507;
    private static final int WIN_HEIGHT = 555;
    private JButton[][] b = new JButton[currentSizeX][currentSizeY];
    private static final Border border = new LineBorder(Color.BLACK, 2);
    public static final JPanel panelCenter = new JPanel();

    Map() {
        setBackground(Color.DARK_GRAY);
        centerMaker();
    }

    private void centerMaker() {
        if (currentSizeX > 0){
            panelCenter.setLayout(new GridLayout(currentSizeX, currentSizeY));
            for (int i = 0; i < currentSizeX; i++) {
                for (int y = 0; y < currentSizeY; y++) {
                    b[i][y] = new JButton("Place Here");
                    b[i][y].setPreferredSize(new Dimension(WIN_WIDTH / currentSizeX - 5, WIN_HEIGHT / currentSizeY - 20));
                    b[i][y].setBackground(Color.GRAY);
                    b[i][y].setForeground(Color.BLACK);
                    b[i][y].setBorder(border);
                    panelCenter.add(b[i][y]);
                }
            }
            add(panelCenter,BorderLayout.CENTER);
            ButtonChecker.ButtonChanger(b, panelCenter);
        }
    }

    void startNewGame(int mode, int fieldSizeX, int fieldSizeY, int winLength) {
//        System.out.printf("mode: %d, size: %d, len: %d\n", mode, fieldSizeX, winLength);
        currentSizeX = fieldSizeX;
        currentSizeY = fieldSizeY;
        new GameWindow();
    }
}
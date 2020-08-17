//package geekbrains.java1.lesson7;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class MapDrawer {
//
//    Map(int FIELD_SIZE_X, int FIELD_SIZE_Y) {
//        setBackground(Color.DARK_GRAY);
//        JPanel panelCenter = new JPanel();
//        panelCenter.setLayout(new GridLayout(FIELD_SIZE_X * 2 - 1, FIELD_SIZE_Y * 2 - 1));
//        System.out.println(FIELD_SIZE_X * 2 - 1 + " " + (FIELD_SIZE_Y * 2 - 1));
//        for (int i = 0; i < FIELD_SIZE_X * 2 - 1; i++) {
//            for (int y = 0; y < FIELD_SIZE_Y * 2 - 1; i++) {
//                System.out.println(i % 2 + " " + y % 2);
////                if (i % 2 == 0) {
////                    panelCenter.add(vB, i, y);
////                }
////                if (y % 2 == 0){
////                    panelCenter.add(hB, i, y);
////                }
////                if (i % 2 == 0 && y % 2 == 0){
////                    panelCenter.add(cB, i , y);
////                }
//            }
//        }
//        add(panelCenter, BorderLayout.CENTER);
//    }
//}

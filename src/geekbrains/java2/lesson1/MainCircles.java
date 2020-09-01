package geekbrains.java2.lesson1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class MainCircles extends JFrame {
    private static final int POS_X = 400;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static int counter = 0;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainCircles();
            }
        });
    }

    Sprite[] sprites = new Sprite[10];
    Sprite back;
    Sprite[] ball = new Sprite[10];

    private MainCircles() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        GameCanvas canvas = new GameCanvas(this);
        add(canvas, BorderLayout.CENTER);
        JButton butt = new JButton("Удалить");
        add(butt, BorderLayout.SOUTH);
        initApplication();
        setTitle("Circles");
        setVisible(true);
        butt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteButton();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                addBall(e);
            }
        });
    }

    private void deleteButton(){
        Random random = new Random();
        int i = random.nextInt(ball.length);
        ball[i].setHalfHeight(0);
        ball[i].setHalfWidth(0);
    }

    private void addBall(MouseEvent e){
        if (counter == ball.length) return;
        ball[counter] = new Ball(e);
        counter++;
    }

    private void initApplication() {
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new Ball();
        }
        back = new BackGround();
    }

    public void onDrawFrame(GameCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    public void backColor(GameCanvas canvas, Graphics g, float deltaTime){
        back.update(canvas,deltaTime);
        back.render(canvas, g);
    }

    private void update(GameCanvas canvas, float deltaTime) {
        for (int i = 0; i < sprites.length; i++) {
            sprites[i].update(canvas, deltaTime);
        }
        for (int i = 0; i < counter; i++) {
            ball[i].update(canvas, deltaTime);
        }
    }

    private void render(GameCanvas canvas, Graphics g) {
        for (int i = 0; i < sprites.length; i++) {
            sprites[i].render(canvas, g);
        }
        for (int i = 0; i < counter; i++) {
            ball[i].render(canvas, g);
        }
    }
}

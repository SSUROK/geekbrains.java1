package geekbrains.java2.lesson1;

import java.awt.*;
import java.util.Random;

public class BackGround extends Sprite{

    private static Color color;

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static int r = 0,g = 0,b = 0;
    private Random random = new Random();

    BackGround(){
    }

    @Override
    public void update(GameCanvas canvas, float deltaTime) {
        color = new Color (r, g, b);
        int ra = random.nextInt(2);
        switch (ra){
            case 0:
                r++;
                break;
            case 1:
                g++;
                break;
            case 2:
                b++;
                break;
        }
        ra = random.nextInt(255);
        if (r == 256){ r = ra; }
        if (g == 256){ g = ra; }
        if (b == 256){ b = ra; }
    }

    @Override
    public void render(GameCanvas canvas, Graphics g) {
//        System.out.println(color);
        g.setColor(color);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
    }
}

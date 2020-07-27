package geekbrains.java1;

import javax.swing.*;
import java.util.Scanner;
public class Main {

    public static float Math(float a, float b, float c, float d) {
        return a * (b + (c / d));
    }

    public static boolean SumCheck(int a, int b) {
        if ((a + b <= 20) && (a + b > 10)) {
            return true;
        } else {
            return false;
        }
    }

    public static String NumberCheck(int i) {
        if (i >= 0) {
            return "Number is positive";
        } else {
            return "Number is negative";
        }
    }

    public static String HelloUser(String s) {
        s = "Привет, " + s;
        return s;
    }

    public static String YearCheck(float y) {
        if ((y % 4 == 0) && ((y % 100 != 0) || (y % 400 == 0))) {
            return "Год високосный";
        } else {
            return "Год не високосный";
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your name");
        String s = in.nextLine();
        System.out.println(HelloUser(s));
        System.out.println("What do you need?");
        System.out.println("1:Math");
        System.out.println("2:Sum check");
        System.out.println("3:Number check");
        System.out.println("4:Year check");
        int i = in.nextInt();

        switch (i) {
            case 1: {
                System.out.println("Input four numbers");
                float a = in.nextFloat(), b = in.nextFloat(), c = in.nextFloat(), d = in.nextFloat();
                System.out.println(Math(a, b, c, d));

            }
            case 2: {
                System.out.println("Input two numbers");
                int z = in.nextInt(), x = in.nextInt();
                System.out.println(SumCheck(z, x));
            }
            case 3: {
                System.out.println("Enter a number");
                int o = in.nextInt();
                System.out.println(NumberCheck(o));
            }
            case 4: {
                System.out.println("Enter a year");
                int y = in.nextInt();
                System.out.println(YearCheck(y));
            }
            default:return;
        }
    }
}

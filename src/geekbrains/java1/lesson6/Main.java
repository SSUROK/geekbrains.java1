package geekbrains.java1.lesson6;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static final String test1 = new String("src/geekbrains/java1/lesson6/testPackage/test1.txt");
    public static final String test2 = new String("src/geekbrains/java1/lesson6/testPackage/test2.txt");
    public static final String test = new String("src/geekbrains/java1/lesson6/testPackage/test.txt");
    public static final String testPackage = new String("src/geekbrains/java1/lesson6/testPackage");

    public static void clay() {
        try {
            FileInputStream fis0 = new FileInputStream(test1);
            FileInputStream fis1 = new FileInputStream(test2);
            FileOutputStream fos3 = new FileOutputStream(test,true);

            int b;
                while ( (b = fis0.read()) != -1 )
                fos3.write(b);

                while ( (b = fis1.read()) != -1 )
                    fos3.write(b);

            fos3.flush();
            fos3.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    public static boolean check(String s) {
        try {
            int l = 0;
            FileInputStream fis = new FileInputStream(test);
            Scanner sc = new Scanner(new FileInputStream(test));
            while (sc.hasNext()){
                String count = sc.nextLine();
                l += count.length();
            }

            char[] fisString = new char[l];
            for (int i = 0; i < l; i++){
                fisString[i] = (char) fis.read();
            }
            for (int i = 0; i < l - s.length(); i++){
                StringBuilder sb = new StringBuilder();
                for (int y = 0; y < s.length(); y++) {
                    sb.append(fisString[i+y]);
                }
                if (s.equals(sb.toString()))
                    return true;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public static boolean checkInPackage(String s){
        File file = new File(testPackage);
        File[] files = file.listFiles();
        for (int t = 0; t < files.length; t++)
            try {
                int l = 0;
                FileInputStream fis = new FileInputStream(files[t]);
                Scanner sc = new Scanner(new FileInputStream(files[t]));
                while (sc.hasNext()){
                    String count = sc.nextLine();
                    l += count.length();
                }

                char[] fisString = new char[l];
                for (int i = 0; i < l; i++){
                    fisString[i] = (char) fis.read();
                }
                for (int i = 0; i < l - s.length(); i++){
                    StringBuilder sb = new StringBuilder();
                    for (int y = 0; y < s.length(); y++) {
                        sb.append(fisString[i+y]);
                    }
                    if (s.equals(sb.toString()))
                        return true;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        return false;
    }

    public static void main(String[] args) {
        clay();
        System.out.println(check("GeekBrains"));
        System.out.println(checkInPackage("GeekBrains"));
    }
}

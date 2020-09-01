package geekbrains.java2.lesson2;


public class Main {

    static String s = ("10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0");
    static String[][] matrix;
    static int[][] matrixToInt;

    private static void matrixBuilder(String s) throws Exception {
        int pointer = 0, collum = 1, line = 1, c = 0, l = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '\n') {
                line++;
            }
        }
        while (s.charAt(pointer) != '\n'){
            if (s.charAt(pointer) == ' ')
                collum++;
            pointer++;
        }

        if (line != 4 || collum != 4 )
            throw new Exception("Не тот размер массива");

        matrix = new String[line][collum];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ' && s.charAt(i) != '\n') {
                matrix[l][c] += s.charAt(i);
            }
            if (s.charAt(i) == ' ')
                c++;
            if (s.charAt(i) == '\n') {
                l++;
                c = 0;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int y = 0; y < matrix[0].length; y++) {
                matrix[i][y] = matrix[i][y].substring(matrix[i][y].indexOf('l') + 2);
            }
        }
    }

    private static int toInt() {
        int sum = 0;
        matrixToInt = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int y = 0; y < matrix[0].length; y++) {
                matrixToInt[i][y] = Integer.parseInt(matrix[i][y]);
                sum +=matrixToInt[i][y];
            }
        }
        return sum/2;
    }

    public static void main(String[] args){
        try {
            matrixBuilder(s);
        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        System.out.println(toInt());
    }
}

package geekbrains.java1;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'O';
    private static final char DOT_EMPTY = '.';
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Random RANDOM = new Random();
    private static int fieldSizeY;
    private static int fieldSizeX;
    private static char[][] field;

    private static void initField() {
        System.out.println("Enter field size >>>");
        fieldSizeX = SCANNER.nextInt();
        fieldSizeY = fieldSizeX;
        field = new char[fieldSizeY][fieldSizeX];
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                field[y][x] = DOT_EMPTY;
            }
        }
    }

    private static void printField() {
        System.out.print("+");
        for (int x = 0; x < fieldSizeX * 2 + 1; x++)
            System.out.print((x % 2 == 0) ? "-" : x / 2 + 1);
        System.out.println();

        for (int y = 0; y < fieldSizeY; y++) {
            System.out.print(y + 1 + "|");
            for (int x = 0; x < fieldSizeX; x++)
                System.out.print(field[y][x] + "|");
            System.out.println();
        }

        for (int x = 0; x <= fieldSizeX * 2 + 1; x++)
            System.out.print("-");
        System.out.println();
    }

    private static void humanTurn() {
        int x;
        int y;
        do {
            System.out.println("Enter coords >>> ");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!isValidCell(x, y) || !isEmptyCell(x, y));
        field[x][y] = DOT_HUMAN;
    }

    private static boolean isEmptyCell(int x, int y) {
        return field[x][y] == DOT_EMPTY;
    }

    private static boolean isValidCell(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    private static boolean searchH (int x, char c) {
        for (int i = 0; i < field.length; i++)
            if (field[x][i] != c && field[x][i] != DOT_EMPTY)
                return true;
        return false;
    }

    private static boolean searchV (int y, char c) {
        for (int i = 0; i < field.length; i++)
            if (field[i][y] != c && field[i][y] != DOT_EMPTY)
                return true;
        return false;
    }

    private static boolean searchD (char c) {
        for (int i = 0; i < field.length; i++)
            if (field[i][i] != c && field[i][i] != DOT_EMPTY)
                return true;
        return false;
    }

    private static boolean searchD1 (char c) {
        for (int i = 0; i < field.length; i++)
            if (field[i][field.length - 1 - i] != c && field[i][field.length - 1 - i] != DOT_EMPTY)
                return true;
        return false;
    }

    private static int patern(char c,int [] stvr){
        int dia = 1, dia1 = 1, max = 1;
        for (int i = 0; i < field.length; i++){
            int hor = 1, ver = 1;
            for (int y = 0; y < field.length; y++) {
                if (searchH(i,c))
                    hor = 0;
                if (field[i][y] == c && hor != 0)
                    hor++;
                if (hor > max) {
                    max = hor;
                    stvr[1] = i;
                    stvr[0] = 1;
                }
                if (searchV(y,c))
                    ver = 0;
                if (field[y][i] == c && ver != 0)
                    ver++;
                if (ver > max) {
                    max = ver;
                    stvr[1] = y;
                    stvr[0] = 2;
                }
            }
            if (searchD(c))
                dia = 0;
            if (field[i][i] == c && dia != 0)
                dia++;
            if (dia > max) {
                max = dia;
                stvr[0] = 3;
            }
            if (searchD1(c))
                dia1 = 0;
            if (field[i][field.length - 1 -i] == c && dia1 != 0)
                dia1++;
            if (dia1 > max) {
                max = dia1;
                stvr[0] = 4;
            }
        }
        return max;
    }

    private static boolean checkY(int y){
        for (int i = 0; i <field.length; i++ ){
            if (field[i][y] == DOT_EMPTY)
                return false;
        }
        return true;
    }

    private static boolean checkX(int x){
        for (int i = 0; i <field.length; i++ ){
            if (field[x][i] == DOT_EMPTY)
                return false;
        }
        return true;
    }

    private static boolean checkd1(){
        for (int i=0; i < field.length; i++){
            if (field[i][i] == DOT_EMPTY)
                return false;
        }
        return true;
    }

    private static boolean checkd2(){
        for (int i=0; i < field.length; i++){
            if (field[i][field.length - i -1] == DOT_EMPTY)
                return false;
        }
        return true;
    }

    private static void aiTurn() {
        int x;
        int y;
        int[] stvr = new int[2];
        char choice;

        if (patern(DOT_AI, stvr) > patern(DOT_HUMAN, stvr))
            choice = DOT_AI;
        else
            choice = DOT_HUMAN;
        patern(choice, stvr);
            switch (stvr[0]) {
                case 1:
                    do {
                        x = stvr[1];
                        y = RANDOM.nextInt(fieldSizeY);
                    } while (!isEmptyCell(x, y) && !checkX(x));
                    if (checkX(x)) {
                        do {
                            x = RANDOM.nextInt(fieldSizeX);
                            y = RANDOM.nextInt(fieldSizeY);
                        } while (!isEmptyCell(x, y));
                    }
                    field[x][y] = DOT_AI;
                    break;
                case 2:
                    do {
                        x = RANDOM.nextInt(fieldSizeX);
                        y = stvr[1];
                    } while (!isEmptyCell(x, y) && !checkY(y));
                    if (checkY(y)) {
                        do {
                            x = RANDOM.nextInt(fieldSizeX);
                            y = RANDOM.nextInt(fieldSizeY);
                        } while (!isEmptyCell(x, y));
                    }
                    field[x][y] = DOT_AI;
                    break;
                case 3:
                    do {
                        x = RANDOM.nextInt(fieldSizeX);
                        y = x;
                    } while (!isEmptyCell(x, y) && !checkd1());
                    if (checkd1()) {
                        do {
                            x = RANDOM.nextInt(fieldSizeX);
                            y = RANDOM.nextInt(fieldSizeY);
                        } while (!isEmptyCell(x, y));
                    }
                    field[x][y] = DOT_AI;
                    break;
                case 4:
                    do {
                        x = RANDOM.nextInt(fieldSizeX);
                        y = field.length - x - 1;
                    } while (!isEmptyCell(x, y) && !checkd2());
                    if (checkd2()) {
                        do {
                            x = RANDOM.nextInt(fieldSizeX);
                            y = RANDOM.nextInt(fieldSizeY);
                        } while (!isEmptyCell(x, y));
                    }
                    field[x][y] = DOT_AI;
                    break;
                default: {
                    do {
                        x = RANDOM.nextInt(fieldSizeX);
                        y = RANDOM.nextInt(fieldSizeY);
                    } while (!isEmptyCell(x, y));
                    field[x][y] = DOT_AI;
                }
            }
        }

    private static boolean checkDraw() {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (isEmptyCell(x, y)) return false;
            }
        }
        return true;
    }

    private static boolean checkWin(char c, char[][] arr) {

        int dia = 1, dia1 = 1;
        for (int i = 0; i < arr.length; i++){
            int hor = 1, ver = 1;
            for (int y = 0; y < arr.length; y++) {
                if (arr[i][y] != c)
                    hor = 0;
                if (arr[y][i] != c)
                    ver = 0;
                if (i == y && arr[i][y] != c)
                    dia = 0;
                if (y == arr.length - 1 -i && arr[i][y] != c)
                    dia1 = 0;
            }
            if (hor == 1 || ver == 1)
                return true;
        }
        return dia1 == 1 || dia == 1;

    }

    public static void main(String[] args) {
        String answer;
        do {
            initField();
            printField();
            while (true) {

                aiTurn();
                if (checkEndGame(DOT_AI, "Computer win!")) break;
                humanTurn();
                if (checkEndGame(DOT_HUMAN, "Human win!")) break;
            }
            System.out.println("Wanna play again? (y/n) >>> ");
            answer = SCANNER.next();
        } while (answer.equals("y"));
        SCANNER.close();
    }

    private static boolean checkEndGame(char dot, String winMessage) {
        printField();
        if (checkWin(dot, field)) {
            System.out.println(winMessage);
            return true;
        }
        if (checkDraw()) {
            System.out.println("Draw!");
            return true;
        }
        return false;
    }
}

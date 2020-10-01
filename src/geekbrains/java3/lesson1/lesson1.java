package geekbrains.java3.lesson1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class lesson1 {

    private static void swap (Object[] obj, int pos1, int pos2){
        Object temp = obj[pos1];
        obj[pos1] = obj[pos2];
        obj[pos2] = temp;
    }

    public static <T> ArrayList<T> toList(T[] arr) {
        return new ArrayList<T>(Arrays.asList(arr));
    }

    public static void main(String[] args){
        Integer[] ints = {1,2,3,4,5};

        swap(ints, 1, 3);
        System.out.println(Arrays.deepToString(ints));

        ArrayList<Integer> list = toList(ints);

        Apple apple1 = new Apple();
        Apple apple2 = new Apple();
        Apple apple3 = new Apple();

        Orange orange1 = new Orange();
        Orange orange2 = new Orange();
        Orange orange3 = new Orange();

        Box<Apple> boxA1 = new Box<>(apple1, apple2, apple3);
        Box<Apple> boxA2 = boxA1;
        Box<Orange> boxO1 = new Box<>(orange1, orange2, orange3);

        System.out.println(boxA1.getWeight());
        System.out.println(boxA1.compare(boxA2));
        System.out.println(boxA1.compare(boxO1));

        boxA1.boxSwap(boxA2);
        System.out.println(boxA1.getWeight());
        System.out.println(boxA2.getWeight());
    }
}

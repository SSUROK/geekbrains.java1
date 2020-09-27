package geekbrains.java3;

import java.util.ArrayList;
import java.util.Arrays;

public class Box<T extends Fruit> {
    private ArrayList<T> fruits;

    public Box(T ...fruits){
        this.fruits = new ArrayList<T>(Arrays.asList(fruits));
    }

    public float getWeight(){
        if (fruits.size() == 0) return 0;
        T fruit = fruits.get(1);
        return fruits.size() * fruit.getWeight();
    }

    public boolean compare(Box box){
        return this.getWeight() == box.getWeight();
    }

    public void boxSwap(Box<T> box){
        box.fruits.addAll(this.fruits);
        fruits.clear();
    }
}

package geekbrains.java1.lesson5;

public class Main {

    public static void main(String[] args){
        Dog dog1 = new Dog("Бобик");
        Dog dog2 = new Dog("Тузик");
        Cat cat1 = new Cat("Марсик");
        Cat cat2 = new Cat("Мурлок");

        dog1.run(40);
        dog2.setMaxRun(300);
        dog2.run(400);
        cat1.jump(30);
        cat2.swim(40);
    }
}

package geekbrains.java1.lesson5;

public class Cat extends Animals {

    public Cat(String name){
        super(name);
        this.maxJump = 2;
        this.maxRun = 200;
    }

    @Override
    public void swim(int s){
        System.out.println("Котики не плавают");
    }

    @Override
    protected void run(int r) {
        if (maxRun >= r)
            System.out.println(name + " пробежал " + r + " метров");
        else
            System.out.println("Котик столько не пробежит");
    }

    @Override
    public void jump(int j){
        if (maxRun >= j)
            System.out.println(name + " прыгнул на " + j + " метров");
        else
            System.out.println("Котик так высоко не прыгнет");
    }
}

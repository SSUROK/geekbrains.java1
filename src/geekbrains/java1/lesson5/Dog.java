package geekbrains.java1.lesson5;

public class Dog extends Animals {

    public Dog(String name){
        super(name);
        this.maxJump = 0.5;
        this.maxRun = 500;
        this.maxSwim = 10;
    }

    @Override
    public void swim(int s){
        if (maxRun >= s)
            System.out.println(this.name + " проплыл " + s + " метров");
        else
            System.out.println("Песик столько не проплывет");
    }

    @Override
    protected void run(int r) {
        if (maxRun >= r)
            System.out.println(name + " пробежал " + r + " метров");
        else
            System.out.println("Песик столько не пробежит");
    }

    @Override
    public void jump(int j){
        if (maxRun >= j)
            System.out.println(name + " прыгнул на " + j + " метров");
        else
            System.out.println("Песик так высоко не прыгнет");
    }
}

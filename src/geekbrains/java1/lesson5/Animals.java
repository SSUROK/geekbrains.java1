package geekbrains.java1.lesson5;

public abstract class Animals {
    protected String name;
    protected double maxRun;
    protected double maxSwim;
    protected double maxJump;

    Animals(String name){
        this.name = name;
    }

    protected abstract void run(int r);

    protected abstract void swim(int s);

    protected abstract void jump(int j);

    public double setMaxRun(int r){
        return this.maxRun = r;
    }

    public double setMaxSwim(int s){
        return this.maxSwim = s;
    }

    public double setMaxJump(int j){
        return this.maxJump = j;
    }
}

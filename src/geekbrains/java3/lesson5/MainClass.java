package geekbrains.java3.lesson5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static final CountDownLatch cdlStart = new CountDownLatch(CARS_COUNT);
    public static final CountDownLatch cdlFinish = new CountDownLatch(CARS_COUNT);
    public static String winCar;
    public static boolean hasWinner = false;
    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), cdlStart);
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        try {
            cdlStart.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        try {
            cdlFinish.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%s -> WIN\n", winCar);
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}
    class Car implements Runnable {
        private static int CARS_COUNT;

        static {
            CARS_COUNT = 0;
        }

        private Race race;
        private int speed;
        private String name;
        private CountDownLatch cdlStart;

        public String getName() {
            return name;
        }

        public int getSpeed() {
            return speed;
        }

        public Car(Race race, int speed, CountDownLatch cdlStart) {
            this.cdlStart = cdlStart;
            this.race = race;
            this.speed = speed;
            CARS_COUNT++;
            this.name = "Участник #" + CARS_COUNT;
        }

        @Override
        public void run() {
            try {
                System.out.println(this.name + " готовится");
                Thread.sleep(500 + (int) (Math.random() * 800));
                System.out.println(this.name + " готов");
                cdlStart.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                cdlStart.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }
            MainClass.cdlFinish.countDown();
            if (!MainClass.hasWinner){
                MainClass.hasWinner = true;
                MainClass.winCar = name;
            }
        }
    }
    abstract class Stage {
        protected int maxCar;
        protected int length;
        protected String description;
        public Semaphore semStage;
        public String getDescription() {
            return description;
        }

        public abstract void go(Car c);
    }
    class Road extends Stage {
        public Road(int length) {
            this.length = length;
            this.description = "Дорога " + length + " метров";
        }

        @Override
        public void go(Car c) {
            try {
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
                System.out.println(c.getName() + " закончил этап: " + description);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    class Tunnel extends Stage {
        public Tunnel() {
            this.maxCar = 2;
            this.semStage = new Semaphore(maxCar);
            this.length = 80;
            this.description = "Тоннель " + length + " метров";
        }

        @Override
        public void go(Car c) {
            try {
                try {
                    System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                    semStage.acquire();
                    System.out.println(c.getName() + " начал этап: " + description);
                    Thread.sleep(length / c.getSpeed() * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(c.getName() + " закончил этап: " + description);
                    semStage.release();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    class Race {
        private ArrayList<Stage> stages;

        public ArrayList<Stage> getStages() {
            return stages;
        }

        public Race(Stage... stages) {
            this.stages = new ArrayList<>(Arrays.asList(stages));
        }

    }

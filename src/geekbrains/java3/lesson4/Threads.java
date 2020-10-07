package geekbrains.java3.lesson4;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Threads {

    public final int Count = 5;
    static volatile String c = "A";
    static Object mon = new Object();

    public Threads() {

    CountDownLatch cdl1 = new CountDownLatch(Count);
    CountDownLatch cdl2 = new CountDownLatch(Count);
    CountDownLatch cdl3 = new CountDownLatch(Count);

    ExecutorService ex = Executors.newFixedThreadPool(1);
    System.out.println("start");
    ex.execute(new MyThread(cdl1, "A"));
    ex.execute(new MyThread(cdl2, "B"));
    ex.execute(new MyThread(cdl3, "C"));


    try {
        cdl1.await();
        cdl2.await();
        cdl3.await();
    } catch (InterruptedException e) {

    }

    ex.shutdown();
}

    class MyThread implements Runnable {
        String name;
        CountDownLatch latch;

        public MyThread(CountDownLatch latch, String name) {
            this.name = name;
            this.latch = latch;
            new Thread(this);
        }

        public void run() {
            try {
                for (int i = 0; i < Count; i++) {
                    System.out.print(name);
                    latch.countDown();
                    Thread.sleep((long) (Math.random() * 20));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class WaitNotify extends Thread {
        private String nextLetter;

        public WaitNotify(String name, String nextLetter) {
            super(name);
            this.nextLetter = nextLetter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                synchronized (mon) {
                    try {
                        while (c != getName())
                            mon.wait();
                        System.out.print(getName());
                        c = nextLetter;
                        mon.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new Threads();
        System.out.println();

        new Thread(new WaitNotify("A", "B")).start();
        new Thread(new WaitNotify("B", "C")).start();
        new Thread(new WaitNotify("C", "A")).start();
    }
}

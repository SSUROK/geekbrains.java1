package geekbrains.java2.lesson5;

public class Main {
    static final int size = 10000000;
    static final int h = size / 2;
    static float[] arr = new float[size];

    private static void oneThread(){
        long time = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++){
            arr[i] = 1f;
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("One thread time: " + (System.currentTimeMillis() - time));
    }

    private static void twoThreads(){
        long time = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1f;
        }

        float[] a1 = new float[h];
        float[] a2 = new float[h];
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        System.out.println("Two thread arraycopy: " + (System.currentTimeMillis() - time));

        MyThread t1 = new MyThread(a1, time, "First arr");
        MyThread t2 = new MyThread(a2, time, "Second arr");
        t1.start();
        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        System.out.println("Two threads time: " + (System.currentTimeMillis() - time));
    }

    public static void main(String[] args){
        oneThread();
        twoThreads();
    }
}

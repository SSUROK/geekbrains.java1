package geekbrains.java2.lesson5;

public class MyThread extends Thread {

    private static float[] arr;
    private static long time;

    public MyThread(float[] arr, long time, String name) {
        super(name);
        this.arr = arr;
        this.time = time;
    }


    @Override
    public void run(){
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println(getName() + " time: "+ (System.currentTimeMillis() - time));
    }
}

package geekbrains.java1;

public class lesson2 {

    public static void converter ( int[] arr ){
        for (int i=0; i < arr.length; i++ ){
            if ( arr[i] == 1 ){
                arr[i] = 0;
            } else {
                arr[i] = 1;
            }
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void arrayCreator ( int[] arr ){
        arr[0] = 1;
        System.out.print(arr[0]);
        for (int i = 1; i < arr.length; i++){
            arr[i] = arr[i-1] + 3;
            System.out.print(" " + arr[i]);
        }
        System.out.println();
    }

    public static void arrayDoubler ( int[] arr ){
        for ( int i = 0; i < arr.length; i++){
            if (arr[i]<6){
                arr[i] = arr[i] * 2;
            }
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void arrayMinMax (int[] arr){
        int min = 1,max = 0;
        for ( int i = 0; i < arr.length; i++){
            if ( arr[i] > max ){
                max = arr[i];
            }
            if ( arr[i] < min ){
                min = arr[i];
            }
        }
        System.out.print("min: " + min);
        System.out.println(" max: " + max);
    }

    public static void arrayQadro (int[][] arr){
        for (int i = 0; i < arr.length; i++){
            for (int y = 0; y < arr.length; y++){
                if ((i == y)||(y == arr.length - i - 1)){
                    arr[i][y] = 1;
                }
                System.out.printf("%2d", arr[i][y]);
            }
            System.out.println();
        }
    }

    public static boolean arrayChecker (int[] arr){
        int sum1 = 0, sum2 =0, k=0;
        for ( int i =  0; i < arr.length; i++){
            for ( int y = 0; y <=i; y++){
                sum1 += arr[y];
            }
            for ( int u = i+1; u < arr.length; u++){
                sum2 += arr[u];
            }
            if (sum1 == sum2){
                k = 1;
            }
            sum1=0;
            sum2=0;
        }
        return k == 1;
    }

    public static void arrayMover ( int[] arr, int n) {
        int a = 0, b = 0;
        if (n > 0) {
            for (int y = 0; y < n + 1; y++) {
                for (int i = 0; i < arr.length - 1; i++) {
                    a = arr[i];
                    b = arr[i+1];
                    arr[i] = b;
                    arr[i+1] = a;
                }
            }
        } else {
            for (int y = 1; y > n - 1; y--) {
                for (int i = 0; i < arr.length - 1; i++) {
                    a = arr[arr.length - i - 1];
                    b = arr[arr.length - i - 2];
                    arr[arr.length - i - 1] = b;
                    arr[arr.length - i - 2] = a;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
    }

    public static void main(String[] args) {

        int[] arr0 = new int[] {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        converter(arr0);
        System.out.println();

        int[] arr1 = new int[8];
        arrayCreator(arr1);
        System.out.println();

        int[] arr2 = new int[] {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        arrayDoubler(arr2);
        System.out.println();

        int[] arr3 = new int[] {1, 5 , 2, 8, 3, 6, 0, 9, 4, 10};
        arrayMinMax(arr3);
        System.out.println();

        int[][] arr4 = new int[10][10];
        arrayQadro(arr4);
        System.out.println();

        int[] arr5 = new int[] {10, 1, 2, 3, 4};
        int[] arr6 = new int[] {1, 1, 1, 2, 1};
        int[] arr7 = new int[] {2, 1, 1, 2, 1};
        System.out.print(arrayChecker(arr5) + " ");
        System.out.print(arrayChecker(arr6) + " ");
        System.out.print(arrayChecker(arr7) + " ");
        System.out.println();

        int[] arr8 = new int[] { 1, 2, 3, 4, 5};
        int n = 2;
        int n1 =-4;
        arrayMover(arr8, n);
        System.out.println();
        arrayMover(arr8, n1);
    }
}
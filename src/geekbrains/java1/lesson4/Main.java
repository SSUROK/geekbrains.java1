package geekbrains.java1.lesson4;

public class Main {

    static employee[] e1 = new employee[5];

    public static void pensionnayaReforma (){
        int bonus = 5000;
        for (int i = 0; i < e1.length; i++)
            if (e1[i].getAge() > 45)
                e1[i].setSalary(bonus);
    }

    public static float midAge(){
        int sum = 0;
        for (int i = 0; i < e1.length; i++)
            sum += e1[i].getAge();
        return sum * 1.0f / e1.length;
    }

    public static float midSalary(){
        int sum = 0;
        for (int i = 0; i < e1.length; i++)
            sum += e1[i].getSalary();
        return sum * 1.0f / e1.length;
    }

    public static void main(String[] args){
        employee e0 = new employee("Ilay", 20000,19);

        System.out.printf("Employee %s with salary %d and age of %d\n",e0.getName(),e0.getSalary(),e0.getAge());

        e1[0] = new employee("Dima", 19000, 20);
        e1[1] = new employee("Alex", 21000, 46);
        e1[2] = new employee("Ivan", 22000, 60);
        e1[3] = new employee("Petr", 23000, 30);
        e1[4] = new employee("Andry", 24000, 40);

        for (int i = 0; i < e1.length; i++)
            System.out.printf("Id of %s is %d\n", e1[i].getName(),e1[i].getId());

        for (int i = 0; i < e1.length; i++)
            if (e1[i].getAge() > 40)
                System.out.printf("Employee %s with salary %d and age of %d\n",e1[i].getName(),e1[i].getSalary(),e1[i].getAge());
        pensionnayaReforma();
        for (int i = 0; i < e1.length; i++)
            if (e1[i].getAge() > 45)
                System.out.printf("Employee %s with salary %d and age of %d\n",e1[i].getName(),e1[i].getSalary(),e1[i].getAge());
        System.out.println(midAge());
        System.out.println(midSalary());
    }
}

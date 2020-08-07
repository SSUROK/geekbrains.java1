package geekbrains.java1.lesson4;

public class employee {
    private String name;
    private int salary;
    private int age;
    private static int numberOfEmployees;
    private final int id;


    employee(String name, int salary, int age){
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.numberOfEmployees++;
        this.id = getNumberOfEmployees();
    }

    public int getAge() {
        return this.age;
    }

    public int getSalary() {
        return this.salary;
    }

    public String getName() {
        return this.name;
    }

    private int getNumberOfEmployees(){
        return numberOfEmployees;
    }

    public int getId() {
        return id;
    }

    public void setSalary(int bonus){
        this.salary += bonus;
    }
}

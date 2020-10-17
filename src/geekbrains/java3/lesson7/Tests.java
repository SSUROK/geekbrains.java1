package geekbrains.java3.lesson7;

public class Tests {

    @Test(name = "1", priority = 1)
    public void test1(){
        System.out.println(1);
    }
    @Test(name = "2", priority = 2)
    public void test2(){
        System.out.println(2);
    }
    @Test(name = "3", priority = 3)
    public void test3(){
        System.out.println(3);
    }
    @AfterSuite(name = "After")
    public void After(){
        System.out.println("after");
    }
    @BeforeSuite(name = "Before")
    public void Before(){
        System.out.println("before");
    }
}

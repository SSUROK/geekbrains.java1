package geekbrains.java3.lesson7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

public class TestOperator {
    private static Object obj;

    public static void start(Class cClass){

        if (!isOne(cClass))
            throw new RuntimeException();

        Method afterSuite = null;
        Method beforeSuite = null;
        Method[] tests  = cClass.getDeclaredMethods();

        ArrayList<Method> mList = new ArrayList<>();

        for(Method method : tests){
            if (method.getAnnotation(Test.class) != null){
                mList.add(method);
            }
            if (method.getAnnotation(BeforeSuite.class) != null){
                beforeSuite = method;
            }
            if (method.getAnnotation(AfterSuite.class) != null){
                afterSuite = method;
            }
        }

        mList.sort(Comparator.comparingInt(c -> c.getAnnotation(Test.class).priority()));

        if (beforeSuite != null)
            mList.add(0,beforeSuite);
        if (afterSuite != null)
            mList.add(afterSuite);


    }
    private static boolean isOne(Class cClass){
        Method[] tests  = cClass.getDeclaredMethods();
        int k = 0;
        for (Method method : tests){
            if (method.getAnnotation(BeforeSuite.class) != null || method.getAnnotation(AfterSuite.class) != null)
                k++;
        }
        return k < 3;
    }
}

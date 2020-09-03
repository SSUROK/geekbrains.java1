package geekbrains.java2.lesson3;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static HashMap<Integer, String> telephoneBook = new HashMap<>();

    private static void uniqueWords (ArrayList list) {
        HashSet<String> unique = new HashSet<>();
        unique.addAll(list);
        for (String s : unique){
            System.out.println(s);
        }
    }

    private static void multipleUsedWords (ArrayList list){
        ArrayList<String> list1 = new ArrayList<>();
        list1.addAll( (Collection) list.stream().distinct().collect(Collectors.toList()) );
        for (int i = 0; i < list1.size(); i++){
            System.out.println(list1.get(i) + " : " + list.stream().filter(list1.get(i)::equals).count());
        }
    }

    private static void add (Integer in, String s){
        telephoneBook.put(in, s);
    }

    private static void get (String s){
        Set<Map.Entry<Integer, String>> set = telephoneBook.entrySet();
        Iterator<Map.Entry<Integer, String>> iter = set.iterator();
        while (iter.hasNext()) {
            Map.Entry<Integer, String> entry = iter.next();
            if (entry.getValue().equals(s))
                System.out.println(entry.getValue() + "=" + entry.getKey());
        }
    }

    public static void main(String[] args){
        ArrayList<String> list = new ArrayList<>();
        list.add("geekbrains");
        list.add("apple");
        list.add("orange");
        list.add("cherry");
        list.add("apple");
        list.add("apple");
        list.add("geekbrains");
        list.add("egg");
        list.add("avokado");
        list.add("melon");
        list.add("lemon");
        list.add("lemon");
        list.add("lime");
        uniqueWords(list);
        System.out.println("---------------------");
        multipleUsedWords(list);
        System.out.println("---------------------");

        add(81111111, "Ivanov");
        add(82222222, "Ivanov");
        add(83333333, "Vasiliev");
        add(84444444, "Petrov");
        add(85555555, "Bocharov");
        add(86666666, "Petrov");
        get("Bocharov");
        System.out.println("---------------------");
        get("Ivanov");
    }
}

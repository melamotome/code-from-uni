import java.util.*;

public class CollectionsExample3 {
    public static void main(String[] args) {
        ArrayList<String> l1 = new  ArrayList<String>();
        ArrayList<String> l2 = new  ArrayList<>();
        List<String> l3 = new ArrayList<>();
        Collection<String> l4 = new ArrayList<>();
        l3.add("Hello");
        l3.add("Cat");
        l3.add("abc");
        System.out.println(l3);
        for (String s : l3) {
            System.out.println(s);
        }
        System.out.println(l3.get(2));
        l4.add("abc");
        //System.out.println(l4.get(0)); //??? l4 collection, doesn't have get, it's not a List
        List<String> l5 = Arrays.asList("aa", "bb", "cc");
        l5.add("dd"); //Ошибка при запуске будет
        String[] s = {"xx", "yy", "zz"};
        List<String> l6 = Arrays.asList(s);
//        List<String> l7 = Collections.nCopies( n:10, o:"asdf");
    }
}

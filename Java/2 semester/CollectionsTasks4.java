import java.io.File;
import java.util.*;
import java.util.stream.Collectors;


/*
Создать список List<String> из чисел от 1 до 100. Здесь и везде реализуйте функцию, которая решает задачу.
В main методе вызовите ее для проверки.

Дан список, верните новый список, в котором все элементы идут в обратном порядке.

Дан List<String>, удалите в нем все элементы с четным индексом.
Дан List<String>, удалить в нем все элементы, которые являются четными числами
Дан List<Integer>, удалить в нем все элементы, которые являются четными числами

Взять текстовый файл, желательно большой на русском.
Прочитать из него все слова, каждое слово привести к нижнему регистру и сохранить в множестве (HashSet, TreeSet, LinkedHashSet).
Вывести все слова. Убедитесь, что вы не дублируете код, и не скопировали программу три раза для каждого из видов множества.
*/
public class CollectionsTasks4 {
    public static void main(String[] args) {
        System.out.println(task1());

        System.out.println(task2(task1()));
        List<Integer> list2 = task1();
        task2Change(list2);
        System.out.println("list2 = " + list2);

        List<String> list3 = createTestList();
        System.out.println(task31(list3));
        task31Change(list3);
        System.out.println("list3 = " + list3);

        List<String> list32 = createTestList();
        System.out.println(task32(list32));
        task32Change(list32);
        System.out.println("list32 = " + list32);

        //noinspection Convert2MethodRef
        List<Integer> list33 = createTestList().stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());
        System.out.println("list33 = " + task33(list33));
        task33Change(list33);
        System.out.println("list33 = " + list33);

        task41(new LinkedHashSet<>(), "LinkedHashSet");
        task41(new HashSet<>(), "HashSet");
        task41(new TreeSet<>(), "TreeSet");
    }

    private static List<String> createTestList() {
        List<String> list3 = new ArrayList<>();
        list3.add("10");
        list3.add("20");
        list3.add("30");
        list3.add("11");
        list3.add("21");
        list3.add("31");
        list3.add("40");
        list3.add("41");
        list3.add("42");
        list3.add("50");
        list3.add("51");
        list3.add("52");
        return list3;
    }

    private static List<Integer> task1() {
        List<Integer> list = new ArrayList<>();
        int i = 1;
        while (i <= 100) {
            list.add(i);
            i += 1;
        }
        return list;
    }

    private static List<Integer> task2(List<Integer> list) {
        List<Integer> lis = new ArrayList<>();
        int i = list.size() - 1;
        while (i >= 0) {
            lis.add(list.get(i));
            i = i - 1;
        }
        return lis;
    }

    private static void task2Change(List<Integer> list) {
        int i = 0;
        int end = list.size() / 2;
        while (i <= end) {
            int firstin = i;
            int lastin = list.size() - 1 - i;
            int temp1 = list.get(firstin);
            int temp2 = list.get(lastin);
            list.set(firstin, temp2);
            list.set(lastin, temp1);
            i = i + 1;
        }
    }

    private static List<String> task31(List<String> list) {
        List<String> lis = new ArrayList<>();
        for (int i = 0; i < list.size(); i++)
            if (i % 2 != 0)
                lis.add(list.get(i));
        return lis;
    }

    private static void task31Change(List<String> list) {
        int i = 0;
        while (i < list.size()) {
            list.remove(i);
            i += 1;
        }
    }

    private static List<String> task32(List<String> list) {
        List<String> lis = new ArrayList<>();
        for (String element : list)
            if (Integer.valueOf(element) % 2 != 0)
                lis.add(element);
        return lis;
    }

    private static void task32Change(List<String> list) {
        int i = 0;
        while (i < list.size())
            if (Integer.valueOf(list.get(i)) % 2 == 0)
                list.remove(i);
            else
                i += 1;
    }

    private static List<Integer> task33(List<Integer> list) {
        List<Integer> lis = new ArrayList<>();
        for (Integer element : list)
            if (element % 2 != 0)
                lis.add(element);
        return lis;
    }

    private static void task33Change(List<Integer> list) {
        int i = 0;
        while (i < list.size()) {
            if (list.get(i) % 2 == 0)
                list.remove(i);
            else
                i += 1;
        }
    }

    private static void task41(Set<String> words, String typeInfo) {
        File f = new File("TheSameBook.txt");
        try (Scanner in = new Scanner(f, "UTF-8")) {
            while (in.hasNext()) {
                String next = in.next().toLowerCase();
                words.add(next);
            }
        } catch (Exception e) {
            System.out.println("Ошибка");
            e.printStackTrace();
        }
        System.out.println(typeInfo + ": words = " + words);
    }
}

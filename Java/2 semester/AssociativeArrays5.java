//1.Дана строка.
//Посчитать каждый символ, сколько раз он встретился.
//Например, строка “banana” должна выдать массив Map<Character, Integer>:
//'b' -> 1
//'a' -> 3
//'n' -> 2
//2.Аналогично предыдущему, но выдать Map<Character, List<Integer>> со списком индексов,
// где встретился символ:
// b -> [0]
// a -> [1, 3, 5]
// n -> [2, 4]
// 3.Частотный словарь слов из файла.
// Т.е. нужно прочитать слова из файла и посчитать, сколько раз они встретились.
// Приводите слова к нижнему регистру перед подсчетом.
// Желательно использовать большой текст на русском, например, возьмите его на http://lib.ru.
// Используйте Scanner.useDelimiter(), чтобы указать сканеру,
// какие символы считать разделителями слов.
//    Сначала используйте HashMap
//    Потом исправьте программу так, чтобы она использовала все три вида массивов: HashMap, TreeMap, LinkedHashMap.
//             Соответственно, выведите ответ три раза. Не дублируйте код.
//    Выведите слова в порядке уменьшения частот.
// Вам потребуется отсортировать список List<Map.Entry<String, Integer»


import java.io.File;
import java.util.*;
import java.util.regex.Pattern;

public class AssociativeArrays5 {
    public static void main(String[] args) {
        System.out.println("countLetters(\"banana\") = " + countLetters("banana"));
        System.out.println("countIndexes(\"banana\") = " + countIndexes("banana"));

        Map<String, Integer> dictLinkedHash = new LinkedHashMap<>();
        Map<String, Integer> dictHash = new HashMap<>();
        Map<String, Integer> dictTree = new TreeMap<>();
        dict(dictLinkedHash);
        dict(dictHash);
        dict(dictTree);
        System.out.println("dictLinkedHash = " + dictLinkedHash);
        System.out.println("dictHash = " + dictHash);
        System.out.println("dictTree = " + dictTree);
        System.out.println("sortByValue(dictTree) = " + sortByValue(dictTree));
    }

    private static Map<Character, Integer> countLetters(String string) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < string.length(); i++) {
            char letter = string.charAt(i);
            int counted = map.getOrDefault(letter, 0);
            map.put(letter, counted + 1);
        }
        return map;
    }

    private static Map<Character, List<Integer>> countIndexes(String string) {
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < string.length(); i++) {
            char letter = string.charAt(i);
            List<Integer> counted = map.getOrDefault(letter, new ArrayList<>());
            counted.add(i);
            map.put(letter, counted);
        }
        return map;
    }

    private static List<Map.Entry<String, Integer>> sortByValue(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> l = new ArrayList<>(map.entrySet());
        l.sort((entry1, entry2) -> entry2.getValue() - entry1.getValue());
        return l;
    }

    private static void dict(Map<String, Integer> words) {
        File f = new File("TheSameBook.txt");
        Pattern wordPat = Pattern.compile("[\\s.,()?!=]+");
        try (Scanner in = new Scanner(f, "UTF-8")) {
            in.useDelimiter(wordPat);
            while (in.hasNext()) {
                String next = in.next().toLowerCase();
                int counted = words.getOrDefault(next, 0);
                words.put(next, counted + 1);
            }
        } catch (Exception e) {
            System.out.println("Ошибка");
            e.printStackTrace();
        }
    }
}

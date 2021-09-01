import java.io.File;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
1.Дано n. Посчитайте сумму:
1.1 Чисел от 1 до n
1.2 Квадратов чисел от 1 до n
1.3 Кубов чисел от 1 до n
1.4 Обратных чисел от 1 до n
2.Дан список чисел. Верните новый список, в котором удалены все четные числа.
3.Дан файл. Посчитайте среднюю длину слов в этом файле. А еще максимальные и минимальные длины слов.
Используйте подходящий Collector.
Найдите самое длинное слово в файле. Получится сделать это за один проход по потоку?
4.Бросьте монетку 100 раз, сколько раз выпал орёл, а сколько решка?
5.Еще будут
 */
public class Streams6 {
    public static void main(String[] args) {
        System.out.println("sum1ToN(10) = " + sum1ToN(10));
        System.out.println("sumSquared(10) = " + sumSquared(10));
        System.out.println("sumCube(10) = " + sumCube(10));
        System.out.println("sumBackward(10) = " + sumBackward(10));
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(11);
        list.add(21);
        list.add(31);
        list.add(40);
        list.add(41);
        list.add(42);
        list.add(50);
        list.add(51);
        list.add(52);
        System.out.println("deleteEven(list) = " + deleteEven(list));
        File f = new File("TheSameBook.txt");
        text(f);
        coin();
    }

    private static int sum1ToN(int n) {
        return IntStream.rangeClosed(1, n).sum();
    }

    private static int sumSquared(int n) {
        return IntStream.rangeClosed(1, n).map(x -> x * x).sum();
    }

    private static int sumCube(int n) {
        return IntStream.rangeClosed(1, n).map(x -> x * x * x).sum();
    }

    private static double sumBackward(int n) {
        return IntStream.rangeClosed(1, n).mapToDouble(x -> 1 / (double) x).sum();
    }

    private static List<Integer> deleteEven(List<Integer> list) {
        return list.stream().filter(x -> x % 2 != 0).collect(Collectors.toList());
    }

    private static Stream<String> wordsStream(File f) {
        try {
            byte[] fileAsBytes = Files.readAllBytes(f.toPath());
            String fileAsText = new String(fileAsBytes, "UTF-8");
            return Arrays.stream(fileAsText.split("\\s+"));
        } catch (Exception e) {
            System.out.println("Failed to read file " + f);
            return Stream.of();
        }
    }

    private static void text(File file) {
        Optional<Integer> maxLength = wordsStream(file).map(x -> x.length()).max(Comparator.comparingInt(p -> p));
        int maxLengthInt = maxLength.orElse(0);

        String longWord = wordsStream(file).filter(s -> s.length() == maxLengthInt).collect(Collectors.joining());

        Optional<Integer> minLength = wordsStream(file).map(x -> x.length()).min(Comparator.comparingInt(p -> p));
        int minLengthInt = minLength.orElse(0);

//      int total = (int) wordsStream(file).count();
//      int sumOfLengths = wordsStream(file).map(x -> x.length()).mapToInt(p -> p).sum();
        IntSummaryStatistics stat = wordsStream(file).collect(Collectors.summarizingInt(p -> p.length()));
        double average = stat.getAverage();
//        int maxLengthInt = stat.getMax();
//        int minLengthInt = stat.getMin();

        System.out.println("maxLength = " + maxLengthInt);
        System.out.println("longWord = " + longWord);
        System.out.println("minLength = " + minLengthInt);
        System.out.println("Average length = " + (int) average);

    }

    private static void coin() {
//        решка 0, орёл 1
        Random r = new Random();
        int heads = IntStream.generate(() -> r.nextInt(2)).limit(100).sum();
        System.out.printf("Орёл %d, решка %d", heads, 100 - heads);
    }
}

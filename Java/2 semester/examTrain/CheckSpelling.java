package examTrain;

import java.io.File;
import java.io.PrintStream;
import java.util.*;
import java.util.regex.Pattern;

public class CheckSpelling {
    public static void main(String[] args) {
        //read the vocabulary
        Set<String> words = new HashSet<>();
        try (Scanner in = new Scanner(new File("C:\\Users\\Chris\\Google Диск\\Uni\\Semester 2\\Основы программирования\\src\\examTrain\\words.txt"), "UTF-8")) {
            while (in.hasNext())
                if (in.hasNextLine()) {
                    words.add(in.nextLine());
                } else if (in.hasNext())
                    System.out.println(in.next());
        } catch (Exception e) {
            System.out.println("Ошибка чтения словаря");
//            e.printStackTrace();
        }

        //read the text
        File input = new File("C:\\Users\\Chris\\Google Диск\\Uni\\Semester 2\\Основы программирования\\src\\examTrain\\input.txt");
        File output = new File("C:\\Users\\Chris\\Google Диск\\Uni\\Semester 2\\Основы программирования\\src\\examTrain\\output.txt");
        try (Scanner in = new Scanner(input, "UTF-8"); PrintStream out = new PrintStream(output)) {
            in.useDelimiter(Pattern.compile("[^a-zA-Z]+"));
            while (in.hasNext()) {
                String word = in.next().toLowerCase();
                if (!words.contains(word)) {
                    out.printf("%s: ", word);
                    List<WordCorrector> correctors = new ArrayList<>();
                    correctors.add(new ChangeLetterCorrector());
                    correctors.add(new DeleteLetterCorrector());
                    correctors.add(
                        (String s) -> {
                            Set<String> corrections = new HashSet<>();
                            for (int i = 0; i < s.length(); i++) {
                                for (char c = 'a'; c <= 'z'; c++) {
                                    String correctedWord = s.substring(0, i) + c + s.substring(i);
                                    corrections.add(correctedWord);
                                }
                            }
                            return corrections;
                        }
                    );
                    correctors.add(s -> {
                        Set<String> corrections = new HashSet<>();
                        for (int i = 0; i < s.length() - 1; i++) {
                            String correctedWord = s.substring(0, i) + s.substring(i + 1, i + 2) + s.substring(i, i + 1) + s.substring(i + 2);
                            corrections.add(correctedWord);
                        }
                        return corrections;
                    });
                    Set<String> corrections = new HashSet<>();
                    for (WordCorrector corrector : correctors) {
                        for (String propos : corrector.proposeCorrections(word)) {
                            if (words.contains(propos))
                                corrections.add(propos);
                        }
                    }
                    out.println(String.join(", ", corrections));
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка чтения input или записи output");
//            e.printStackTrace();
        }
    }
}

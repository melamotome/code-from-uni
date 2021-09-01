package exam;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Ex {
    public static void main(String[] args) {
        changeWords("text.txt", "p1.txt", x -> x.toUpperCase());
        changeWords("text.txt", "p4.txt", x -> String.valueOf(x.length()));
        changeWords("text.txt", "p5.txt", new DictionaryTransformer("words_alpha.txt"));
        changeWords("p5.txt", "text_back.txt", new DictionaryBackTransformer("words_alpha.txt"));
    }

    private static void changeWords(String inName, String outName, WordTransformer how) {
        File input = new File(inName);
        File output = new File(outName);
        try (Scanner in = new Scanner(input, "UTF-8"); PrintStream out = new PrintStream(output)) {
            in.useDelimiter(Pattern.compile("[^a-zA-Z0-9]+"));
            while (in.hasNext()) {
                out.printf("%s ", how.transform(in.next()));
            }
        } catch (Exception e) {
            System.out.println("Ошибка чтения или записи");
            e.printStackTrace();
        }
    }
}

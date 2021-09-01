package exam;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class WordsTransformerWithList implements WordTransformer {
    protected List<String> dict = new ArrayList<>();

    protected WordsTransformerWithList(String fileName) {
        File input = new File(fileName);
        try (Scanner in = new Scanner(input, "UTF-8")) {
            while (in.hasNextLine())
                dict.add(in.nextLine());
        } catch (Exception e) {
            System.out.println("Ошибка");
            e.printStackTrace();
        }
    }
}

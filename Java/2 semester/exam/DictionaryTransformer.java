package exam;

public class DictionaryTransformer extends WordsTransformerWithList {
    public DictionaryTransformer(String fileName) {
        super(fileName);
    }

    @Override
    public String transform(String word) {
        word = word.toLowerCase();
        if (!dict.contains(word)) {
            word = "xxx" + word;
        } else {
            word = Integer.toString(dict.indexOf(word));
        }
        return word;
    }
}

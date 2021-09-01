package exam;

public class DictionaryBackTransformer extends WordsTransformerWithList {
    public DictionaryBackTransformer(String fileName) {
        super(fileName);
    }

    @Override
    public String transform(String word) {
        word = word.toLowerCase();
        if (word.length() >= 3 && word.substring(0, 3).equals("xxx")) {
            word = word.substring(3);
        } else if (word.matches("\\d{1,8}")){
            word = dict.get(Integer.valueOf(word));
        }
        return word;
    }
}

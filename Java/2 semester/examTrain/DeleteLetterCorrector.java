package examTrain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

//По очереди удаляет каждую букву слова
public class DeleteLetterCorrector implements WordCorrector {
    @Override
    public Collection<String> proposeCorrections(String word) {
        Set<String> corrections = new HashSet<>();
        for (int i = 0; i < word.length(); i++) {
            String correctedWord = word.substring(0, i) + word.substring(i + 1);
            corrections.add(correctedWord);
        }
        return corrections;
    }
}

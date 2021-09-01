package examTrain;

import java.util.Collection;

public interface WordCorrector {
    Collection<String> proposeCorrections(String word);
}

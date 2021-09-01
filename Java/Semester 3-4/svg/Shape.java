package svg;

import java.io.IOException;
import java.util.List;

public interface Shape {
    List<Tag> getTags();

    default void draw(SVG svg) {
        List<Tag> listOfTags = getTags();
        for (Tag tag : listOfTags) {
            svg.addTag(tag);
        }
    }
}

package svg;

import java.util.ArrayList;
import java.util.List;

public class SmallSquare implements Shape {
    @Override
    public List<Tag> getTags() {
        List<Tag> listOfTags = new ArrayList<>();

        Tag square = new Tag("rect", TagType.OPEN_AND_CLOSE);
        square.set("x", "-5");
        square.set("y", "-5");
        square.set("height", "10");
        square.set("width", "10");
        listOfTags.add(square);

        return listOfTags;
    }
}

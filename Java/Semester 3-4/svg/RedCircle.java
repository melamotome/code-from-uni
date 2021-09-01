package svg;

import java.util.ArrayList;
import java.util.List;

public class RedCircle implements Shape {
    @Override
    public List<Tag> getTags() {
        List<Tag> listOfTags = new ArrayList<>();

        Tag circle = new Tag("circle", TagType.OPEN_AND_CLOSE);
        circle.set("cx", "-5");
        circle.set("cy", "-5");
        circle.set("r", "5");
        circle.set("style", "stroke:#ff0000; fill: #ff0000");
        listOfTags.add(circle);

        return listOfTags;
    }
}

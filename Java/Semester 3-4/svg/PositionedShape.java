package svg;

import java.util.ArrayList;
import java.util.List;

public class PositionedShape implements Shape {
    private Shape shape;
    private int x;
    private int y;

    public PositionedShape(Shape shape, int x, int y) {
        this.shape = shape;
        this.x = x;
        this.y = y;
    }

    @Override
    public List<Tag> getTags() {
        List<Tag> listOfTags = new ArrayList<>();
        Tag gOpen = new Tag("g", TagType.OPEN);
        gOpen.set("transform", "translate(" + x + "," + y + ")");
        listOfTags.add(gOpen);

        List<Tag> shapeTags = shape.getTags();
        listOfTags.addAll(shapeTags);

        listOfTags.add(new Tag("g", TagType.CLOSE));
        return listOfTags;
    }
}

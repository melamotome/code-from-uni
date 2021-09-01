package svg;
//Класс Tag - это описание одного тега.
// Tag rect1 = new Tag("rect");
// rect1.set("x", "200");
// rect1.set("y", "200");
// rect1.set("width", "10");
// rect1.set("height", "20");
// rect1.set("style", "stroke:#ff0000; fill: #0000ff");

import java.util.HashMap;
import java.util.Map;

public class Tag {
    //Тег мы будем хранить как его название,
    //плюс пары атрибутов и их значений,
    //чтобы иметь возможность потом это всё написать красиво текстом
    public String tagName;
    public Map<String, String> attributes;
    public TagType typeOfTag;

    public Tag(String tagName, TagType typeOfTag) {
        this.tagName = tagName;
        this.typeOfTag = typeOfTag;
        attributes = new HashMap<>();
    }

    public void set(String attribute, String value) {
        attributes.put(attribute, value);
    }
}


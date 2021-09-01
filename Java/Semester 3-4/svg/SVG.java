package svg;
//Класс SVG, содержит открытый PrintStream для печати SVG:
// SVG svg = new SVG("a.svg", 300, 300);
// svg.addTag(rect1);
// svg.addTag(rect2);
// svg.close();
//В конце обязательно будет вызвано закрытие, чтобы закрыть PrintStream

import java.io.*;

public class SVG implements AutoCloseable{
    private final String fileOfSVGString;
    private final int width;
    private final int height;
    private final PrintStream out;

    public SVG(String fileOfSVGString, int width, int height) throws IOException{
        //Здесь зададим поля с высотой и шириной и создадим файл, в который будем записывать свг уже вот строчками
        this.fileOfSVGString = fileOfSVGString;
        this.width = width;
        this.height = height;
        File fileOfSVG = new File(fileOfSVGString);
        out = new PrintStream(fileOfSVG, "UTF-8");
        out.println("<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"" + width + "\" height=\"" + height +"\">");
    }

    public void addTag(Tag tag) {
        //Берем тег и все его атрибуты и записываем по синтаксису всё
        switch (tag.typeOfTag) {
            case OPEN:
                out.print("    <" + tag.tagName);
                tag.attributes.forEach(
                        (attribute, value) -> out.print(" " + attribute + "=\"" + value + "\"")
                );
                out.println(">");
                break;
            case CLOSE:
                out.println("</" + tag.tagName + ">");
                break;
            case OPEN_AND_CLOSE:
                out.print("    <" + tag.tagName);
                tag.attributes.forEach(
                        (attribute, value) -> out.print(" " + attribute + "=\"" + value + "\"")
                );
                out.println("/>");
                break;
        }
    }

    @Override
    public void close() {
        //Закрывает тег свг и файл
        out.println("</svg>");
        out.close();
    }
}

import TurtleGraphics.Pen;
import TurtleGraphics.StandardPen;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Draw {
    public static void main(String[] args) throws IOException {
        //1.3
        String s = "F";
        StringTransformer trf = (str) -> str + "+" + str + "--" + str + "+" + str;
        System.out.println(trf.transform(s, 1));
        System.out.println(trf.transform(s, 2));

        //1.4
        String tenFs = trf.transform(s, 10);
        Files.writeString(Path.of("fs.txt"), tenFs);

        //2.1
        String svgRectangle = "<rect x=\"0\" width=\"100\" y=\"0\" style=\"stroke:#000000; fill: #edce95\" height=\"100\"/>";

        //2.2
        StringTransformer squares = (str) -> "<g transform=\"scale(0.333333333333 0.3333333333333)\">" +
                "<g transform=\"translate(0, 0)\">" +
                str +
                "</g>" +
                "<g transform=\"translate(100, 0)\">" +
                str +
                "</g>" +
                "<g transform=\"translate(200, 0)\">" +
                str +
                "</g>" +
                "<g transform=\"translate(0, 100)\">" +
                str +
                "</g>" +
                "<g transform=\"translate(0, 200)\">" +
                str +
                "</g>" +
                "<g transform=\"translate(200, 100)\">" +
                str +
                "</g>" +
                "<g transform=\"translate(100, 200)\">" +
                str +
                "</g>" +
                "<g transform=\"translate(200, 200)\">" +
                str +
                "</g>" +
                "</g>";

        //2.3
        String inputString = "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"100\" height=\"100\">" +
                squares.transform(svgRectangle, 4) +
                "</svg>";
        Files.writeString(Path.of("squares.svg"), inputString);

        //3.1, 3.2
        Pen turtle = new StandardPen();
        turtle.up();
        turtle.setColor(Color.RED);
        turtle.down();

        //3.3, 3.4
        int len = tenFs.length();
        int step = 5;
        for (int i = 0; i < len; i++) {
            char ch = tenFs.charAt(i);
            switch (ch) {
                case 'F':
                    turtle.move(step);
                    break;
                case '+':
                    turtle.turn(60);
                    break;
                case '-':
                    turtle.turn(-60);
                    break;
                default:
                    System.out.println("Invalid command to turtle");
                    break;
            }
        }
    }
}

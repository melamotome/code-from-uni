package svg;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class DoingSVG {
    public static void main(String[] args) {
        task2("rect.svg");
        task4("doubleRects.svg");

        try (SVG svg = new SVG("task5.svg", 300, 300)){
            SmallSquare square = new SmallSquare();
            RedCircle circle = new RedCircle();
            square.draw(svg);
            circle.draw(svg);
        } catch (IOException e) {
            System.out.println("Ошибка создания SVG");
        }

        try (SVG svg = new SVG("task6.svg", 300, 300)){
            PositionedShape square = new PositionedShape(new SmallSquare(), 5, 5);
            PositionedShape circle = new PositionedShape(new RedCircle(), 20, 20);
            square.draw(svg);
            circle.draw(svg);
        } catch (IOException e) {
            System.out.println("Ошибка создания SVG");
        }

        task7("LotsOfShapes.svg");
        taskSettings("ShapesFromSettings.svg");
    }

    private static void task2(String SVGFileString) {
        Tag rect = new Tag("rect", TagType.OPEN_AND_CLOSE);
        rect.set("x", "10");
        rect.set("y", "10");
        rect.set("height", "100");
        rect.set("width", "100");
        rect.set("style", "stroke:#ff0000; fill: #0000ff");
        try (SVG svg = new SVG(SVGFileString, 300, 300)){
            //В формате try with resource,
            //так как интерфейс AutoCloseable свг применяет метод close
            //и нам не надо его прописывать
            svg.addTag(rect);
        } catch (IOException e) {
            System.out.println("Ошибка создания SVG");
        }
    }

    private static void task4(String SVGFileString) {
        //Имплементирование вложенных тегов
        Tag rect1 = new Tag("rect", TagType.OPEN_AND_CLOSE);
        rect1.set("x", "10");
        rect1.set("y", "10");
        rect1.set("height", "100");
        rect1.set("width", "100");
        rect1.set("style", "stroke:#ff0000; fill: #0000ff");

        Tag rect2 = new Tag("rect", TagType.OPEN_AND_CLOSE);
        rect2.set("x", "20");
        rect2.set("y", "20");
        rect2.set("height", "100");
        rect2.set("width", "100");
        rect2.set("style", "stroke:#ff0000; fill: #00ff00");

        Tag g = new Tag("g", TagType.OPEN);
        g.set("transform", "translate(150, 150)");

        Tag gClose = new Tag("g", TagType.CLOSE);

        try (SVG svg = new SVG(SVGFileString, 300, 300)) {
            svg.addTag(rect1);
            svg.addTag(rect2);
            svg.addTag(g);
            svg.addTag(rect1);
            svg.addTag(rect2);
            svg.addTag(gClose);
        } catch (IOException e) {
            System.out.println("Ошибка создания SVG");
        }
    }

    private static void task7(String SVGFileString) {
        try (SVG svg = new SVG(SVGFileString, 400, 400)){
            Random random = new Random();
            for (int i = 0; i < 100; i++) {
                PositionedShape square = new PositionedShape(new SmallSquare(), random.nextInt(400), random.nextInt(400));
                square.draw(svg);
            }

            for (int i = 0; i < 150; i++) {
                PositionedShape circle = new PositionedShape(new RedCircle(), random.nextInt(400), random.nextInt(400));
                circle.draw(svg);
            }
        } catch (IOException e) {
            System.out.println("Ошибка создания SVG");
        }
    }

    private static void taskSettings(String SVGFileString) {
        //getting rectangle for background
        Tag bg = new Tag("rect", TagType.OPEN_AND_CLOSE);
        bg.set("x", "0");
        bg.set("y", "0");
        Integer bgHeight = Settings.getInstance().getHeight();
        bg.set("height", String.valueOf(bgHeight));
        Integer bgWidth = Settings.getInstance().getWidth();
        bg.set("width", String.valueOf(bgWidth));
        bg.set("style", "stroke:#ff0000; fill: " + Settings.getInstance().getBackground());
        //creating svg
        try (SVG svg = new SVG(SVGFileString, Settings.getInstance().getWidth(), Settings.getInstance().getHeight())){
            //drawing background
            svg.addTag(bg);
            //drawing shapes nowwww:
            //implementing getting randSeed from Settings to customize random  of creating shapes
            int randSeed = Settings.getInstance().getRandSeed();
            Random random = new Random();
            if (randSeed != 0) {
                random = new Random(randSeed);
            }

            ShapeFactory shapeFactory = new ShapeFactory();
            HashMap<String, Integer> shapesToDraw = Settings.getInstance().getShapesWithCount();
            for (HashMap.Entry<String, Integer> entry : shapesToDraw.entrySet()) {
                String shapeName = entry.getKey();
                Integer howMuch = entry.getValue();
                for (int i = 0; i < howMuch; i++) {
                    String shapeDescription = Settings.getInstance().getShapeDescription(shapeName);
                    PositionedShape shapeToDraw = null;
                    try {
                        shapeToDraw = new PositionedShape(shapeFactory.create(shapeDescription), random.nextInt(bgWidth), random.nextInt(bgHeight));
                    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                        e.printStackTrace();
                    }
                    shapeToDraw.draw(svg);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка создания SVG");
        }
    }
}

package svg;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Settings {
    private static Settings instance = new Settings();
    private int width;
    private int height;
    private String randSeed;
    private String background;
    private Properties p;

    public static Settings getInstance() {
        return instance;
    }

    private Settings() {
        p = new Properties();
        try {
            p.load(new InputStreamReader(
                    new FileInputStream("svg.properties"),
                    StandardCharsets.UTF_8)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        width = Integer.parseInt(p.getProperty("width", "500"));
        height = Integer.parseInt(p.getProperty("height", "500"));
        randSeed = p.getProperty("rand_seed", "0");
        background = p.getProperty("background", "#FFFFFF");
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getBackground() {
        return background;
    }

    public int getRandSeed() {
        if (randSeed.equals("auto"))
            randSeed = "0";
        return Integer.parseInt(randSeed);
    }

    public String getShapeDescription(String shapeName) {
        //получает "red_circle", даёт "svg.RedCircle", оно как раз после shape.инпут=
        return p.getProperty("shape." + shapeName);
    }

    public HashMap<String, Integer> getShapesWithCount() {
        //from string draw=red_circle:150 small_square:100 return 150 for red_circle etc
        String draw = p.getProperty("draw");
        return (HashMap<String, Integer>) Arrays.stream(draw.split(" "))
                .map(s -> s.split(":"))
                .collect(Collectors.toMap(e -> e[0], e -> Integer.parseInt(e[1])));
    }
}

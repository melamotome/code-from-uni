package patterns;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class PropertiesFileExample {
    public static void main(String[] args) throws IOException{
        Properties p = new Properties();
        p.load(new FileInputStream("example.properties"));
        p.load(new InputStreamReader(
                new FileInputStream("example.properties"),
                StandardCharsets.UTF_8
        ));

        String name = p.getProperty("name");
        String age = p.getProperty("age");
        String city = p.getProperty("city", "Moscow");
        System.out.println(name + " " + age + " " + city);
    }
}

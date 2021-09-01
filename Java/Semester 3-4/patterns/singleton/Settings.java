package patterns;

import javafx.scene.paint.Color;

public class Settings {
    private static Settings instance = new Settings();

    public static Settings getInstance() {
        return instance;
    }

    private Settings() {
        //загрузить настройки
    }

    public Color getBackground() {
        return Color.RED;
    }

    public String getUserName() {
        return "Chris";
    }
}

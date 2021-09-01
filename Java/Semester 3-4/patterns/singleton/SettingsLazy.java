package patterns;

public class SettingsLazy {
    private static SettingsLazy instance = null;

    private static SettingsLazy getInstance() {
        //Создать объект, если он ещё не создан
        if (instance == null)
            instance = new SettingsLazy();
        return instance;
    }

    private SettingsLazy() {
        //Долгое и дорогое создание объекта
    }
}

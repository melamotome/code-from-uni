package objects.interfaces;

// Интерфейс реализуют (implements), а не расширяют (extends)
public class Cat implements Animal {

    private String name;

    public Cat(String name) { // кот при создании запоминает своё имя
        this.name = name;
    }

    @Override
    public String getSound() {
        return "Meow";
    }

    @Override
    public String getName() {
        return name;
    }
}

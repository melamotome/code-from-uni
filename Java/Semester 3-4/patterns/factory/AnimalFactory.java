package patterns.factory;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnimalFactory {

    // фабрика нужна, чтобы следить за процессом создания объектов

    private List<Cat> cats = new ArrayList<>();
    private List<Dog> dogs = new ArrayList<>();

    public Cat createCat(String name) {
        Cat cat = new Cat(name);
        cats.add(cat);
        return cat;
    }

    public Dog createDog(String name) {
        Dog dog = new Dog(name);
        dogs.add(dog);
        return dog;
    }

    public List<Cat> getAllCats() {
        // чтобы тот, кто получит список котов, не мог его изменять
        return Collections.unmodifiableList(cats);
    }

    public List<Dog> getAllDogs() {
        return Collections.unmodifiableList(dogs);
    }
}

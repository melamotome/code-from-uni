package objects.abstractinheritance;

public abstract class Animal {

    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // у абстрактного метода не пишется тело.
    public abstract String getSound();

    public void greet() {
        System.out.println("Hallo, I'm " + name);
    }
}

package patterns.factory;

public class Animal {

    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String greet() {
        return "Hello, my name is " + name;
    }
}

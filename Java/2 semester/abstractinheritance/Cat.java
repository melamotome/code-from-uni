package objects.abstractinheritance;


public class Cat extends Animal {

    public Cat() {
        super("Мурзик");
    }

    public Cat(String name) {
        super(name);
    }

    private int miceCount = 0;
    public void catchAMouse() {
        miceCount++;
        System.out.println(getSound() + ", " + miceCount);
    }

    @Override
    public String getSound() {
        return "Meow";
    }
}

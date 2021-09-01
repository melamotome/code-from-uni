package objects.abstractinheritance;

public class Cat extends Animal{

    public Cat() {
        super("Murzik");
        System.out.println("Meoow");
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
        return "MEOOOOOWW";
    }
}

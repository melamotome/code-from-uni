package interfacelambdaanonclasses;

import drawing.Drawing;

public class interfacelambdaanonclassesTest {
    public static void main(String[] args) {
        Printable[] arr = new Printable[5];
        arr[0] = new Drawing(5, 2, '~');
        arr[1] = new PrintableLetter('@', 7);
        arr[2] = new PrintableString("asdf");
        arr[3] = new Printable() {
            @Override
            public void print() {
                System.out.println("I'm anonymous");
            }
        };
        arr[4] = () -> System.out.println("lambda");

        for (Printable printable : arr) {
            printable.print();
        }
    }
}

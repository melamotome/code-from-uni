package interfacelambdaanonclasses;

public class PrintableLetter implements Printable {
    private char letter;
    private int n;

    public PrintableLetter(char letter, int n) {
        this.letter = letter;
        this.n = n;
    }

    @Override
    public void print() {
        for (int i = 0; i < n; i++) {
            System.out.printf("%c",letter);
        }
        System.out.println();
    }
}

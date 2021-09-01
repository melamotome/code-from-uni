package exceptions;

public class FirstTask {
    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            try {
                f(i);
            } catch (Exception e) {
                System.out.printf("%d Есть исключение\n", i);
                continue;
            }
            System.out.printf("%d Нет исключения\n", i);
        }
    }

    private static void f(int x) throws Exception {
        if (x % 2 == 0) {
            throw new MyException();
        }
    }
}

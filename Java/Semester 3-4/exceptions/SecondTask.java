package exceptions;

import java.util.Scanner;

public class SecondTask {

    private static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        int x = readInt("Введите x");
        int y = readInt("Введите y");
        System.out.println("x + y = " + (x + y));
    }
    private static int readInt(String message) {
        System.out.println(message);
        while (true) {
            try {
                String line = s.nextLine();
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели не число, введите снова");
            }
        }
    }
}

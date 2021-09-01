package objects.interfaces;

public class LambdasExample {

    public static void main(String[] args) {
        // Создаём считателя.
        Counter c1 = new Counter() {
            @Override
            public String count(int x) {
                return "*" + x;
            }
        };

        doCount(c1);

        Counter c2 = (int x) -> {
            return "(" + x + ")";
        };
        // Вместо аноимного класса пишем только аргументы метода, потом стрелка, потом тело
        doCount(c2);
        // Метод Integer.toString превращает в текст по основанию
        doCount(x -> Integer.toString(x, 2));
        // Как и раньше, тип параметра не нужен.
        // Если в теле метода только return, можно и без него.

    }

    private static void doCount(Counter c) {
        for(int i = 1; i <=10; i++)
            System.out.println(i + "-->" + c.count(i));
    }
}

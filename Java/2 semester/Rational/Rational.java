package Rational;
//Сделаем класс Rational, это рациональные числа, для которых хранится их числитель и знаменатель.
// С рациональными числами можно совершать арифметические операции.
//1.Создайте класс и добавьте поля n и d (numerator, denominator - числитель, знаменатель).
//Убедитесь, что поля приватные.

//2.Класс должен иметь конструкторы вида Rational(3, 2) и Rational(3).

//3.Добавьте метод public String toString(),
// который возвращает естественное представление числа в виде строки, например, 5/7.
// Если знаменатель равен 1, его не нужно писать. Если числитель ноль, то “0”, а не “0/2” и т.п.

//4.Добавьте метод double toDouble(), который возвращает значение в виде double.

//5.Добавьте сокращение дроби в конструктор: поделить числитель и знаменатель на НОД,
// убедиться, что знаменатель положительный.

//6.Арифметические функции. add, sub, mul, div:
//Rational r1 = r2.add(r3); // создает новое число
//r2.addInPlace(r3); // добавляет к r2 и изменяет его
//Протестируйте! Проверьте, например, что одна шестая плюс одна третья = одна вторая
//, и проверьте другие аналогичные равенства для других арифметических операций.

//7.Создайте функцию не в Rational, которая по n считает
// 1 + 1/2 + 1/3 + .. + 1/n
//Проверьте, что f(1) = 1, f(2) = 1.5, f(3) = 1.833333333333333

//8.Чему равно f(20)? Почему оно равно тому, чему равно? Как исправить?

//9.Добавьте get- методы для числителя и знаменателя. Чтобы Rational стал совсем неизменяемым,
//закомментируйте методы *InPlace().

//10.Создайте статические константы ONE и ZERO типа Rational, хранящие элементы 0, 1 в виде рациональных чисел.

//11.Сделайте статическим метод поиска НОД (или сокращения)

//11.Создайте статические версии арифметических операций: static Rational add(Rational r1, Rational r2) и т.п.
//Эти операции создают новое число, вы можете переиспользовать старые арифметические операции.
public class Rational {
    private int n; // числитель, должно быть целое
    private int d; // знаменатель, должно быть натуральное

    public static final Rational ZERO = new Rational(0);
    public static final Rational ONE = new Rational(1,1);

    public Rational(int n, int d) {
        int gcd = gcd(n, d);
        this.n = n / gcd;
        this.d = d / gcd;
    }

    public Rational(int n) {
        this.n = n;
        this.d = 1;
    }

    public int getN() {
        return n;
    }

    public int getD() {
        return d;
    }

    public String toString() {
        if (d == 1)
            return String.format("%d", n);
        else
            return String.format("%d/%d", n, d);
    }

    public double toDouble() {
        return (double) n / d;
    }

    private static int gcd(int a, int b) {
        if (a == 0)
            return b;
        else if (b == 0)
            return a;
        else {
            a = Math.abs(a);
            b = Math.abs(b);
            while (b != 0) {
                int t = b;
                b = a % b;
                a = t;
            }
            return a;
        }
    }

    public Rational add(Rational a) {
        int num = this.n * a.d + a.n * this.d;
        int den = this.d * a.d;
        return new Rational(num, den);
    }

    public static Rational add(Rational a, Rational b) {
        return a.add(b);
    }

//    public void addInPlace(Rational a) {
//        int num = this.n * a.d + a.n * this.d;
//        int den = this.d * a.d;
//        int gcd = gcd(num, den);
//        n = num / gcd;
//        d = den / gcd;
//    }

    public Rational sub(Rational a) {
        int num = this.n * a.d - a.n * this.d;
        int den = this.d * a.d;
        return new Rational(num, den);
    }

    public static Rational sub(Rational a, Rational b) {
        return a.sub(b);
    }

//    public void subInPlace(Rational a) {
//        int num = this.n * a.d - a.n * this.d;
//        int den = this.d * a.d;
//        int gcd = gcd(num, den);
//        n = num / gcd;
//        d = den / gcd;
//    }

    public Rational mul(Rational a) {
        int num = this.n * a.n;
        int den = this.d * a.d;
        return new Rational(num, den);
    }

    public static Rational mul(Rational a, Rational b) {
        return a.mul(b);
    }

//    public void mulInPlace(Rational a) {
//        int num = this.n * a.n;
//        int den = this.d * a.d;
//        int gcd = gcd(num, den);
//        n = num / gcd;
//        d = den / gcd;
//    }

    public Rational div(Rational a) {
        int num = this.n * a.d;
        int den = this.d * a.n;
        return new Rational(num, den);
    }

    public static Rational div(Rational a, Rational b) {
        return a.div(b);
    }

//    public void divInPlace(Rational a) {
//        int num = this.n * a.d;
//        int den = this.d * a.n;
//        int gcd = gcd(num, den);
//        n = num / gcd;
//        d = den / gcd;
//    }
}

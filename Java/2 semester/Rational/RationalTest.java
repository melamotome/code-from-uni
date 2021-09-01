package Rational;

public class RationalTest {
    public static void main(String[] args) {
        Rational number1 = new Rational(6,4);
        Rational number2 = new Rational(3);
        Rational number3 = new Rational(7, 1);
        Rational number4 = new Rational(0, 3);
        System.out.println("number1.getD() = " + number1.getD());
        System.out.println("number1.getN() = " + number1.getN());
        System.out.println("toString(number1) = " + number1.toString());
        System.out.println("toString(number2) = " + number2.toString());
        System.out.println("toString(number3) = " + number3.toString());
        System.out.println("toString(number4) = " + number4.toString());
        System.out.println("number1.toDouble() = " + number1.toDouble());
        System.out.println("number2.toDouble() = " + number2.toDouble());
        System.out.println("number3.toDouble() = " + number3.toDouble());
        System.out.println("number4.toDouble() = " + number4.toDouble());

        Rational aa = new Rational(1, 6);
        Rational bb = new Rational(1, 3);
        System.out.println("aa.add(bb) = " + aa.add(bb));
        System.out.println("Rational.add(aa, bb) = " + Rational.add(aa, bb));
//        a.subInPlace(b);
//        System.out.println("a = " + a);

        Rational a = new Rational(1, 6);
        Rational b = new Rational(1, 3);
        System.out.println("a.sub(b) = " + a.sub(b));
        System.out.println("Rational.sub(a, b) = " + Rational.sub(a, b));
//        a.subInPlace(b);
//        System.out.println("a = " + a);

        Rational c = new Rational(1, 6);
        Rational d = new Rational(1, 3);
        System.out.println("c.mul(d) = " + c.mul(d));
        System.out.println("Rational.mul(c, d) = " + Rational.mul(c, d));
//        c.mulInPlace(d);
//        System.out.println("c = " + c);

        Rational cc = new Rational(1, 6);
        Rational dd = new Rational(1, 3);
        System.out.println("cc.div(dd) = " + cc.div(dd));
        System.out.println("Rational.div(cc, dd) = " + Rational.div(cc, dd));
//        cc.divInPlace(dd);
//        System.out.println("cc = " + cc);

        System.out.println("f(1) = " + f(1));
        System.out.println("f(1).toDouble() = " + f(1).toDouble());
        System.out.println("f(2) = " + f(2));
        System.out.println("f(2).toDouble() = " + f(2).toDouble());
        System.out.println("f(3) = " + f(3));
        System.out.println("f(3).toDouble() = " + f(3).toDouble());
        System.out.println("f(20) = " + f(20));
        System.out.println("f(20).toDouble() = " + f(20).toDouble());

        System.out.println("Rational.ONE = " + Rational.ONE);
        System.out.println("Rational.ZERO = " + Rational.ZERO);

        Rational bbb = new Rational(1, 3);
        System.out.println("Rational.ZERO.add(bbb) = " + Rational.ZERO.add(bbb));
        System.out.println("Rational.ONE.add(bbb) = " + Rational.ONE.add(bbb));
    }

    private static Rational f(int n) {
        Rational sum = Rational.ZERO;
        for (int i = 1; i <= n; i++) {
            Rational num = new Rational(1, i);
            sum  = Rational.add(sum, num);
        }
        return sum;
    }
}

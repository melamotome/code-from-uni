package fractals;

public class MandelbrotFractal implements Fractal {
    @Override
    public double getColorIndex(double x, double y) {
        int N = 1000;
        long R = 1000000000;
        //c = x + yi
        //z = a + bi
        double a = 0;
        double b = 0;
        for (int i = 0; i <= N; i++) {
            double tempa = a*a-b*b;
            double tempb = a*b+a*b;
            a = tempa + x;
            b = tempb + y;
//            Старый вариант без сглаживания
//            if (Math.sqrt(a*a + b*b) > R) {
//                ii = i;
//                break;
//            }
//            Вот со сглаживаем штука вместо
            double abs2 = a * a + b * b;
            if (abs2 > R * R) {
                double fix = Math.log(Math.log(abs2) / Math.log(R) / 2) / Math.log(2);
                return (i - fix) / N;
            }
        }
//        Тоже перед сглаживанием
//        return (double)ii/N;
        return 1;
    }
}

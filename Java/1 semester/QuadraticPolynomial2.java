public class QuadraticPolynomial2 {
	public static void main(String[] args)  {
		//Введите коэффициенты квадратного уравнения
		int a = 0;
		int b = 0;
		int c = 0;
        if (a == 0 && b == 0 && c == 0)
            System.out.println("0");
        else
            System.out.println(quadr(a, b, c));
	}

    private static String quadr(int a, int b, int c) {
    	return sum(sum(coef(a, "x^2"), coef(b, "x")), coef(c, ""));
    }

    private static String coef(int koef, String x) {
        if (koef == 0) return "";
        else if (koef == 1 && x.equals("")) return x;
        else if (koef == -1 && x.equals("")) return "-" + x;
        else return Integer.toString(koef) + x;
    }
    
    private static String sum(String s1, String s2){
        if (s2.equals("")) return s1;
        else if (s1.equals("")) return s2;
        else if (s2.charAt(0) == '-') return s1 + s2;
        else return s1 + "+" + s2;
    }
}
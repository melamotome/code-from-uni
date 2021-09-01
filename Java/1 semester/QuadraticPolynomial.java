public class QuadraticPolynomial {
	public static void main(String[] args)  {
		//Введите коэффициенты квадратного уравнения
		int a = 2;
		int b = -1;
		int c = 0;
		System.out.println(quadr(a, b, c));
	}

	private static String quadr(int a, int b, int c) {
		String res = "";
		if (a == 0) {
			if (b == 0) {
				if (c != 0)
					res = res + c;
			} else {
				if (c == 0) {
					res = res + outing(b) + "x";
				} else
					res = res + outing(b) + "x" + outing(c);
			}
		} else if (b == 0) {
			if (c == 0)
				res = outing(a) + "x^2";
			else
			    res = outing(a) + "x^2" + outing(c);
		} else {
			if (c == 0)
				res = outing(a) + "x^2" + outing(b) + "x";
			else
				res = outing(a) + "x^2" + outing(b) + "x" + outing(c);
		}

		if (a > 0)
			return res.substring(1, res.length());
		else
			return res;
    }
    
    private static String outing(int koef) {
    	String result = "";
    	if (koef > 0 ) result = result + "+";
    	if (koef != 1 && koef != -1) result = result + koef;
    	if (koef == -1) result = result + "-";
    	return result;
    }
}
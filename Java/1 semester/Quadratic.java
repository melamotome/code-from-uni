public class Quadratic {
	public static void main(String[] args)  {
		int a = 23;
		int b = 1;
		int c = 4;

		if (a == 0) 
			if (b == 0)
				if (c == 0)  //0 0 0
					System.out.println("Решений бесконечно много");
				else  //0 0 c
					System.out.println("Решений нет");
			else { //0 b c
				System.out.print("Одно решение x = ");
				System.out.println((double)-c / b);
			}
		else {
			double d = b * b - 4 * a * c;
			if (d < 0)
				System.out.println("Решений нет");
			else if (d == 0) {
				System.out.print("Одно решение x = ");
				System.out.println((double)-b / (2 * a));
			} else {
				System.out.print("Два решения, x1 = ");
				System.out.print(((double)-b + Math.sqrt(d)) / (2 * a));
				System.out.print(", x2 = ");
				System.out.println(((double)-b - Math.sqrt(d)) / (2 * a));
		    }
		}
	}
}
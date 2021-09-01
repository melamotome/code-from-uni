public class Cycleception {
	public static void main(String[] args) {
		int m = 5;
		int n = 4;	
		table(m, n);
		System.out.println();
		triangle1(n);
		System.out.println();
		triangle2(n);
		System.out.println();
		triangle3(n);
		System.out.println();
		pseudoTable(m, n);
		System.out.println();
	}

	private static void table(int m, int n) {
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++)
				System.out.print("*");
			System.out.println();
		}
	}

	private static void triangle1(int n) {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= i; j++)
				System.out.print("*");
			System.out.println();
		}
	}

	private static void triangle2(int n) {
		for (int i = n; i >= 1; i--) {
			for (int j = 1; j <= i; j++)
				System.out.print("*");
			System.out.println();
		}
	}

	private static void triangle3(int n) {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n - i; j++)
				System.out.print(" ");
			for (int j = 1; j <= i; j++)
				System.out.print("*");
			System.out.println();
		}
	}
	
	private static void pseudoTable(int m, int n) {
		n = n + 1;
		//Верхняя крышка
		for (int j = 1; j <= n; j++) {
			if (j == 1) 
				System.out.print("\u250C\u2500");
			else if (j == n)
				System.out.println("\u2510");
			else 
				System.out.print("\u252C\u2500");
		}
		borders(n);

		//Середина
		for (int i = 2; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (j == 1) 
					System.out.print("\u251C\u2500");
				else if (j == n)
					System.out.println("\u2524");
				else 
					System.out.print("\u253C\u2500");
			}
			borders(n);
		}

		//Нижняя крышка
		for (int j = 1; j <= n; j++) {
			if (j == 1) 
				System.out.print("\u2514\u2500");
			else if (j == n)
				System.out.println("\u2518");
			else 
				System.out.print("\u2534\u2500");
		}
	}

	private static void borders(int n) {
		System.out.print("\u2502");
		for (int j = 2; j <= n; j++)
			System.out.print(" \u2502");
		System.out.println();
	}
}
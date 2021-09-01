public class Cycles {
	public static void main(String[] args) {
		int n = 5;
		String stri = "x,y,z,1    2 3abc";
		System.out.println("1.1: " + intToStr(n));
		System.out.println("1.2: " + intToStrReverse(n));
		System.out.println("2: " + deletNotLetters(stri));
	}

	private static String intToStr(int n) {
		String s = "1";
		for (int i = 2; i <= n; i++)
			s = s + ", " + i;
		return s;
	}

	private static String intToStrReverse(int n) {
		String s = Integer.toString(n);
		for (int i = n - 1; i >= 1; i--)
			s = s + ", " + i;
		return s;
	}

	private static String deletNotLetters(String s) {
		int i = 0;
		while (i < s.length()) {
			if (!Character.isLetter(s.charAt(i)))
				s = s.substring(0, i) + s.substring(i + 1, s.length());
			else
				i++;
		}
		return s;
	}
}
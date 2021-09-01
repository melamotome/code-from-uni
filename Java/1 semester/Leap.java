public class Leap {
	public static void main(String[] args)  {
		int year = 1984;

		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) 
			System.out.println("Високосный");
		else 
			System.out.println("Не високосный");
	}
}
public class NumbersToText {
	public static void main(String[] args)  {
		int number = 531;
		String text = "";
		
		int end1 = numbers % 10;
		int end2 = numbers % 100;
		
		System.out.print(numbers);
		System.out.print(" ");

		if (end2 >= 11 && end2 <= 19)
			System.out.println("котов");
		else if (end1 == 1)
			System.out.println("кот");
		else if (end1 >= 2 && end1 <= 4)
			System.out.println("кота");
		else
			System.out.println("котов");
	}
}
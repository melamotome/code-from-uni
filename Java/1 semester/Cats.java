public class Cats {
	public static void main(String[] args)  {
		int cats = 411;

		int end1 = cats % 10;
		int end2 = cats % 100;
		
		System.out.print(cats);
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
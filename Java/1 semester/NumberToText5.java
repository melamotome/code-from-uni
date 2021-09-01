/*Дано целое число от 1 до 999, вывести его в виде текста. 
Убедитесь, что вы не выводите двух пробелов подряд, и пробелов в конце текста. 
Получите результат в виде строки, 
последней операцией в программе напечатайте эту строку на экран.*/
import java.util.Arrays;
public class NumberToText5 {
	public static void main(String[] args) {
		//int number = 110;
		//System.out.println(Number999ToText(number));
		for (int i = 1; i < 1000; i++)
			System.out.println(Number999ToText(i));
	}

	public static String Number999ToText(int number) {
		String[] digits1 = {"", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"};
		String[] digits2 = {"", "", "двадцать ", "тридцать ", "сорок ", "пятьдесят ", "шестьдесят ", "семьдесят ", "восемьдесят ", "девяносто "};
		String[] digits3 = {"", "сто ", "двести ", "триста ", "четыреста ", "пятьсот ", "шестьсот ", "семьсот ", "восемьсот ", "девятьсот "};
		String[] elevens = {"десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"};
		int third = number / 100;
		int second = number / 10 % 10;
		int first = number % 10;
		String s = "";
		if (second == 1)
			s = digits3[third] + elevens[first];
		else
			s = digits3[third] + digits2[second] + digits1[first];
		return s;
	}
}
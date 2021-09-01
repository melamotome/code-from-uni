import java.io.*;
import java.util.regex.*;
import java.util.*;

public class RegexCats {
	public static void main(String[] args) {
		String textWithCats = "Кот который фабрикот КОТ кот";
		System.out.println(numberOfCats(textWithCats) + "\n");
	}
	public static int numberOfCats(String text) {
		int sum = 0;		
		//ДЗ 
		// \\A - это аналог ^, т.е. начало строки, 
		// а \\z - $ - конец строки. ?<=\\s значит, 
		// что перед словом "кот" есть пробел, но он не читается
		Pattern catPattern = Pattern.compile("(?<=\\s|\\A)кот(\\s|\\z)", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
		Matcher matcher = catPattern.matcher(text);
		while (matcher.find()) {
			sum++;
			System.out.println("Совпадение с котом:" + matcher.group());
		}
		return sum;
	}
}


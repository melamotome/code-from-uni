// 1
// Дана строка, проверьте, что в ней содержится корректный email адрес. 
// Будем считать, что корректный email состоит из имени пользователя 
// (несколько латинских букв, точек, подчеркиваний, минусов), 
// далее следует символ @, далее идет домен (тоже несколько латинских букв, точек, подчеркиваний, минусов), 
// в конце должна быть точка и от двух до четырех латинских букв.
//  Т.е. конец должен выглядеть как .com, .ru и т.п. 
// Помните, что обычная точка означает любой символ, и ее может понадобиться экранировать. 
// Используйте метод matches() класса String

// 2
// Дана строка с текстом на русском языке, 
// в которой автор неправильно расставил пробелы перед запятыми. 
// Например, Это строка , у которой зачем-то написаны два пробела перед запятой.
//  Нужно найти все пробельные символы перед запятыми и удалить их. 
//  Должно получиться Это строка, у которой зачем-то написаны два пробела перед запятой. 
//  Используйте метод replaceAll() класса String.

// 3
// Дана строка. 
// Найдите в ней все слова, написанные через дефис и поменяйте две половинки этих слов местами. 
// Например, строка “Какая-то сине-зеленовая трава” должна превратиться в 
// “то-Какая зеленовая-сине трава”. 
// Используйте метод replaceAll класса String и $ для ссылки на группы.

// 4
// Дана строка. Посчитайте, сколько раз в ней встречаются символы “кот”, 
// в произвольном регистре. Т.е. Кот и КОТ тоже надо считать. 
// Используйте Matcher с методом find(). 
// При создании регулярного выражения с помощью класса Pattern установите режимы 
// Pattern.UNICODE_CASE и Pattern.CASE_INSENSITIVE.

// 5
// Дан файл, допустим, text_with_numbers.txt. 
// Создайте файл text_with_numbers_plus_one.txt, который содержит ровно то же, 
// что содержит первый файл, только все числа (последовательности цифр \d+) увеличены на единицу. 
// Используйте методы appendReplacement и appendTail в классе Matcher.

import java.io.*;
import java.util.regex.*;
import java.util.*;

public class Regulars13 {
	public static void main(String[] args) {
		String emailText = "Bbfs._--sasd@mail_mail.MAIL-.su";
		System.out.println("Is email valid? " + emails(emailText));
		System.out.println("Is email valid? " + emailRegexTxt(emailText));

		String spacesText = "Это строка , у которой зачем-то написаны два пробела перед запятой.";
		System.out.println(deleteWrongSpaces(spacesText) + "\n");

		String hyphenText = "Какая-то сине-зеленовая трава";
		System.out.println(inverseHyphen(hyphenText) + "\n");

		String textWithCats = "Кот который фабрикот КОТ кот";
		System.out.println(numberOfCats(textWithCats) + "\n");

		numbersInFileUp("text_with_numbers.txt", "text_with_numbers_plus_one.txt");
	}

	public static boolean emails(String emailText) {
		return emailText.matches("([a-zA-Z-_\\.])+@([a-zA-Z-_\\.])+\\.([a-zA-Z]){2,4}");
	}

	public static boolean emailRegexTxt(String emailText) {
		String emailRegex = "";
		try (Scanner in = new Scanner(new File("emailregex.txt"), "UTF-8")) {
			if (in.hasNextLine())
				emailRegex = in.nextLine();
		} catch (Exception e) {
			System.out.println("Ошибка");
			e.printStackTrace();
		}
		return emailText.matches(emailRegex);
	}

	public static String deleteWrongSpaces(String text) {
		return text.replaceAll("\\s+,", ",");
	}

	public static String inverseHyphen(String text) {
		return text.replaceAll("(\\S+)-(\\S+)","$2-$1");
	}

	public static int numberOfCats(String text) {
		int sum = 0;
		//Pattern catPattern = Pattern.compile("кот", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
		//Два варианта не символов кот, а именно слова кот:
		//Pattern catPattern = Pattern.compile("\\bкот\\b", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
		
		//ДЗ
		Pattern catPattern = Pattern.compile("(\\s+|^)кот($| |[.?!]+)", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
		Matcher matcher = catPattern.matcher(text);
		while (matcher.find()) {
			sum++;
			System.out.println("Совпадение с котом:" + matcher.group() + "   " + matcher.start());
		}
		return sum;
	}

	public static void numbersInFileUp(String finName, String foutName) {
		File fin = new File(finName);
		File fout = new File(foutName);
		try (Scanner in = new Scanner(fin, "UTF-8"); PrintStream out = new PrintStream(fout)) {
			Pattern numberPattern = Pattern.compile("\\d+");
			
			while (in.hasNextLine()) {
				StringBuffer sb = new StringBuffer();
				Matcher matcher = numberPattern.matcher(in.nextLine());
				while (matcher.find()) {
					String numberString = matcher.group();
					int number = Integer.parseInt(numberString); //Перевод в целое число
					matcher.appendReplacement(sb, "" + (number + 1));
				}
				matcher.appendTail(sb);
				out.println(sb); 
			}
		} catch (Exception e) {
			System.out.println("Ошибка");
			e.printStackTrace();
		}
	}
}


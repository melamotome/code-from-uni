// 1. Дан файл. Прочитайте из него все целые числа и выведите на экран их сумму.
// Измените метод так, чтобы он игнорировал все слова, которые не являются целыми числами. 
// (используйте hasNextInt(), не используйте scanner.useDelimiter()).


// 2. Дан массив строк lines и имя файла. Запишите в файл заданные строки построчно. 
// Не забудьте, что есть цикл “for each”.


// 3. Даны два файла. Прочитайте из первого файла текст и перепишите его во второй файл, 
// исправив ошибки в регистрах букв Например, если исходный текст был:

//  какой-то Текст с неправильными   Регистрами букв!  втОРОе предложение Этого текста.
// то должно получиться:

//  Какой-то текст с неправильными регистрами букв! Второе предложение этого текста.

// Вы должны читать исходный файл по словам, разделенными пробельными символами, 
// следить за последним символом очередного слова. Если это символ конца предложения 
// (точка, восклицательный знак, вопросительный знак), нужно сделать так, 
// чтобы следующее слово начиналось с заглавной буквы. 
// Иначе следующее слово должно начинаться со строчной буквы. 
// Заглавные буквы внутри слов нужно изменить на строчные.

import java.io.*;
import java.util.*;

public class Files12 {
	public static void main(String[] args) {
		sumOfIntegers(new File("Text_with_numbers.txt"));

		String[] lines = {"Aaaaaaaaaaaaaaa", "Bbbbbbbbbbbbbbbbbbb", "Ccccccccccccccccccccccc"};
		linesToFile(lines, new File ("linesToFile.txt"));

		fixRegister(new File("Wrong_register.txt"), new File("Right_register.txt"));
	}

	//Первое задание
	public static void sumOfIntegers(File file) {
		int sum = 0;
		try (Scanner in = new Scanner(file, "UTF-8")) {
			while (in.hasNext())
				if (in.hasNextInt()) {
					int x = in.nextInt();
					sum += x;
				} else if (in.hasNext())
					in.next();
		} catch (Exception e) {
			System.out.println("Ошибка");
			e.printStackTrace();
		}
		System.out.printf("Сумма целых чисел файла равна %d%n", sum);
	}

	//Второе задание
	public static void linesToFile(String[] lines, File file) {
		try (PrintStream out = new PrintStream(file)) { 
			for (String line : lines)
				out.println(line); 
		} catch (Exception e) {
			System.out.println("Ошибка записи");
		}
	}

	//Третье задание
	public static void fixRegister(File fin, File fout) {
		boolean sentenceStart = true;
		boolean fileStart = true;
		try (Scanner in = new Scanner(fin, "UTF-8"); PrintStream out = new PrintStream(fout)) {
			while (in.hasNext()) {
				String word = in.next();

				char firstLetter = word.charAt(0); 
				if (sentenceStart)
					word = Character.toUpperCase(firstLetter) + word.substring(1);
				else
					word = Character.toLowerCase(firstLetter) + word.substring(1);

				char lastLetter = word.charAt(word.length() - 1);
				sentenceStart = lastLetter == '!' || lastLetter =='?' || lastLetter =='.';
				word = word.charAt(0) + word.substring(1).toLowerCase();
				if (fileStart) {
					out.printf("%s", word); 
					fileStart = false;
				} else 
					out.printf(" %s", word);
			}
		} catch (Exception e) {
			System.out.println("Ошибка");
			e.printStackTrace();
		}
	}
}


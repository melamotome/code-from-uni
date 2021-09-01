/*Часть 1
Дан текстовый файл sounds.txt, в котором написаны звуки животных, например:

мяу игого игого бее беее мее мяяяяяяяяяяяуууууууууууу мяуу бее

Звуки разделяются пробелами или переводами строк.

По каждому звуку можно определить животное, которое его издает. 
По условию, Коты говорят «мяу», овцы — «бе», ослы — «игого». 
Необходимо определить по каждому звуку животное и вывести список животных в файл animals.txt.

Не нужно выводить одно животное два раза подряд. 
Например, если вы видете «игого» два или три раза подряд, то написать «Осёл» нужно ровно один раз.

Если звук невозможно определить, то животное зовут «???».

В каждом звуке животное может произносить любую гласную несколько раз (как минимум один).

Поэтому в указанном примере вы должны вывести ответ
Кот Осел Овца ??? Кот Овца*/

import java.io.*;
import java.util.*;
import java.util.regex.*;
public class Animals {
	public static void main(String[] args) {
		String prevAnimal = "";
		try (Scanner in = new Scanner(new File("sounds.txt"), "UTF-8")) {
			try (PrintStream out = new PrintStream(new File("animals.txt"), "UTF-8")) { 
				while (in.hasNext()) {
					// Первая часть
					// String sound = in.next().toLowerCase();
					// String animal = detectAnimal(reduceVowels(sound));
					// Вторая часть
					String sound = in.next();
					String animal = detectAnimal2(sound);
					if (!animal.equals(prevAnimal))
						if (prevAnimal.equals(""))
							out.printf("%s", animal);
						else 
							out.printf(" %s", animal);
					prevAnimal = animal;	
				}
			} catch (Exception er) {
				System.out.println(er);
			}
		} catch (Exception e) {
			System.out.println("Ошибка");
			e.printStackTrace();
		}
	}

	public static String reduceVowels(String sound) {
		Pattern p = Pattern.compile("(а)+|(о)+|(и)+|(е)+|(ё)+|(э)+|(ы)+|(у)+|(ю)+|(я)+");
		Matcher m = p.matcher(sound);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "$1$2$3$4$5$6$7$8$9$10");
		}
		m.appendTail(sb);
		sound = sb.toString();
		return sound;
	}

	//По первой части было
	public static String detectAnimal(String sound) {
		if (sound.equals("мяу"))
			return "Кот";
		else if (sound.equals("бе"))
			return "Овца";
		else if (sound.equals("игого"))
			return "Осёл";
		else return "???";
	}

	//Вторая часть
	public static String detectAnimal2(String sound) {
		if (sound.matches(makePattern("мяу")))
			return "Кот";
		else if (sound.matches(makePattern("игого")))
			return "Осёл";
		else if (sound.matches(makePattern("бе")))
			return "Овца";
		else return "???";
	}

	// При решении первой части вы наверняка пользовались регулярными выражениями. 
	// Напишите функцию, которая обычный звук для животного («мяу», «бе», ...) 
	// превращает в регулярное выражение для проверки на звук. 
	// Это регулярное выражение должно разрешать повторять гласные несколько раз, 
	// и должно разрешать буквы в обоих регистрах.
	public static String makePattern(String sound) {
		char[] letters = new char[sound.length()];
		sound = sound.toLowerCase();
		for (int i = 0; i < sound.length(); i++)
			letters[i] = sound.charAt(i);
		char[] vowels = {'а', 'о', 'и', 'е', 'ё', 'э', 'ы', 'у', 'ю', 'я'};
		StringBuilder animal = new StringBuilder("");

		for (char letter : letters) {
			boolean isVowel = false;
			for (char vowel : vowels) 
				if (letter == vowel) {
					isVowel = true;
					break;
				}
			animal.append("(" + letter + "|" + Character.toString(letter).toUpperCase() + ")"); //[яЯ]
			if (isVowel) 
				animal.append("+");
		}	
		return animal.toString();
	}
}


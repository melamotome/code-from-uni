public class Strings4Func {
	public static void main(String[] args) {
		String str = "ДлЯ кАжДоГо";
		System.out.println("Строка состоит только из строчных букв: " + isLowerCase(str));
		System.out.println("Строка состоит только из заглавных букв: " + isUpperCase(str));
		System.out.println("Строка начинается с заглавной буквы, а все остальные строчные: " + isSentence(str));
		System.out.println("В строке чередуются буквы: " + isJumping(str));
	}

	private static boolean isLowerCase(String text) {
		return text.equals(text.toLowerCase());
	}

	private static boolean isUpperCase(String text) {
		return text.equals(text.toUpperCase());
	}

	private static boolean isSentence(String text) {
		String s1 = text.substring(0, 1);
		String s2 = text.substring(1, text.length());
		return s1.equals(s1.toUpperCase()) && s2.equals(s2.toLowerCase());
	}

	private static boolean isJumping (String text) {
		//Избавление от мешающих знаков	
		String textChanged = "";

		/*
		for (int i = 0; i < text.length(); i++) {
			char symbol = text.charAt(i);
			if (!Character.isLetter(symbol))
				textChanged = text.substring(0,i) + text.substring(i + 1, text.length());
		}
		*/

		for (int i = 1; i < textChanged.length(); i++) {
			char chNow = textChanged.charAt(i);
			char chPre = textChanged.charAt(i - 1);
			if (Character.isUpperCase(chNow) == Character.isLowerCase(chPre))) {
				return false;
		}
		return true;
	}
}
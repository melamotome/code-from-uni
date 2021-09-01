import java.util.Arrays;
public class TwoArrays10 {
	public static void main(String[] args) {
		char[][] c = createTable(20, '.');
	    printTable(c);

	    System.out.println("============ Заполним строки: ==========");
	    fillFirstAndLastLines(c, '#');
	    printTable(c);

	    System.out.println("============ Заполним столбцы: =========");
	    fillFirstAndLastColumns(c, '#');
	    printTable(c);
	}

	public static char[][] createTable(int n, char symbol) {
		char[][] arr = new char[n][n];
		for (char[] line : arr)
			Arrays.fill(line, symbol);
		return arr;
	}

	public static void printTable(char[][] arr) {
		for (char[] line : arr) {
			for (char symbol : line)
				System.out.print(symbol + " ");
			System.out.println();
		}
	}

	public static void fillFirstAndLastLines(char[][] arr, char symbol) {
		Arrays.fill(arr[0], symbol);
		Arrays.fill(arr[arr.length - 1], symbol);	
	}

	public static void fillFirstAndLastColumns(char[][] arr, char symbol) {
		for (char[] line : arr) {
			line[0] = symbol;
			line[line.length - 1] = symbol;
		}
	}
}
import java.util.Arrays;
public class Arrayss {

	public static void main(String[] args) {
		int n = 5;	
		System.out.println(Arrays.toString(even(n)));	

		int[] arr = new int[]{0, 2, 5, 2, 4, 5, 6, 3, 9, 8, 7};
		System.out.println(hasSimilar(arr));
		//System.out.println(hasSimilar(new int[]{0, 2, 2, 4, 5, 6, 3, 9, 8, 7}));

		double[] arr2 = {0, 2, 2, 4, 5, 6, 3};
		System.out.println(average(arr2));

		System.out.println(dispersion(arr2));

		//5.
		int[] x = {20, 10, 30};
		int[] y = sortReverse(x);
		System.out.println(Arrays.toString(x)); // печатает 20 10 30
		System.out.println(Arrays.toString(y)); // печатает 30 20 10

		//System.out.println(Arrays.toString(sortReverse(arr)));
	}

	private static int[] even(int n) {
		int[] arr = new int[n];
		for (int i = 1, number = 2; i <= n; i++, number += 2)
			arr[i - 1] = number;
		return arr;
	}

	private static boolean hasSimilar(int[] arr) {
		for (int i = 0; i < arr.length; i++)
			for (int j = i + 1; j < arr.length; j++)
				if (/*i != j && */arr[i] == arr[j])
					return true;
		return false;
	}

	private static double average(double[] arr) {
		double average = 0;
		for (int i = 0; i < arr.length; i++)
			average = average + arr[i];
		return average / arr.length;
	}

	private static double dispersion(double[] arr) {
		double average1 = 0;
		//double average2 = 0;

		for (int i = 0; i < arr.length; i++) {
			average1 = average1 + arr[i] * arr[i];
			//average2 = average2 + arr[i];
		}

		double a2 = average(arr); //average2 / arr.length;
		return average1 / arr.length - a2 * a2;
	}

	private static int[] sortReverse(int[] arr) {
		int[] arr2 = Arrays.copyOf(arr, arr.length);
		Arrays.sort(arr2);
		int[] arr3 = new int[arr.length];
		for (int i = 0; i < arr3.length; i++)
			arr3[arr3.length - 1 - i] = arr2[i];
		return arr3;
	}
}
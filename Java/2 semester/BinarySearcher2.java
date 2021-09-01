@SuppressWarnings("WeakerAccess")
public class BinarySearcher2 {
    public static void main(String[] args) {
        int[] a = {1, 4, 5, 10, 20, 100};
        test(a, 5, 2);
        test(a, 1, 0);
        test(a, 100, 5);
        test(a, 42, -1);
        test(a, 120, -1);
        test(a, 0, -1);

        int[] b = {1, 4, 6, 65, 100};
        test(b, 0, -1);
        test(b, 1, 0);
        test(b, 2, -1);
        test(b, 4, 1);
        test(b, 5, -1);
        test(b, 6, 2);
        test(b, 10, -1);
        test(b, 65, 3);
        test(b, 70, -1);
        test(b, 100, 4);
        test(b, 200, -1);

        int[] c = {1, 4, 6, 65, 100, 110};
        test(c, 0, -1);
        test(c, 1, 0);
        test(c, 2, -1);
        test(c, 4, 1);
        test(c, 5, -1);
        test(c, 6, 2);
        test(c, 10, -1);
        test(c, 65, 3);
        test(c, 70, -1);
        test(c, 100, 4);
        test(c, 105, -1);
        test(c, 110, 5);
        test(c, 200, -1);

        test(new int[]{}, 42, -1);
        test(new int[]{42}, 0, -1);
        test(new int[]{42}, 42, 0);
        test(new int[]{42}, 100, -1);

        test(new int[]{10, 20}, 0, -1);
        test(new int[]{10, 20}, 10, 0);
        test(new int[]{10, 20}, 15, -1);
        test(new int[]{10, 20}, 20, 1);
        test(new int[]{10, 20}, 25, -1);
    }

    private static void test(int[] a, int value, int correctAnswer) {
//        System.out.println("Looking for " + value + " in " + Arrays.toString(a));
        int actualAnswer = binarySearch(a, value);
//        System.out.println("actualAnswer = " + actualAnswer);
//        System.out.println("correctAnswer = " + correctAnswer);
        if (actualAnswer == correctAnswer)
            System.out.println("Ok");
        else
            System.out.println("Error! Got " + actualAnswer + " instead of " + correctAnswer);
    }

    public static int binarySearch(int[] a, int value) {
        //ищем значения от индекса l до индекса r
        int l = 0;
        int r = a.length - 1;
        int direction = 1; //1 - right, 0 - left

        //Пустой массив?
        if (a.length == 0)
            return -1;

        //на случай массива одного элемента
        if (r == 0)
            if (a[0] == value)
                return 0;
            else
                return -1;

        if (value < a[l] || a[r] < value)
            return -1;


        while (l < r) {
            int m;
            //на случаи крайних значение
            if (r - l == 1) {
                if (direction == 0) //direction determines whether we are on the start or the end here
                    m = l;
                else
                    m = r;
            } else
                m = (l + r) / 2; //середина диапазона

            //общее вычисление
            if (a[m] == value)
                return m;
            else if (a[m] > value) {
                direction = 0;
                r = m;
            } else {
                direction = 1;
                l = m;
            }

            //Если промежуточное значение
            if (direction == 1)
                if (a[m] < value && value < a[m + 1])
                    return -1;
            else
                if (a[m - 1] < value && value < a[m])
                    return -1;
        }
        return -1;
    }
}

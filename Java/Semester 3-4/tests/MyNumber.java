package tests;

import java.util.*;

public class MyNumber {
    public static List<Integer> splitToNumbers(int number) {
        List<Integer> list = new ArrayList<>();

        while (number != 0) {
            int n = number % 10;
            number = number / 10;
            list.add(n);
        }
        Collections.reverse(list);
        return list;
    }

    public static int sum(int number) {
        int sum = 0;
        List<Integer> list = splitToNumbers(number);
        for(int num : list)
            sum = sum + num;
        return sum;
    }
}

package rabbitmq.simpleoneway;

import java.util.Arrays;

/**
 * Description:
 *
 * @author zhangr
 * 2020/7/16 16:28
 */
public class Combination {

    private static String[] temp;
    private static int count = 0;

    public static void main(String[] args) {
        String[][] array = new String[][]{
                {"A", "B", "C"},
                {"E", "D"},
                {"1", "2"},
        };
        temp = new String[array.length];
        combination(array, 0, 0);
    }

    private static void combination(String[][] array, int i, int j) {
        for (; i < array.length; i++) {
            for (; j < array[i].length; j++) {
                temp[i] = array[i][j];
                if (j != array[i].length-1) {
                    combination(array, i + 1, 0);
                } else {
                    System.out.println(++count);
                    System.out.println(Arrays.toString(temp));
                }
            }
        }
    }
}
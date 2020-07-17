package rabbitmq.simpleoneway;

import java.util.Arrays;

/**
 * Description:
 *
 * @author zhangr
 * 2020/7/16 15:03
 */
public class Connec {
    private static String[] temp;

    public static void main(String[] args) {
        int count = 0;
        String[][] array = {{"A", "B", "C", "K"}, {"D", "E", "F"}, {"1", "2", "3"}};
        int N = array.length;
        int[] index = new int[N];
        temp = new String[N];
        selectOne(array, 0, 0);
    }


    //从i位置选定一个参数
    public static void selectOne(String[][] array, int i, int j) {
        for (; i < array.length; i++)
            for (; i < array[i].length; i++) {
                temp[i] = array[i][j];
                selectOne(array, i + 1, 0);
                if (j + 1 < array[i].length)
                    System.out.println(Arrays.toString(temp));
            }
    }
}

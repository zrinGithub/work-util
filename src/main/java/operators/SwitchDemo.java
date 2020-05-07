package operators;

/**
 * Description:
 *
 * @author zhangr
 * 2020/4/30 9:45
 * }
 */
public class SwitchDemo {
    public static void main(String[] args) {
        swap2(1,2);
    }

    public static void swap(int a, int b){
        System.out.printf("a=%d, b=%d",a,b);
        a = a + b;
        b = a - b ;
        a = a - b;
        System.out.printf("\na=%d, b=%d",a,b);
    }

    public static void swap2(int a, int b){
        System.out.printf("a=%d, b=%d",a,b);
        a = a^b;
        b = b^a;   // newB = a^b^b
        a = a^b;   // newA = a^b^b^a
        System.out.printf("\na=%d, b=%d",a,b);
    }
}

// 实训第一天作业 第二大题

public class Homework2 {
    /**
     * 创建一个类 为该类定义三个构造函数 分别执行下列操作
     */
    int q1;     /* 传递两个整数值并找出其中较大的一个值 */
    double q2;      /* 传递三个double值并求出其乘积 */
    boolean q3;     /* 传递两个字符串值并检查其是否相同 */

    Homework2(int n, int m) {
        this.q1 = (n > m ? n : m);
    }

    Homework2(double x, double y, double z) {
        this.q2 = x * y * z;
    }

    Homework2(String a, String b) {
        this.q3 = a.equals(b);
    }

    public static void main(String[] args) {
        Homework2 h = new Homework2(3,5);
        System.out.println(h.q1);
        h = new Homework2(3,5,3.0);
        System.out.println(h.q2);
        h = new Homework2("Hello World!", "Hello World!");
        System.out.println(h.q3);
    }


}

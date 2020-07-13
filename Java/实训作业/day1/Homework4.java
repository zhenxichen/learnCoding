// 实训第一天作业 第四大题

import java.util.Scanner;

public class Homework4 {
    /**
     * 从键盘输入10个成绩，求平均成绩，并找出高于平均成绩的成绩个数。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] arr = new double[10];
        int count = 0;
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            arr[i] = scanner.nextDouble();
            sum += arr[i];
        }
        double avg = sum/10;
        for (int i = 0; i < 10; i++) {
            if (arr[i] > avg) {
                count++;
            }
        }
        System.out.println("平均成绩:" + avg);
        System.out.println("高于平均成绩的成绩个数:" + count);
    }
}

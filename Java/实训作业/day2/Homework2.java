// 实训第二天作业 第二大题

import java.util.Scanner;

public class Homework2 {
    /**
     * 编写程序，从键盘输入字符串，输出其中重复的字符、不重复的字符以及消除重复以后的字符列表
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        StringBuilder stringBuilder = new StringBuilder();
        int[] count = new int[26];
        int pos = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            count[c - 'a']++;
            if(count[c-'a'] == 1) {
                stringBuilder.append(c);
            }
        }
        System.out.print("重复的字符:");
        for (int i = 0; i < 26; i++) {
            if (count[i] > 1) {
                System.out.print((char) (i + 'a') + " ");
            }
        }
        System.out.println("");
        System.out.print("不重复的字符:");
        for (int i = 0; i < 26; i++) {
            if (count[i] == 1) {
                System.out.print((char) (i + 'a') + " ");
            }
        }
        System.out.println("");
        System.out.println(stringBuilder.toString());
    }
}


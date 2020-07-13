// 大二下Java实训作业 第一天第一大题

import java.util.ArrayList;
import java.util.List;

public class Homework1 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.question1();
        solution.question2();
    }
}

class Student {
    int number; // 学号
    int state; // 年级
    int score; // 成绩

    public Student(int number) { // 构造函数
        this.number = number;
        this.state = (int) (Math.random() * 4);
        this.score = (int) (Math.random() * 100);
    }
}

class Solution {
    List<Student> students = new ArrayList<Student>();

    public Solution() {     //构造函数
        for (int i = 0; i < 20; i++) {  // 生成20个学生信息
            Student newStudent = new Student(i + 1);
            students.add(newStudent);
        }
    }

    public void question1() {
        /**
         * 问题一：打印出3年级(state值为3）的学生信息。
         */
        System.out.println("----question 1----");
        System.out.println("学号\t年级\t成绩");
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if(student.state == 3) {
                System.out.println(student.number + "\t\t" + student.state +
                        "\t\t" + student.score);
            }
        }
    }

    public void question2() {
        /**
         * 问题二：使用冒泡排序按学生成绩排序，并遍历所有学生信息
         */
        Student[] arr = students.toArray(new Student[students.size()]);
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j].score < arr[j + 1].score) {
                    Student temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("----question 2----");
        System.out.println("学号\t年级\t成绩");
        for (int i = 0; i < arr.length; i++) {
            Student student = arr[i];
            System.out.println(student.number + "\t\t" + student.state +
                    "\t\t" + student.score);
        }
    }


}
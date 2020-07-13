// 实训第一天作业 第三大题

public class Homework3 {
    /**
     * 创建学生类，有姓名学号等属性，重写equals方法，只要姓名及学号相等就就视为同一对象，重写toString方法，
     * 格式：学生xxx学号为xxx，创建测试类进行测试。
     */
    public static void main(String[] args){
        Student s1 = new Student("123",123);
        Student s2 = new Student("123", 123);
        Student s3 = new Student("111", 111);
        System.out.println(s1.equals(s2));      // This should be true
        System.out.println(s1.equals(s3));      // This should be false
    }
}

class Student {
    String name;
    int number;

    public Student(String name, int number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public boolean equals(Object object) {
        if(Student.class != object.getClass()){
            return false;
        }
        Student s2 = (Student)object;
        if(this.name == s2.name && this.number == s2.number){
            return true;
        }
        return false;
    }
}

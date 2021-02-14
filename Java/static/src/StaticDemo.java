import static java.lang.Math.*;     // 静态导包，可以导入所有静态对象和方法

public class StaticDemo {
    /**
     * 关键字 static 的几种用法
     */
    static {        // 静态代码块
        // 静态代码块在非静态代码块之前执行
        i = 3;          // 静态代码块对于定义在它之后的静态变量，可以赋值
        // System.out.println(i);      // 但不可以访问
        System.out.println("静态代码块");
    }
    private static int i;           // 修饰变量，表示这个变量为这个类所有，而非为单个对象所有

    {       // 非静态代码块（构造代码块）
        System.out.println("构造代码块");
    }

    public StaticDemo() {
        System.out.println("构造函数");
    }

    public static void staticFunc() {
        System.out.println("静态方法");
    }

    public void func() {
        System.out.println("非静态方法");
    }

    public static void main(String[] args) {
        int j = max(1, 2);      // 可以直接是用Math类的静态方法
        /**
         * 输出结果如下：
         * 静态代码块
         * ---- 静态方法调用 ----
         * 静态方法
         * ---- 建立对象并调用方法 ----
         * 构造代码块
         * 构造函数
         * 非静态方法
         * ---- 第二次建立对象并调用方法 ----
         * 构造代码块
         * 构造函数
         * 非静态方法
         */
        // 说明静态代码块在JVM加载类时运行
        // 构造代码块则在构造对象时运行，并且在构造函数之前运行
        System.out.println("---- 静态方法调用 ----");
        StaticDemo.staticFunc();
        System.out.println("---- 建立对象并调用方法 ----");
        StaticDemo demo = new StaticDemo();
        demo.func();
        System.out.println("---- 第二次建立对象并调用方法 ----");
        StaticDemo demo2 = new StaticDemo();
        demo.func();
    }
}

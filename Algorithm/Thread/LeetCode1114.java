import java.util.concurrent.atomic.AtomicInteger;

public class LeetCode1114 {
    /* LeetCode 1114 按序打印 */
    /**
     * 我们提供了一个类：
     * public class Foo {
     *   public void first() { print("first"); }
     *   public void second() { print("second"); }
     *   public void third() { print("third"); }
     * }
     * 三个不同的线程 A、B、C 将会共用一个 Foo 实例。
     * 一个将会调用 first() 方法
     * 一个将会调用 second() 方法
     * 还有一个将会调用 third() 方法
     * 请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行。
     */
    public static void main(String[] args) throws InterruptedException {
        FooInteger s1 = new FooInteger();
        s1.first(new PrintFirst());
        s1.second(new PrintSecond());
        s1.third(new PrintThird());
    }
}

// 解法一：自旋等待AtomicInteger
class FooInteger {

    // 标记第一个线程打印完成
    private AtomicInteger firstDone = new AtomicInteger(0);
    // 标记第二个线程打印完成
    private AtomicInteger secondDone = new AtomicInteger(0);

    public FooInteger() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        firstDone.incrementAndGet();        // 将标记第一个线程打印完毕的AtomicInteger自增为1
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (firstDone.get() != 1) {
            // 自旋等待第一个线程打印完成
        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        secondDone.incrementAndGet();       // 标记第二个线程打印完成
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (secondDone.get() != 1) {
            // 自旋等待第二个线程打印完成
        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}

class PrintFirst implements Runnable {
    @Override
    public void run() {
        System.out.println("First");
    }
}

class PrintSecond implements Runnable {
    @Override
    public void run() {
        System.out.println("Second");
    }
}

class PrintThird implements Runnable {
    @Override
    public void run() {
        System.out.println("Third");
    }
}
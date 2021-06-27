import java.util.concurrent.Semaphore;

public class LeetCode1115 {
    /* LeetCode 1115 交替打印FooBar */
    /**
     * 我们提供一个类：
     * class FooBar {
     *   public void foo() {
     *     for (int i = 0; i < n; i++) {
     *       print("foo");
     *     }
     *   }
     *
     *   public void bar() {
     *     for (int i = 0; i < n; i++) {
     *       print("bar");
     *     }
     *   }
     * }
     * 两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。
     * 请设计修改程序，以确保 "foobar" 被输出 n 次。
     */
    public static void main(String[] args) {
        // testSemaphore();
        testWaitNotify();
    }

    // 测试解法一：信号量
    private static void testSemaphore() {
        FooBarSemaphore fbs = new FooBarSemaphore(3);
        new Thread(() -> {
            try {
                fbs.foo(new PrintFoo());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fbs.bar(new PrintBar());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    // 测试解法二：wait/notify
    private static void testWaitNotify() {
        FooBarWaitNotify fb = new FooBarWaitNotify(3);
        new Thread(() -> {
            try {
                fb.foo(new PrintFoo());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fb.bar(new PrintBar());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

// 解法一：信号量
class FooBarSemaphore {
    private int n;

    private Semaphore foo;
    private Semaphore bar;


    public FooBarSemaphore(int n) {
        this.n = n;
        foo = new Semaphore(1);
        bar = new Semaphore(0);
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            foo.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            bar.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            bar.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            foo.release();
        }
    }
}

// 解法二：wait/notify
class FooBarWaitNotify {
    private int n;

    private int count;

    private Object lock;

    public FooBarWaitNotify(int n) {
        this.n = n;
        count = 0;
        lock = new Object();
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (lock) {
                if (count % 2 == 1) {
                    lock.wait();
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                count++;
                lock.notify();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (lock) {
                if (count % 2 == 0) {
                    lock.wait();
                }
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                count++;
                lock.notify();
            }
        }
    }
}

class PrintFoo implements Runnable {
    @Override
    public void run() {
        System.out.print("foo");
    }
}

class PrintBar implements Runnable {
    @Override
    public void run() {
        System.out.print("bar");
    }
}
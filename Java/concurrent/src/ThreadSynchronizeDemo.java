public class ThreadSynchronizeDemo {
    private static Thread t1, t2;

    public static void main(String[] args) {
        printOne2Ten();
    }

    // 两个线程交替打印1-10
    private static void printOne2Ten() {
        Object lock = new Object();

        t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    synchronized (lock) {
                        System.out.println(i);
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    synchronized (lock) {
                        System.out.println(i);
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }
}

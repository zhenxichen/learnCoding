import java.util.Date;
import java.util.concurrent.*;

public class ThreadPoolDemo {

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " start. time = " + new Date());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " end. time = " + new Date());
        }
    }

    private static final int CORE_POOL_SIZE = 5;        // 核心线程数
    private static final int MAX_POOL_SIZE = 10;        // 最大线程数
    private static final int QUEUE_CAPACITY = 100;      // 任务队列的容量
    private static final long KEEP_ALIVE_TIME = 1L;     // 销毁非核心线程的等待时间

    public static void main(String[] args) {
        // 用 ThreadPoolExecutor 的构造函数建立线程池（推荐）
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,         // 定义核心线程数
                MAX_POOL_SIZE,          // 定义最大线程数
                KEEP_ALIVE_TIME,        // 定义keepAliveTime
                TimeUnit.SECONDS,       // 设定时间单位为秒
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),       // 定义任务队列
                /**
                 * 饱和策略：处理当前同时运行的线程数量达到最大线程数量并且队列也已经被放满了任务的情况
                 * 常见的饱和策略有以下几种：
                 * ThreadPoolExecutor.AbortPolicy: 抛出 RejectedExecutionException来拒绝新任务的处理
                 * ThreadPoolExecutor.CallerRunsPolicy: 直接在调用Executor的线程中执行任务
                 * ThreadPoolExecutor.DiscardPolicy: 不处理新任务，直接丢弃掉
                 * ThreadPoolExecutor.DiscardOldestPolicy: 丢弃最早的未处理的任务请求
                 */
                new ThreadPoolExecutor.CallerRunsPolicy()       // 定义饱和策略
        );
        // 调用线程
        for (int i = 0; i < MAX_POOL_SIZE; i++) {
            Runnable command = new MyRunnable();
            executor.execute(command);
        }
        // 终止线程池
        executor.shutdown();
        while(!executor.isTerminated()) { }
        System.out.println("All Threads Finished.");
    }

    /**
     * 本方法演示其他的建立线程池的方法（不推荐）
     */
    private static void otherWayToGenerateThreadPool() {
        /**
         * FixedThreadPool:
         * 查阅源码可以发现，问题在于，任务队列LinkedBlockedQueue的长度为Integer.MAX_VALUE
         * 因此可能会导致堆积任务过多，从而导致 OOM
         */
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(CORE_POOL_SIZE);
        /**
         * SingleThreadExecutor:
         * 只有一个线程，同时任务队列LinkedBlockedQueue的长度同样为Integer.MAX_VALUE
         * 因此也可能会导致堆积任务过多，从而导致 OOM
         */
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        /**
         * CachedThreadPool:
         * 核心线程数为 0，最大线程数为 Integer.MAX_VALUE
         * 可能导致线程堆积过多，从而导致 OOM
         */
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        /**
         * ScheduledThreadPool:
         * 最大线程数为 Integer.MAX_VALUE
         * 可能导致线程堆积过多，从而导致 OOM
         */
        ExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(CORE_POOL_SIZE);
    }
}
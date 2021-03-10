package singleton;

/**
 * 通过双重校验锁来实现懒汉式单例模式
 */
public class DoubleCheckSingleton {
    /**
     * 这里在定义对象的时候使用volatile，主要目的是为了防止指令重排。
     * JVM在定义对象时的四个关键步骤为：类加载检查、分配内存、初始化对象和将内存地址赋值给引用
     * 但是，第三步和第四步有可能发生指令重排。
     * 如果发生指令重排，在单例模式下就可能使得一个线程错误地使用了未被初始化的对象。
     */
    private volatile static DoubleCheckSingleton INSTANCE;  // volatile的主要目的是防止指令重排

    private DoubleCheckSingleton() { }      // 用private隐藏构造方法，防止构造出单例对象

    public static DoubleCheckSingleton getInstance() {
        if (INSTANCE == null) {     // 第一个if主要目的是控制加锁的条件
            synchronized (DoubleCheckSingleton.class) {
                if (INSTANCE == null) {     // 这里再次进行校验是为了防止多个线程同时进入if块，导致生成多个对象
                    INSTANCE = new DoubleCheckSingleton();
                }
            }
        }
        return INSTANCE;
    }
}

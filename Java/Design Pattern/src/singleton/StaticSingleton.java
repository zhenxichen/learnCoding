package singleton;

/**
 * 通过静态内部类实现单例模式
 */
public class StaticSingleton {
    /**
     * 只有当显式调用getInstance()方法之后，这里的静态内部类SingletonHolder才会被加载
     * 从而将INSTANCE初始化
     * 因此，通过静态内部类实现的单例模式是懒汉式的
     */
    private static class SingletonHolder {  // 通过静态内部类持有实例
        private static final StaticSingleton INSTANCE = new StaticSingleton();
    }

    private StaticSingleton() { }   // 用private隐藏构造方法

    public static StaticSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}

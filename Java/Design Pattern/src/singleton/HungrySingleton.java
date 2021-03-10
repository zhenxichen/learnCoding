package singleton;

/**
 * 饿汉式单例模式
 */
public class HungrySingleton {
    private static HungrySingleton instance = new HungrySingleton();    // 直接实例化

    private HungrySingleton() { }       // 用private隐藏构造方法

    public static HungrySingleton getInstance() {
        return instance;
    }
}

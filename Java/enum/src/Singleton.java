/**
 * 通过enum类可以实现单例模式
 */

public enum Singleton {
    INSTANCE;

    public static Singleton getInstance() {
        return INSTANCE;
    }

    public void someMethods() {

    }
}

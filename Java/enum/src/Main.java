public class Main {
    public static void main(String[] args) {
        Gender gender = Gender.FEMALE;
        if (gender == Gender.FEMALE) {
            System.out.println("True");     // Enum 可用==进行比较
        }
        Singleton s = Singleton.getInstance();      // 可以通过Enum实现单例模式
        s.someMethods();
    }
}

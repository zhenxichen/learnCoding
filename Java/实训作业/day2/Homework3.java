import java.util.HashSet;
import java.util.Random;

public class Homework3 {
    /**
     * 3.产生10个1~20之间的随机数，要求随机数不能重复
     */

    public static void main(String[] args) {
        Random random = new Random();
        HashSet<Integer> hashSet = new HashSet<>();
        for(;;) {
            hashSet.add(random.nextInt(20)+1);
            if(hashSet.size() >= 10) {
                break;
            }
        }
        System.out.println(hashSet);
    }
}

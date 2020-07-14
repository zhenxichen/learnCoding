import java.util.HashSet;
import java.util.Random;

public class Homework5 {
    /**
     * 5.双色球规则：双色球每注投注号码由6个红色球号码和1个蓝色球号码组成。红色球号码从1—33中选择；
     * 蓝色球号码从1—16中选择；请随机生成一注双色球号码。（要求同色号码不重复）
     */
    public static void main(String[] args) {
        Random random = new Random();
        HashSet<Integer> red = new HashSet<>();
        for(;;) {
            red.add(random.nextInt(33) + 1);
            if(red.size() > 5) {
                break;
            }
        }
        System.out.println("红色球: " + red);
        int blue = random.nextInt(16) + 1;
        System.out.println("蓝色球: " + blue);
    }
}

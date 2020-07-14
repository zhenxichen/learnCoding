import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Homework4 {
    /**
     * 4.完成模拟斗地主洗牌发牌程序
     */
    public static void main(String[] args) {
        String[] values = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};
        String[] suits = {"♠", "♥", "♣", "♦"};
        HashMap<Integer, String> hashMap = new HashMap<>();
        List<Integer> cards = new ArrayList<>();
        List<Integer> player1 = new ArrayList<>();
        List<Integer> player2 = new ArrayList<>();
        List<Integer> player3 = new ArrayList<>();
        List<Integer> other = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < suits.length; j++) {
                int num = i * 4 + j;
                String str = suits[j] + values[i];
                hashMap.put(num, str);
                cards.add(num);
            }
        }
        hashMap.put(52, "小王");
        cards.add(52);
        hashMap.put(53, "大王");
        cards.add(53);
        Collections.shuffle(cards);
        for (int i = 0; i < 54; i++) {
            if (i >= 51) {
                other.add(cards.get(i));
            } else if (i % 3 == 0) {
                player1.add(cards.get(i));
            } else if (i % 3 == 1) {
                player2.add(cards.get(i));
            } else {
                player3.add(cards.get(i));
            }
        }
        System.out.print("玩家1: ");
        for (int i = 0; i < player1.size(); i++) {
            System.out.print(hashMap.get(player1.get(i)) + " ");
        }
        System.out.println();
        System.out.print("玩家2: ");
        for (int i = 0; i < player2.size(); i++) {
            System.out.print(hashMap.get(player2.get(i)) + " ");
        }
        System.out.println();
        System.out.print("玩家3: ");
        for (int i = 0; i < player3.size(); i++) {
            System.out.print(hashMap.get(player3.get(i)) + " ");
        }
        System.out.println();
        System.out.print("地主牌: ");
        for (int i = 0; i < other.size(); i++) {
            System.out.print(hashMap.get(other.get(i)) + " ");
        }
        System.out.println();
    }
}

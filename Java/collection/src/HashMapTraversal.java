import java.util.*;

public class HashMapTraversal {
    /**
     * 涉及Java HashMap的七种遍历方法
     */
    private static Map<Integer, String> map;

    public static void init() {
        map = new HashMap<>();
        map.put(1, "java");
        map.put(2, "Collection");
        map.put(3, "Map");
        map.put(4, "HashMap");
        map.put(5, "traverse");
    }

    // 1. 通过Iterator遍历EntrySet
    public static void traverseByIteratorEntrySet() {
        System.out.println("Iterator EntrySet遍历：");
        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();  // 使用EntrySet的Iterator
        while(iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            System.out.print(entry.getKey());
            System.out.print(entry.getValue());
        }
        System.out.println();
    }

    // 2. 通过Iterator遍历KeySet
    public static void traverseByIteratorKeySet() {
        System.out.println("Iterator KeySet遍历：");
        Iterator<Integer> iterator = map.keySet().iterator();
        while(iterator.hasNext()) {
            Integer key = iterator.next();
            System.out.print(key);
            System.out.print(map.get(key));
        }
        System.out.println();
    }

    // 3. 通过For Each遍历EntrySet
    public static void traverseByForEachEntrySet() {
        System.out.println("ForEach EntrySet遍历：");
        for (Map.Entry<Integer, String> entry: map.entrySet()) {
            System.out.print(entry.getKey());
            System.out.print(entry.getValue());
        }
        System.out.println();
    }

    // 4. 通过For Each遍历KeySet
    public static void traverseByForEachKeySet() {
        System.out.println("ForEach KeySet遍历：");
        for (Integer key: map.keySet()) {
            System.out.print(key);
            System.out.print(map.get(key));
        }
        System.out.println();
    }

    // 5. 通过foreach方法，lambda表达式遍历
    public static void traverseByLambda() {
        System.out.println("ForEach方法 Lambda表达式遍历：");
        map.forEach((key, value) -> {       // 这里的参数是Consumer<Integer, String>
            System.out.print(key);          // 因此lambda表达式的参数为key value
            System.out.print(value);
        });
        System.out.println();
    }

    // 6. 通过Stream API单线程遍历
    public static void traverseByStreamAPI() {
        System.out.println("通过Stream API单线程遍历");
        map.entrySet().stream().forEach((entry) -> {    // 这里的参数是Consumer<Entry>，因此参数只有一个entry
            System.out.print(entry.getKey());
            System.out.print(entry.getValue());
        });
        System.out.println();
    }

    // 7. 通过Stream API多线程遍历
    public static void traverseByStreamAPIinParallel() {
        System.out.println("通过Stream API多线程遍历");
        map.entrySet().parallelStream().forEach((entry) -> {
            System.out.print(entry.getKey());
            System.out.print(entry.getValue());
        });
        System.out.println();
    }

    public static void main(String[] args) {
        init();
        traverseByIteratorEntrySet();
        traverseByIteratorKeySet();
        traverseByForEachEntrySet();
        traverseByForEachKeySet();
        traverseByLambda();
        traverseByStreamAPI();
        traverseByStreamAPIinParallel();
    }
}

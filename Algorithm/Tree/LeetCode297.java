import java.util.*;

public class LeetCode297 {
    /* LeetCode 297 二叉树的序列化与反序列化 */
    /**
     * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，
     * 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
     * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
     * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
     * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。
     * 你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
     * 示例1：
     * 输入：root = [1,2,3,null,null,4,5]
     * 输出：[1,2,3,null,null,4,5]
     * 提示：
     * · 树中结点数在范围 [0, 104] 内
     * · -1000 <= Node.val <= 1000
     */
    public static void main(String[] args) {
        Codec ser = new Codec();
        Codec deser = new Codec();
        TreeNode root = new TreeNode(1);
        TreeNode ans = deser.deserialize(ser.serialize(root));
    }
}

class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        String ser = "";
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                ser += "null,";
            } else {
                ser += node.val + ",";
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        return ser;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] dataArray = data.split(",");
        int n = dataArray.length;
        if (n == 0 || dataArray[0].equals("null")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(dataArray[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                continue;
            }
            if (dataArray[i].equals("null")) {
                node.left = null;
            } else {
                node.left = new TreeNode(Integer.parseInt(dataArray[i]));
            }
            if (dataArray[i + 1].equals("null")) {
                node.right = null;
            } else {
                node.right = new TreeNode(Integer.parseInt(dataArray[i + 1]));
            }
            queue.add(node.left);
            queue.add(node.right);
            i += 2;
        }
        return root;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
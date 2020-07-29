import java.util.Stack;

public class Leetcode155 {
    /* Leetcode 155 最小栈 */
    /**
     * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
     * push(x) —— 将元素 x 推入栈中。
     * pop() —— 删除栈顶的元素。
     * top() —— 获取栈顶元素。
     * getMin() —— 检索栈中的最小元素。
     */
    public static void main(String[] args) {
        MinStack obj = new MinStack();
    }
}

class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> min_stack;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        min_stack = new Stack<>();
        min_stack.push(Integer.MAX_VALUE);
    }

    public void push(int x) {
        stack.push(x);
        if(x < min_stack.peek()) {
            min_stack.push(x);
        } else {
            min_stack.push(min_stack.peek());
        }
    }

    public void pop() {
        int top = stack.peek();
        stack.pop();
        min_stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min_stack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

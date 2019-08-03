package chapter3;

import java.util.Iterator;

/**
 * 栈的数组实现
 * 
 * @author majq
 * @since 2019/07/29 21:31
 * @version 1.0.0
 */
public class MyStack<AnyType> implements Iterable<AnyType> {

    /**
     * 底层数组
     */
    private AnyType[] items;
    /**
     * 栈内数组元素个数
     */
    private int size = 0;
    /**
     * 默认容量为10
     */
    private int capacity = 10;

    /**
     * 构造器
     */
    public MyStack() {
        ensureCapacity(capacity);
    }

    /**
     * 构造器
     * 
     * @param capacity 初始容量
     */
    public MyStack(int capacity) {
        ensureCapacity(this.capacity = capacity > this.capacity ? capacity : this.capacity);
    }

    /**
     * 底层数组初始化
     * 
     * @param capacity 容量
     */
    private void ensureCapacity(int capacity) {
        items = (AnyType[]) new Object[capacity];
    }

    /**
     * 入栈
     * 
     * @param item 压入栈的元素
     */
    public void push(AnyType item) {
        if (size == capacity)
            throw new ArrayIndexOutOfBoundsException();
        items[size++] = item;
    }

    /**
     * 出栈
     * 
     * @return 弹出栈的元素
     */
    public AnyType pop() {
        if (size == 0)
            return null;
        AnyType item = items[--size];
        items[size] = null; // 删除该元素
        return item;
    }

    @Override
    public Iterator<AnyType> iterator() {
        return null;
    }

    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.print(stack.pop());
        System.out.print(stack.pop());
        System.out.print(stack.pop());
        System.out.print(stack.pop());
        System.out.print(stack.pop());
        System.out.print(stack.pop());
    }
}
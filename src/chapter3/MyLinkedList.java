package chapter3;

import java.util.Iterator;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Any;

/**
 * 双向链表实现
 * 
 * @author majq
 * @version 1.0.0
 * @since 2019/07/16 21:23
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType> {

    /**
     * 链表节点元素对象，使用内部类实现
     * @param <AnyType>
     */
    private static class Node<AnyType>{
        /**
         * 节点内存储的数据
         */
        public AnyType data;
        /**
         * 当前节点前驱节点
         */
        public Node<AnyType> pre;
        /**
         * 当前节点后继节点
         */
        public Node<AnyType> next;
        /**
         * 构造器，根据数据、前驱节点和后继节点构造当前节点
         * 每个节点除了存储数据外，还存储了其前驱节点和后继节点，藉此实现‘可双向遍历’链表
         * @param data 数据
         * @param pre 前驱节点
         * @param next 后继节点
         */
        public Node(AnyType data, Node<AnyType> pre, Node<AnyType> next) {
            this.data = data;
            this.pre = pre;
            this.next = next;
        }
    }
    /**
     * 链表当前存储元素个数
     */
    private int theSize;
    /**
     * 自链表创建后，链表元素修改计数
     */
    private int modCount;
    /**
     * 链表首个节点——链表首部标记节点
     */
    private Node<AnyType> beginMarker;
    /**
     * 链表最后一个节点——链表尾部标记节点
     */
    private Node<AnyType> endMarker;

    
    @Override
    public Iterator<AnyType> iterator() {
        return null;
    }
    /**
     * 构造器，初始化链表
     */
    public MyLinkedList() {
        doClear();
    }
    /**
     * 重置链表
     */
    public void clear(){
        doClear();
    }
    /**
     * 获取链表当前包含节点数目
     * @return int 链表内元素个数
     */
    public int size(){
        return theSize;
    }
    /**
     * 检测链表当前是否包含元素
     * @return true:链表当前为空 false:链表当前非空
     */
    public boolean isEmpty(){
        return theSize == 0;
    }
    /**
     * 向链表末尾添加元素(节点)
     * @param x 新增元素
     * @return 是否添加成功
     */
    public boolean add(AnyType x){
        add(theSize,x);
        return true;
    }
    /**
     * 向链表指定位置插入新元素
     * @param idx 插入元素位置
     * @param x 添加的新元素
     */
    public void add(int idx,AnyType x){
        addBefore(getNode(idx,0,theSize),x);
    }
    /**
     * 获取指定节点存储的数据
     * @param idx 节点位置
     * @return 该节点存储的数据
     */
    public AnyType get(int idx){
        return getNode(idx).data;
    }
    /**
     * 修改指定节点存储的数据
     * @param idx 节点位置
     * @param newVal 新值
     * @return 该节点存储的旧数据
     */
    public AnyType set(int idx,AnyType newVal){
        Node<AnyType> p = getNode(idx);
        AnyType oldVal = p.data;
        p.data = newVal;
        return oldVal;
    }
    /**
     * 删除指定节点
     * @param idx 节点位置
     * @return 被删除节点存储的数据
     */
    public AnyType remove(int idx){
        return remove(getNode(idx));
    }
    /**
     * 删除指定节点
     * @param node 被删除节点
     * @return 被删除节点存储的数据
     */
    private AnyType remove(Node<AnyType> node){
        return null;
    }
    /**
     * Adds an item to this collection,at specified position p.
     * Items at or after that position are slid one position higher.
     * 
     * @param p Node to add add before
     * @param x any Object
     */
    private void addBefore(Node<AnyType> p,AnyType x){
        Node<AnyType> newNode = new Node<AnyType>(x,p.pre,p);//构造新节点
        newNode.pre.next = newNode; //修改新节点的前驱节点的后继节点为新节点
        p.pre = newNode;//修改p节点的前驱节点为新节点
        theSize++; //元素计数自增
        modCount++; //链表修改计数自增
    }
    /**
     * Gets the Node at position idx,which must range from 0 to size()-1.
     * @param idx index to search at.
     * @return internal node corresponding to idx.
     * @throw IndexOutOfBoundsException if idx is not between 0 and size() - 1 ,inclusive.
     */
    private Node<AnyType> getNode(int idx){
        return getNode(idx,0,size()-1);
    }
    /**
     * Gets the Node at position idx,which must range from lower to upper.
     * @param idx index to search at.
     * @param lower lowest valid index.
     * @param upper highest valid index.
     * @return internal node corresponding to idx.
     * @throw IndexOutOfBoundsException if idx is not between lower and upper,inclusive.
     */
    private Node<AnyType> getNode(int idx,int lower,int upper){
        Node<AnyType> p;
        if(idx < lower || idx > upper)
            throw new IndexOutOfBoundsException();
        if(idx < size()/2){
            p = beginMarker.next;
            for(int i = 0;i<idx;i++)
                p = p.next;
        }
        else{
            p = endMarker;
            for(int i = size();i>idx;i--)
                p = p.pre;
        }
        return p;
    }

    /**
     * 构造链表-构造一个只包含首部标识节点和尾部标识节点的链表
     */
    private void doClear(){
        beginMarker = new Node<AnyType>(null,null,null);  //链表第一个节点
        endMarker = new Node<AnyType>(null,beginMarker,null); //链表最后一个节点
        beginMarker.next = endMarker;
    }

}
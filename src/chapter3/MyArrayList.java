package chapter3;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Majq
 * @version 1.0.0
 * @since 2019/07/15 21:17
 * 表实现 ArrayList
 */
public class MyArrayList<AnyType> implements Iterable<AnyType> {
	/**
	 * 底层数组默认大小
	 */
	private static final int DEFAULT_CAPACITY = 10;
	/**
	 * 列表当前大小——当前列表内元素个数
	 */
	private int theSize;
	/**
	 * 底层数组
	 */
	private AnyType[] theItems;

	/**
	 * 构造器
	 */
	public MyArrayList() {

	}

	/**
	 * 重置数组——初始化
	 */
	private void doClear() {
		theSize = 0;
		ensureCapacity(DEFAULT_CAPACITY);
	}

	/**
	 * 获取列表当前所含元素个数
	 *
	 * @return
	 */
	public int size() {
		return theSize;
	}

	/**
	 * 检查列表当前是否无元素
	 *
	 * @return
	 */
	public boolean isEmpty() {
		return theSize == 0;
	}

	/**
	 * 将列表中空位删除
	 */
	public void trimToSize() {
		ensureCapacity(size());
	}

	/**
	 * 获取指定位置元素
	 *
	 * @param idx
	 * @return
	 */
	public AnyType get(int idx) {
		if (idx < 0 || idx >= size())
			throw new ArrayIndexOutOfBoundsException();
		return theItems[idx];
	}

	/**
	 * 修改指定位置元素
	 *
	 * @param idx
	 * @param newVal
	 * @return
	 */
	public AnyType set(int idx, AnyType newVal) {
		if (idx < 0 || idx > size())
			throw new ArrayIndexOutOfBoundsException();
		AnyType old = theItems[idx];
		theItems[idx] = newVal;
		return old;
	}

	public boolean add(AnyType x) {
		add(size(), x);
		return true;
	}

	public void add(int idx, AnyType x) {
		if (theItems.length == size())
			ensureCapacity(size() * 2 + 1);
		for (int i = theSize; i > idx; i--)
			theItems[i] = theItems[i - 1];
		theItems[idx] = x;

		theSize++;
	}

	public AnyType remove(int idx) {
		if (idx < 0 || idx >= size())
			throw new ArrayIndexOutOfBoundsException();
		AnyType removedItem = theItems[idx];
		for (int i = idx; i < theSize - 1; i++)
			theItems[i - 1] = theItems[i];

		theSize--;
		return removedItem;
	}

	/**
	 * 按照指定初始大小初始化底层数组
	 *
	 * @param newCapacity
	 */
	public void ensureCapacity(int newCapacity) {
		if (newCapacity < theSize)
			return;
		AnyType[] old = theItems;
		theItems = (AnyType[]) new Object[newCapacity];
		for (int i = 0; i < size(); i++)
			theItems[i] = old[i];
	}

	@Override
	public Iterator<AnyType> iterator() {
		return new ArrayListIterator();
	}

	/**
	 * 采用内部类实现迭代器
	 */
	private class ArrayListIterator implements java.util.Iterator<AnyType> {

		private int current = 0;

		@Override
		public boolean hasNext() {
			return current < size();
		}

		@Override
		public AnyType next() {
			if (!hasNext())
				throw new NoSuchElementException();
			return theItems[current++];
		}

		@Override
		public void remove() {
			MyArrayList.this.remove(--current);
		}
	}
}

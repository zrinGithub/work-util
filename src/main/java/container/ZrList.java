package container;

import java.util.Arrays;

/**
 * Description:
 *
 * @author zhangr
 * 2020/5/7 14:23
 * }
 */
public class ZrList<T> {
    //transient标识不能被序列化
    transient Object[] elementData;

    //默认扩容大小
    private static final int DEFAULT_CAPACITY = 10;

    //最大的容量
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    //用于初始化的空List
    private static final Object[] EMPTY_LIST = {};

    //list实际存储的大小，从0开始
    private int size;

    public ZrList() {
        this.elementData = EMPTY_LIST;
    }

    public ZrList(int capacity) {
        if (capacity > 0) {
            this.elementData = new Object[capacity];
        } else if (capacity == 0) {
            this.elementData = EMPTY_LIST;
        } else {
            throw new IllegalArgumentException("IllegalArgument capacity:" + capacity);
        }
    }

    public boolean add(T element) {
        //计算最小容量
        int minLength = size + 1;
        if (elementData == EMPTY_LIST) {
            minLength = Math.max(DEFAULT_CAPACITY, minLength);
        }

        //需要扩容的情况，调用grow进行扩容
        if (minLength - elementData.length > 0)
            grow(minLength);

        //尾部插入
        elementData[size++] = element;
        return true;
    }

    //扩容
    private void grow(int minLength) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minLength < 0)
            newCapacity = minLength;
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    //获取
    @SuppressWarnings("unchecked")
    public T get(int index) {
        rangeCheck(index);
        return (T) elementData[index];
    }

    //获取位置
    public int indexOf(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null)
                    return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elementData[i]))
                    return i;
            }
        }
        return -1;
    }

    //更新
    @SuppressWarnings("unchecked")
    public T set(int index, T newVal) {
        rangeCheck(index);
        T oldVal = (T) elementData[index];
        elementData[index] = newVal;
        return oldVal;
    }

    //删除操作
    public T remove(int index) {
        rangeCheck(index);
        T oldVal = (T) elementData[index];
        //计算删除位置后面的数据
        int numMoved = size - (index + 1);

        //如果在后面还有数据
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        //把多出的位置赋值为空，让垃圾回收器工作
        elementData[--size] = null;

        return oldVal;
    }

    private void rangeCheck(int index) {
        //边界检测
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException("Index " + index + " size " + size);
    }
}

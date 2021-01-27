package z.Queue;

import z.Array.Array;

public class MaxHeap<E extends Comparable<E>> {

    /**
     * start from 0
     * parent i = (i-1) / 2
     * left child i = i*2 + 1
     * right child i = i*2 + 2
     * */

    private Array<E> data;

    MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    MaxHeap() {
        data = new Array<>();
    }

    public void add (E e) {
        data.addLast(e);
        siftUP(data.getSize() - 1);
    }

    // sift up
    private void siftUP(int index) {
        while (index > 0 && data.get(parent(index)).compareTo(data.get(index)) < 0) {
            data.swap(parent(index), index);
            index = parent(index);
        }
    }

    public E findMax() {
        if (data.getSize() == 0)
            throw new IllegalArgumentException("empty heap");
        return data.get(0);
    }

    public E extractMax() {
        E ret = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    // sift down
    private void siftDown(int index) {
        while (leftChild(index) < data.getSize()) { // index has a child at least
            // in heap, parent should greater than both left & right child
            // find MAX(left, right) & change parent<->MAX(left, right)
            int j = leftChild(index);
            if (j + 1 < data.getSize() && data.get(j).compareTo(data.get(j + 1)) < 0) // has a right && right > left
                j++; // j -> rightChild

            if (data.get(j).compareTo(data.get(index)) < 0) // both left & right less than parent, break;
                break;

            data.swap(index, j);
            index = j;
        }
    }

    public int getSize() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    private int parent(int index) {
        if (index == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        return (index - 1) >> 1;
    }

    private int leftChild(int index) {
        return (index << 1) + 1;
    }

    private int rightChild(int index) {
        return (index << 1) + 2;
    }
}

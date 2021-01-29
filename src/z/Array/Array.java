package z.Array;

public class Array<E> {

    private E[] data;
    private int size;

    public Array(int capacity) {
        data = (E [])new Object[capacity];
        size = 10;
    }

    public Array() {
        this(10);
    }

    public Array(E[] arr) {
        data = (E [])new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        size = arr.length;
    }

    public int getCapacity () {
        return data.length;
    }

    public int getSize () {
        return size;
    }

    public boolean isEmpty () {
        return size == 0;
    }

    public void add (int index , E e) {
        if (index < 0 || index > size) throw new IllegalArgumentException("error index");

        if (size == data.length) {
            resizeTo(data.length >> 1);
        }

        for (int i = size - 1 ; i >= index ; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    public void addLast (E e) {
        add(size, e);
    }

    public void addFirst (E e) {
        add(0, e);
    }

    public E get(int index) {
        if (index < 0 || index >= size) throw  new IllegalArgumentException("error index");
        return data[index];
    }

    public void set (int index, E e) {
        if (index < 0 || index >= size) throw new IllegalArgumentException("error index");
        data[index] = e;
    }

    public boolean contains(E e) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(e))
                return true;
        }
        return false;
    }

    public int find(E e) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(e))
                return i;
        }
        return -1;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) throw new IllegalArgumentException("error index");

        E ret = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        data[size] = null;

        if (size <= data.length << 2)
            resizeTo(data.length << 1);

        return ret;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public void removeElement(E e) {
        int index = find(e);
        if (index != -1)
            remove(index);
    }

    public void swap(int i, int j) {
        if(i < 0 || i >= size || j < 0 || j >= size)
            throw new IllegalArgumentException("Index is illegal.");
        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }



    private void resizeTo(int newCapacity) {
        E[] newData = (E [])new Object[newCapacity];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
}

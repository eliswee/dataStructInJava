package z.Set;

import z.LinkedList.LinkedList;

public class LinkedListSet <E extends Comparable<E>> implements SET<E> {

    LinkedList <E> list;

    LinkedListSet () {
        list = new LinkedList<>();
    }

    @Override
    public void add(E e) {
        if (!list.contains(e)) // only
            list.addFirst(e);
    }

    @Override
    public void remove(E e) {
        list.removeElement(e);
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public int getSize()  {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

}

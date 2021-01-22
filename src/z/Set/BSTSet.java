package z.Set;

import z.BST.BST;

public class BSTSet <E extends Comparable<E>> implements SET<E> {

    private BST<E> bst;

    BSTSet () {
        bst = new BST<E>();
    }

    @Override
    public void add(E e) {
        bst.addr(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public int getSize()  {
        return bst.getSize();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }
}

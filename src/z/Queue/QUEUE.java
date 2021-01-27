package z.Queue;

public interface QUEUE <E>{
    void enqueue(E e);
    E dequeue();
    E getFront();
    int getSize();
    boolean isEmpty();
}

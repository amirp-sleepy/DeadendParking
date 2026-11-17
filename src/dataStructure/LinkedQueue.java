package dataStructure;

public class LinkedQueue<T> implements IQueue<T> {
    private SLinkedList<T> data;

    public LinkedQueue() {
        data = new SLinkedList<T>();
    }

    @Override
    public void enqueue(T value) {
        data.insertLast(value);
    }

    @Override
    public T dequeue() {
        return data.removeFirst();
    }

    @Override
    public T front() {
        return data.front();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public int size() {
        return data.size();
    }

}

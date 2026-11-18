package dataStructure;

public interface IDequeue<T> {
    public void insertFirst(T value);

    public void insertLast(T value);

    public T removeFirst();

    public T removeLast();

    public boolean isFull();

    public int size();
}

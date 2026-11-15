package dataStructure;

public interface ADT<T> {

    public void insert(int index, T value);

    public T remove(int index);

    public T set(int index, T value);

    public T get(int index);

    public void clear();

    public boolean isEmpty();

    public int size();

    public void insertFirst(T value);

    public void insertLast(T value);

    public T removeFirst();

    public T removeLast();

    public T front();

    public T back();

    public ADT<T> clone();
}

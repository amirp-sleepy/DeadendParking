package dataStructure;

public interface IStack<T> {

    public T peek();

    public void push(T value);

    public T pop();

    public boolean isEmpty();

    public boolean isFull();

    public int size();
}

import java.util.EmptyStackException;

public class ArrayStack<T> implements IStack<T> {
    private T[] data;
    private int tail;
    private int size;

    public ArrayStack(int capacity) {
        data = (T[]) new Object[capacity];
        tail = -1;
        size = 0;
    }

    // if Stack is not Empty it return last value
    // throws "EmptyStackException" for empty Stack
    @Override
    public T peek() {
        if (size != 0)
            return data[tail];
        else
            throw new EmptyStackException();
    }

    // if Stack is not full it insert an item in last
    // throws "IllegalStateException" for full Stack
    @Override
    public void push(T value) {
        if (size != data.length) {
            data[tail + 1] = value;
            size++;
            tail += 1;
        } else
            throw new IllegalStateException();
    }

    // if Stack is not Empty remove the last item from the Stack
    // throws "EmptyStackException" for empty Stack
    @Override
    public T pop() {
        if (size != 0) {
            T cellData = data[tail];
            data[tail] = null;
            tail -= 1;
            size--;

            return cellData;
        } else
            throw new EmptyStackException();

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == data.length;
    }

    @Override
    public int size() {
        return size;
    }

    // The method is not an inherent part of the data structure,
    // and was introduced purely as a helper for the program's internal logic.
    // it return the item of an index when index is valid and stack is not empty
    // throws "IndexOutOfBoundsException" for invalid indexs
    // throws "EmptyStackException" for Empty Stack
    public T get(int index) {
        if (size != 0) {
            if (index < size && index >= 0)
                return data[index];
            else
                throw new IndexOutOfBoundsException();
        } else
            throw new EmptyStackException();
    }
}

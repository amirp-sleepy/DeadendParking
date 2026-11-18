package dataStructure;

public class CircularArray<T> implements ADT<T>, IDequeue<T> {

    private T[] data;
    private int head;
    private int size;

    @SuppressWarnings("unchecked")
    CircularArray(int capacity) {

        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity most be positeve.");
        }
        data = (T[]) new Object[capacity];
        head = 0;
        size = 0;
    }

    private int physicalIndex(int index) {
        return (head + index) % data.length;
    }

    private void checkInsertIndex(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index is out of bounds.");
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index is out of bounds.");
    }

    private void checkFull() {
        if (size == data.length)
            throw new IllegalStateException("Container is full.");
    }

    private void empty() {
        if (size == 0)
            throw new IllegalStateException("Container is empty.");
    }

    @Override
    public void insert(int index, T value) {

        checkInsertIndex(index);
        checkFull();

        if (index == 0) {
            insertFirst(value);
            return;
        }

        if (index == size) {
            insertLast(value);
            return;
        }

        if (index <= size - index) {
            int newHead = (head - 1 + data.length) % data.length;

            for (int i = 0; i < index; i++) {
                data[(newHead + i) % data.length] = data[(head + i) % data.length];
            }

            head = newHead;
            data[physicalIndex(index)] = value;
        } else {
            for (int i = size; i > index; i--) {
                data[physicalIndex(i)] = data[physicalIndex(i - 1)];
            }

            data[physicalIndex(index)] = value;

        }

        size++;

    }

    @Override
    public T remove(int index) {
        empty();
        checkElementIndex(index);

        if (index == 0) {
            return removeFirst();
        }
        if (index == size - 1) {
            return removeLast();
        }

        T cellData = data[physicalIndex(index)];

        if (index <= size - index) {
            for (int i = index; i > 0; i--) {
                data[physicalIndex(i)] = data[physicalIndex(i - 1)];
            }
            data[head] = null;
            head = (head + 1) % data.length;

        } else {
            for (int i = index; i < size; i++) {
                data[physicalIndex(i)] = data[physicalIndex(i + 1)];
            }
            data[size - 1] = null;
        }
        size--;
        return cellData;

    }

    @Override
    public T get(int index) {
        empty();
        checkElementIndex(index);

        return data[physicalIndex(index)];

    }

    @Override
    public T set(int index, T value) {
        empty();
        checkElementIndex(index);

        index = physicalIndex(index);
        T cellData = data[index];
        data[index] = value;

        return cellData;
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

    @Override
    public T front() {
        return get(0);
    }

    @Override
    public T back() {
        return get(size - 1);
    }

    @Override
    public void clear() {
        size = 0;
        head = 0;
    }

    @Override
    public T removeFirst() {
        empty();

        T cellData = data[head];
        data[head] = null;
        head = (head + 1) % data.length;
        size--;

        if (size == 0) {
            head = 0;
        }
        return cellData;
    }

    @Override
    public T removeLast() {
        empty();

        T cellData = data[physicalIndex(size - 1)];
        data[size - 1] = null;
        size--;

        if (size == 0) {
            head = 0;
        }

        return cellData;
    }

    @Override
    public void insertFirst(T value) {
        checkFull();
        head = ((head - 1) + data.length) % data.length;
        data[head] = value;
        size++;
    }

    @Override
    public void insertLast(T value) {
        checkFull();
        data[physicalIndex(size)] = value;
        size++;
    }

    @Override
    public ADT<T> clone() {
        CircularArray<T> copyArray = new CircularArray<>(data.length);

        for (int i = 0; i < size; i++) {
            copyArray.insertLast(data[physicalIndex(i)]);
        }

        return copyArray;
    }

}

package dataStructure;

public class SLinkedList<T> implements ADT<T> {

    private class Node {
        Node next;
        T data;

        public Node(Node next, T data) {
            this.next = next;
            this.data = data;
        }
    }

    private Node m_head;
    private Node m_tail;
    private int m_size;

    public SLinkedList() {
        m_head = null;
        m_tail = null;
        m_size = 0;
    }

    private void checkIndexInsert(int index) {
        if (index < 0 || index > m_size)
            throw new IndexOutOfBoundsException("Index is invalid.");
    }

    private void checkIndexElements(int index) {
        if (index < 0 || index >= m_size)
            throw new IndexOutOfBoundsException("Index is invalid.");
    }

    private void checkEmpty() {
        if (m_size == 0)
            throw new IllegalStateException("The list is empty.");
    }

    private Node findPrev(int index) {
        Node result = m_head;
        for (int i = 1; i < index; i++) {
            result = m_head.next;
        }
        return result;
    }

    @Override
    public void insert(int index, T value) {
        checkIndexInsert(index);

        Node previous = findPrev(index);
        Node next = previous.next;
        Node current = new Node(next, value);

        previous.next = current;
        m_size++;
    }

    @Override
    public T remove(int index) {
        checkIndexElements(index);
        checkEmpty();

        Node previous = findPrev(index);
        Node current = previous.next;
        Node next = current.next;

        previous.next = next;
        m_size--;

        return current.data;
    }

    @Override
    public T get(int index) {
        checkIndexElements(index);
        checkEmpty();

        Node previous = findPrev(index);
        return previous.next.data;
    }

    @Override
    public T set(int index, T value) {
        checkIndexElements(index);

        Node previous = findPrev(index);
        T cellData = previous.next.data;
        previous.next.data = value;

        return cellData;
    }

    @Override
    public boolean isEmpty() {
        return m_size == 0;
    }

    @Override
    public void clear() {
        m_head = null;
        m_tail = null;
        m_size = 0;
    }

    @Override
    public int size() {
        return m_size;
    }

    @Override
    public void insertFirst(T value) {
        Node newNode = new Node(m_head, value);
        m_head = newNode;

        if (m_size == 0)
            m_tail = newNode;

        m_size++;
    }

    @Override
    public void insertLast(T value) {
        Node newNode = new Node(null, value);

        if (m_size != 0)
            m_tail.next = newNode;

        m_tail = newNode;

        if (m_size == 0)
            m_head = newNode;

        m_size++;
    }

    @Override
    public T removeFirst() {
        checkEmpty();
        T cellData = m_head.data;
        m_head = m_head.next;
        m_size--;

        if (m_size == 0)
            m_tail = null;

        return cellData;
    }

    @Override
    public T removeLast() {
        checkEmpty();

        Node previous = findPrev(m_size - 1);

        T cellData = previous.next.data;
        previous.next = null;
        m_size--;

        if (m_size == 0)
            
    }
}

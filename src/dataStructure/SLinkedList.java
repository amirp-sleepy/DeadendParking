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

    private Node findNode(int index) {
        Node result = m_head;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result;
    }

    @Override
    public void insert(int index, T value) {
        checkIndexInsert(index);

        if (index == 0) {
            insertFirst(value);
            return;
        }
        if (index == m_size) {
            insertLast(value);
            return;
        }

        Node previous = findNode(index - 1);
        Node next = previous.next;
        Node current = new Node(next, value);

        previous.next = current;
        m_size++;
    }

    @Override
    public T remove(int index) {
        checkEmpty();
        checkIndexElements(index);

        if (index == 0) {
            return removeFirst();
        }
        if (index == m_size - 1) {
            return removeLast();
        }

        Node previous = findNode(index - 1);
        Node current = previous.next;
        Node next = current.next;

        previous.next = next;
        m_size--;

        return current.data;
    }

    @Override
    public T get(int index) {
        checkIndexElements(index);

        return findNode(index).data;
    }

    @Override
    public T set(int index, T value) {
        checkIndexElements(index);

        Node node = findNode(index);
        T cellData = node.data;
        node.data = value;

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

        T cellData;
        if (m_size != 1) {
            Node previous = findNode(m_size - 2);
            cellData = previous.next.data;
            previous.next = null;
            m_tail = previous;
        } else {
            cellData = m_head.data;
            m_head = null;
            m_tail = null;
        }
        m_size--;
        return cellData;
    }

    @Override
    public T front() {
        checkEmpty();
        return m_head.data;
    }

    @Override
    public T back() {
        checkEmpty();
        return m_tail.data;
    }

    @Override
    public ADT<T> clone() {
        SLinkedList<T> copyList = new SLinkedList<>();
        Node node = m_head;
        while (node != null) {
            copyList.insertLast(node.data);
            node = node.next;
        }

        return copyList;
    }
}

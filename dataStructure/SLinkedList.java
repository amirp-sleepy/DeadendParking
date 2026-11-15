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
    }

    @Override
    public T remove(int index) {

    }

}

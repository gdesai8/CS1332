/**
 * Your implementation of a linked queue.
 *
 * @author Pranshav Thakkar
 * @version 1.0
 */
public class LinkedQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("The queue is empty, yo"
                    + "u can't remove anything.");
        }
        if (size == 1) {
            LinkedNode<T> toRemove = head;
            head = null;
            tail = null;
            size = size - 1;
            return toRemove.getData();
        }
        LinkedNode<T> toRemove = head;
        head = toRemove.getNext();
        size = size - 1;
        return toRemove.getData();
    }

    @Override
    public void enqueue(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("You can't enter a nu"
                    + "ll value as an item in a queue.");
        }
        LinkedNode<T> newNode = new LinkedNode<T>(data);
        if (size == 0) {
            head = newNode;
            tail = newNode;
            size = size + 1;
        } else {
            tail.setNext(newNode);
            tail = newNode;
            size = size + 1;
        }
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the head of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the head node
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the tail of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the tail node
     */
    public LinkedNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }
}

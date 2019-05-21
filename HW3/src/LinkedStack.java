/**
 * Your implementation of a linked stack.
 *
 * @author Pranshav Thakkar
 * @version 1.0
 */
public class LinkedStack<T> implements StackInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private int size;

    @Override
    public boolean isEmpty() {
        return (head == null);
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("The stack is empty, yo"
                    + "u can't pop anything.");
        }
        if (size == 1) {
            LinkedNode<T> toRemove = head;
            head = null;
            size = size - 1;
            return toRemove.getData();
        }
        LinkedNode<T> toRemove = head;
        head = toRemove.getNext();
        size = size - 1;
        return toRemove.getData();
    }

    @Override
    public void push(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("You should not enter"
                    + " a null value into the stack.");
        }
        LinkedNode<T> newNode = new LinkedNode<T>(data);
        if (size == 0) {
            head = newNode;
            size = size + 1;
        } else {
            newNode.setNext(head);
            head = newNode;
            size = size + 1;
        }

    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the head of this stack.
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
}

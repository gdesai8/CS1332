/**
 * Your implementation of an array-backed queue.
 *
 * @author Pranshav Thakkar
 * @version 1.0
 */
public class ArrayQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int front;
    private int back;
    private int size;

    /**
     * Constructs a new ArrayQueue.
     */
    public ArrayQueue() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        front = 0;
        back = 0;
        size = 0;
    }

    /**
     * Dequeue from the front of the queue.
     *
     * Do not shrink the backing array.
     * If the queue becomes empty as a result of this call, you <b>must not</b>
     * explicitly reset front or back to 0.
     *
     * @see QueueInterface#dequeue()
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("The queue is empty, yo"
                                                 + "u can't remove anything.");
        }
        T toRemove = backingArray[front];
        backingArray[front] = null;
        front = (front + 1) % backingArray.length;
        size = size - 1;
        return toRemove;
    }

    /**
     * Add the given data to the queue.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow it to 1.5 times the current backing array length, rounding down
     * if necessary. If a regrow is necessary, you should copy elements to the
     * front of the new array and reset front to 0.
     *
     * @see QueueInterface#enqueue(T)
     */
    @Override
    public void enqueue(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("You should not enter"
                                            + " a null value into the queue.");
        }
        if (size == backingArray.length) {
            T[] temp = (T[]) new Object[(int) (backingArray.length * 1.5)];
            int j = 0;
            for (int i = front; i < size; i++) {
                temp[j] = backingArray[i];
                j = j + 1;
            }
            for (int i = 0; i < front; i++) {
                temp[j] = backingArray[i];
                j = j + 1;
            }
            backingArray = temp;
            front = 0;
        }
        back = (front + size) % backingArray.length;
        backingArray[back] = data;
        size = size + 1;
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
     * Returns the backing array of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the backing array
     */
    public Object[] getBackingArray() {
        // DO NOT MODIFY!
        return backingArray;
    }
}

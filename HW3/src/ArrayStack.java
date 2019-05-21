/**
 * Your implementation of an array-backed stack.
 *
 * @author Pranshav Thakkar
 * @version 1.0
 */
public class ArrayStack<T> implements StackInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayStack.
     */
    public ArrayStack() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Pop from the stack.
     *
     * Do not shrink the backing array.
     *
     * @see StackInterface#pop()
     */
    @Override
    public T pop() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("The stack is empty, yo"
                                                    + "u can't pop anything.");
        }
        T toRemove = backingArray[size - 1];
        backingArray[size - 1] = null;
        size = size - 1;
        return toRemove;
    }

    /**
     * Push the given data onto the stack.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow it to 1.5 times the current backing array length, rounding down
     * if necessary.
     *
     * @see StackInterface#push(T)
     */
    @Override
    public void push(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("You should not enter"
                                            + " a null value into the stack.");
        }
        if (size == backingArray.length) {
            T[] temp = (T[]) new Object[(int) (backingArray.length * 1.5)];
            for (int i = 0; i < size; i++) {
                temp[i] = backingArray[i];
            }
            backingArray = temp;
        }
        backingArray[size] = data;
        size = size + 1;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the backing array of this stack.
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

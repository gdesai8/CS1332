/**
 * Your implementation of an ArrayList.
 *
 * @author Pranshav Thakkar
 * @version 1
 */
public class ArrayList<T> implements ArrayListInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     *
     * You may add statements to this method.
     */
    public ArrayList() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public void addAtIndex(int index, T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Can't enter a null va"
                                                + "lue into a data structure.");
        }
        if (index < 0 || index > size) {
            throw new java.lang.IndexOutOfBoundsException("You have entered an"
                    + "index that is negative or an index that exceeds the siz"
                                                          + "e of the array.");
        }

        if (size >= backingArray.length) {
            T[] temp = (T[]) new Object[backingArray.length * 2];
            for (int i = 0; i < backingArray.length; i++) {
                temp[i] = backingArray[i];
            }
            backingArray = temp;
        }

        for (int i = size - 1; i >= index; i--) {
            backingArray[i + 1] = backingArray[i];
        }
        backingArray[index] = data;
        size = size + 1;
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Can't enter a null va"
                                                + "lue into a data structure.");
        }

        if (size >= backingArray.length) {
            T[] temp = (T[]) new Object[backingArray.length * 2];
            for (int i = 0; i < backingArray.length; i++) {
                temp[i] = backingArray[i];
            }
            backingArray = temp;
        }

        for (int i = size - 1; i >= 0; i--) {
            backingArray[i + 1] = backingArray[i];
        }
        backingArray[0] = data;
        size = size + 1;
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Can't enter a null va"
                                                + "lue into a data structure.");
        }

        if (size >= backingArray.length) {
            T[] temp = (T[]) new Object[backingArray.length * 2];
            for (int i = 0; i < backingArray.length; i++) {
                temp[i] = backingArray[i];
            }
            backingArray = temp;
        }
        backingArray[size] = data;
        size = size + 1;
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index > size) {
            throw new java.lang.IndexOutOfBoundsException("You have entered an"
                    + "index that is negative or an index that exceeds the siz"
                    + "e of the array.");
        }
        T item = backingArray[index];
        for (int i = index; i < size - 1; i++) {
            backingArray[i] = backingArray[i + 1];
        }
        backingArray[size - 1] = null;
        size = size - 1;
        return item;
    }

    @Override
    public T removeFromFront() {
        T item = backingArray[0];
        if (size != 0) {
            for (int i = 0; i < size - 1; i++) {
                backingArray[i] = backingArray[i + 1];
            }
            backingArray[size - 1] = null;
            size = size - 1;
        }
        return item;
    }

    @Override
    public T removeFromBack() {
        if (size != 0) {
            T item = backingArray[size - 1];
            backingArray[size - 1] = null;
            size = size - 1;
            return item;
        }
        return null;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new java.lang.IndexOutOfBoundsException("You have entered an"
                    + "index that is negative or an index that exceeds the siz"
                    + "e of the array.");
        }
        return backingArray[index];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public Object[] getBackingArray() {
        // DO NOT MODIFY.
        return backingArray;
    }
}

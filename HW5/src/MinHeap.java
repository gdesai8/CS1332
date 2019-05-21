/**
 * Your implementation of a min heap.
 *
 * @author Pranshav Thakkar
 * @version 1.0
 */
public class MinHeap<T extends Comparable<? super T>>
    implements HeapInterface<T> {

    private T[] backingArray;
    private int size;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a Heap with an initial size of {@code STARTING_SIZE} for the
     * backing array.
     *
     * Use the constant field in the interface. Do not use magic numbers!
     */
    public MinHeap() {
        backingArray = (T[]) new Comparable[STARTING_SIZE];
        size = 0;
    }

    @Override
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("You should not add a null item"
                    + " to the Heap.");
        }
        if (backingArray.length - 1 == size) {
            T[] temp = (T[]) new Comparable[(int) (backingArray.length * 1.5)];
            for (int i = 0; i < backingArray.length; i++) {
                temp[i] = backingArray[i];
            }
            backingArray = temp;
        }
        if (size == 0) {
            backingArray[1] = item;
            size = size + 1;
        } else {
            int index = size + 1;
            backingArray[index] = item;
            int parentIndex = index / 2;
            while (parentIndex >= 1 && backingArray[parentIndex].compareTo(
                    backingArray[index]) > 0) {
                T toParent = backingArray[index];
                T toChild = backingArray[parentIndex];
                backingArray[parentIndex] = toParent;
                backingArray[index] = toChild;
                index = parentIndex;
                parentIndex = parentIndex / 2;
            }
            size = size + 1;
        }

    }

    @Override
    public T remove() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("There is nothing in the" +
                    " Heap to remove.");
        }
        if (size == 1) {
            T toRemove = backingArray[1];
            backingArray[1] = null;
            size = size - 1;
            return toRemove;
        } else {
            int index = size;
            T toRemove = backingArray[1];
            backingArray[1] = backingArray[index];
            backingArray[index] = null;
            size = size - 1;
            index = 1;
            while ((index * 2 <= size && backingArray[index * 2] != null
                    && (backingArray[index].compareTo(backingArray[index * 2])
                    > 0))
                    || ((index * 2) + 1 <= size
                    && backingArray[(index * 2) + 1] != null)
                    && (backingArray[index].compareTo(backingArray[(index * 2)
                    + 1]) > 0)) {
                if (backingArray[index * 2] != null && backingArray[(index * 2) + 1] != null) {
                    if (backingArray[index * 2].compareTo(backingArray[(index * 2) + 1]) < 0) {
                        T toChild = backingArray[index];
                        T toParent = backingArray[index * 2];
                        backingArray[index] = toParent;
                        backingArray[index * 2] = toChild;
                        index = index * 2;
                    } else if (backingArray[index * 2].compareTo(backingArray[(index * 2) + 1]) > 0) {
                        T toChild = backingArray[index];
                        T toParent = backingArray[(index * 2) + 1];
                        backingArray[index] = toParent;
                        backingArray[(index * 2) + 1] = toChild;
                        index = (index * 2) + 1;
                    }
                } else if (backingArray[index * 2] != null) {
                    T toChild = backingArray[index];
                    T toParent = backingArray[index * 2];
                    backingArray[index] = toParent;
                    backingArray[index * 2] = toChild;
                    index = index * 2;
                } else if (backingArray[(index * 2) + 1] != null) {
                    T toChild = backingArray[index];
                    T toParent = backingArray[(index * 2) + 1];
                    backingArray[index] = toParent;
                    backingArray[(index * 2) + 1] = toChild;
                    index = (index * 2) + 1;
                }
            }
            return toRemove;
        }
    }

    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        backingArray = (T[]) new Comparable[STARTING_SIZE];
        size = 0;
    }

    @Override
    public Comparable[] getBackingArray() {
        // DO NOT CHANGE THIS METHOD!
        return backingArray;
    }

}

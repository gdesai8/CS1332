/**
 * Your implementation of a min priority queue.
 * 
 * @author Pranshav Thakkar
 * @version 1.0
 */
public class MinPriorityQueue<T extends Comparable<? super T>>
    implements PriorityQueueInterface<T> {

    private HeapInterface<T> backingHeap;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a priority queue.
     */
    public MinPriorityQueue() {
        backingHeap = new MinHeap<T>();
    }

    @Override
    public void enqueue(T item) {
        if (item == null) {
            throw new IllegalArgumentException("You should not add a null eleme"
                    + "nt to the PriorityQueue.");
        }
        backingHeap.add(item);
    }

    @Override
    public T dequeue() {
        if (size() == 0) {
            throw new java.util.NoSuchElementException("There is nothing in th"
                    + "e PriorityQueue to remove.");
        }
        return backingHeap.remove();
    }

    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    @Override
    public int size() {
        return backingHeap.size();
    }

    @Override
    public void clear() {
        backingHeap = new MinHeap<T>();
    }

    @Override
    public HeapInterface<T> getBackingHeap() {
        // DO NOT CHANGE THIS METHOD!
        return backingHeap;
    }

}

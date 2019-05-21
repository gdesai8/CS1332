/**
 * Your implementation of a DoublyLinkedList
 *
 * @author Pranshav Thakkar
 * @version 1.0
 */
public class DoublyLinkedList<T> implements LinkedListInterface<T> {
    // Do not add new instance variables.
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;
    private int size;

    @Override
    public void addAtIndex(int index, T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("You can't enter a nu"
                    + "ll value as an item in a Linked List.");
        }
        if (index < 0 || index > size) {
            throw new java.lang.IndexOutOfBoundsException("You can't add an el"
                    + "ement before the head or add an element that is beyond "
                    + "the tail but not right after it.");
        }
        if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
            LinkedListNode<T> newNode = new LinkedListNode<T>(data);
            LinkedListNode<T> toIterate = head;
            for (int i = 0; i < index; i++) {
                toIterate = toIterate.getNext();
            }
            newNode.setNext(toIterate);
            newNode.setPrevious(toIterate.getPrevious());
            toIterate.getPrevious().setNext(newNode);
            toIterate.setPrevious(newNode);
            size = size + 1;
        }
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("You can't enter a nu"
                                    + "ll value as an item in a Linked List.");
        }
        LinkedListNode<T> newNode = new LinkedListNode<T>(data);
        if (size == 0) {
            head = newNode;
            tail = newNode;
            size = size + 1;
        } else {
            head.setPrevious(newNode);
            newNode.setNext(head);
            head = newNode;
            size = size + 1;
        }
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("You can't enter a nu"
                    + "ll value as an item in a Linked List.");
        }
        LinkedListNode<T> newNode = new LinkedListNode<T>(data);
        if (size == 0) {
            head = newNode;
            tail = newNode;
            size = size + 1;
        } else {
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            tail = newNode;
            size = size + 1;
        }
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new java.lang.IndexOutOfBoundsException("You can't remove an"
                    + "element before the head or remove an element that is af"
                    + "ter the tail.");
        }
        if (index == 0) {
            return removeFromFront();
        } else if (index == size - 1) {
            return removeFromBack();
        }
        LinkedListNode<T> toRemove;
        LinkedListNode<T> toIterate = head;
        for (int i = 0; i < index; i++) {
            toIterate = toIterate.getNext();
        }
        toRemove = toIterate;
        toRemove.getPrevious().setNext(toRemove.getNext());
        toRemove.getNext().setPrevious(toRemove.getPrevious());
        size = size - 1;
        return toRemove.getData();

    }

    @Override
    public T removeFromFront() {
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            LinkedListNode<T> toRemove = head;
            head = null;
            tail = null;
            size = size - 1;
            return toRemove.getData();
        }
        LinkedListNode<T> toRemove = head;
        toRemove.getNext().setPrevious(null);
        head = toRemove.getNext();
        size = size - 1;
        return toRemove.getData();
    }

    @Override
    public T removeFromBack() {
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            LinkedListNode<T> toRemove = tail;
            head = null;
            tail = null;
            size = size - 1;
            return toRemove.getData();
        }
        LinkedListNode<T> toRemove = tail;
        toRemove.getPrevious().setNext(null);
        tail = toRemove.getPrevious();
        size = size - 1;
        return toRemove.getData();
    }

    @Override
    public boolean removeAllOccurrences(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("You shouldn't have p"
                    + "assed in null as an argument as there is no point in tr"
                    + "ying to remove null elements from a list.");
        }
        LinkedListNode<T> toIterate = head;
        int counter = 0;
        for (int i = 0; i < size; i++) {
            if (toIterate.getData().equals(data)) {
                removeAtIndex(i);
                counter = counter + 1;
                i = i - 1; //Because deleting the element shifts the element to
                // i's position
            }
            toIterate = toIterate.getNext();
        }
        if (counter > 0) {
            return true;
        }
        return false;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new java.lang.IndexOutOfBoundsException("You can't get an el"
                    + "ement's data that is before the head or the data of an "
                    + "element that is beyond the tail");
        }
        LinkedListNode<T> toIterate = head;
        for (int i = 0; i < index; i++) {
            toIterate = toIterate.getNext();
        }
        return toIterate.getData();
    }

    @Override
    public Object[] toArray() {
        Object[] listArray = new Object[size];
        LinkedListNode<T> toIterate = head;
        for (int i = 0; i < size; i++) {
            listArray[i] = toIterate.getData();
            toIterate = toIterate.getNext();
        }
        return listArray;
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
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    @Override
    public LinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }
}

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Your implementation of HashMap.
 * 
 * @author Pranshav Thakkar
 * @version 1.0
 */
public class HashMap<K, V> implements HashMapInterface<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code INITIAL_CAPACITY}.
     *
     * Do not use magic numbers!
     *
     * Use constructor chaining.
     */
    public HashMap() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}.
     *
     * You may assume {@code initialCapacity} will always be positive.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    public HashMap(int initialCapacity) {
        table = (MapEntry<K, V>[]) new MapEntry[initialCapacity];
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("You shouldn't enter a key or a"
                    + " value that is null into the HashMap.");
        }
        if (((size + 1) / (double) table.length) > MAX_LOAD_FACTOR) {
            resizeBackingTable((2 * table.length) + 1);
        }
        int index = Math.abs(key.hashCode()) % table.length;
        V toReturn = null;
        if (table[index] == null) {
            MapEntry<K, V> newEntry = new MapEntry<K, V>(key, value);
            table[index] = newEntry;
            size = size + 1;
            return toReturn;
        } else if (table[index].getKey().equals(key)) {
            toReturn = table[index].getValue();
            table[index].setValue(value);
            return toReturn;
        } else {
            MapEntry<K, V> current = table[index];
            while (current.getNext() != null) {
                current = current.getNext();
                if (current.getKey().equals(key)) {
                    toReturn = current.getValue();
                    current.setValue(value);
                    return toReturn;
                }
            }
            if (current.getKey().equals(key)) {
                toReturn = current.getValue();
                current.setValue(value);
                return toReturn;
            }
            current = table[index];
            MapEntry<K, V> newEntry = new MapEntry<K, V>(key, value);
            newEntry.setNext(current);
            table[index] = newEntry;
            size = size + 1;
            return null;
        }
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("There shouldn't be any null ke"
                    + "ys in the HashMap to remove.");
        }
        V toReturn;
        int index = Math.abs(key.hashCode()) % table.length;
        MapEntry<K, V> current = table[index];
        MapEntry<K, V> previous;
        if (current == null) {
            throw new java.util.NoSuchElementException("The key was not found "
                    + "in the HashMap.");
        }
        if (current.getKey().equals(key)) {
            toReturn = table[index].getValue();
            table[index] = table[index].getNext();
            size = size - 1;
            return toReturn;
        } else {
            while (current.getNext() != null) {
                previous = current;
                current = current.getNext();
                if (current.getKey().equals(key)) {
                    toReturn = current.getValue();
                    previous.setNext(current.getNext());
                    size = size - 1;
                    return toReturn;
                }
            }
        }
        throw new java.util.NoSuchElementException("The key was not found in t"
                + "he HashMap.");
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("There shouldn't be any null ke"
                    + "ys in the HashMap to get a value from.");
        }
        int index = Math.abs(key.hashCode()) % table.length;
        MapEntry<K, V> current = table[index];
        if (current == null) {
            throw new java.util.NoSuchElementException("The key was not found "
                    + "in the HashMap.");
        }
        if (current.getKey().equals(key)) {
            return current.getValue();
        } else {
            while (current.getNext() != null) {
                current = current.getNext();
                if (current.getKey().equals(key)) {
                    return current.getValue();
                }
            }
            if (current.getKey().equals(key)) {
                return current.getValue();
            }
        }
        throw new java.util.NoSuchElementException("The key was not found in t"
                + "he HashMap.");
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("There shouldn't be any null ke"
                    + "ys in the HashMap.");
        }
        int index = Math.abs(key.hashCode()) % table.length;
        MapEntry<K, V> current = table[index];
        if (current == null) {
            return false;
        }
        if (current.getKey().equals(key)) {
            return true;
        } else {
            while (current.getNext() != null) {
                current = current.getNext();
                if (current.getKey().equals(key)) {
                    return true;
                }
            }
            if (current.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        table = (MapEntry<K, V>[]) new MapEntry[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<K>();
        for (int i = 0; i < table.length; i++) {
            MapEntry<K, V> current = table[i];
            if (current != null) {
                keySet.add(current.getKey());
                while (current.getNext() != null) {
                    current = current.getNext();
                    keySet.add(current.getKey());
                }
            }
        }
        return keySet;
    }

    @Override
    public List<V> values() {
        List<V> valueList = new ArrayList<V>();
        for (int i = 0; i < table.length; i++) {
            MapEntry<K, V> current = table[i];
            if (current != null) {
                valueList.add(current.getValue());
                while (current.getNext() != null) {
                    current = current.getNext();
                    valueList.add(current.getValue());
                }
            }
        }
        return valueList;
    }

    @Override
    public void resizeBackingTable(int length) {
        if (length < 1 || length < size) {
            throw new IllegalArgumentException("The length of the new HashMap "
                    + "is either non-positive or smaller than the number of el"
                    + "ements.");
        }
        MapEntry<K, V>[] oldTable = table;
        table = (MapEntry<K, V>[]) new MapEntry[length];
        size = 0;
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != null) {
                addResize(oldTable[i].getKey(), oldTable[i].getValue());
                MapEntry<K, V> current = oldTable[i];
                while (current.getNext() != null) {
                    current = current.getNext();
                    addResize(current.getKey(), current.getValue());
                }
            }
        }
    }

    /**
     * This is a helper method for adding the key-value pairs into a resized
     * HashMap
     *
     * @param key the key to be added into the resized HashMap
     * @param value the value to be added into the resized HashMap
     */
    private void addResize(K key, V value) {
        int index = Math.abs(key.hashCode()) % table.length;
        if (table[index] == null) {
            MapEntry<K, V> newEntry = new MapEntry<K, V>(key, value);
            table[index] = newEntry;
            size = size + 1;
        } else {
            MapEntry<K, V> current = table[index];
            MapEntry<K, V> newEntry = new MapEntry<K, V>(key, value);
            newEntry.setNext(current);
            table[index] = newEntry;
            size = size + 1;
        }
    }
    
    @Override
    public MapEntry<K, V>[] getTable() {
        // DO NOT EDIT THIS METHOD!
        return table;
    }

}

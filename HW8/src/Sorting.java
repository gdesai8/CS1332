import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Pranshav Thakkar
 * @version 1.0
 */
public class Sorting {

    /**
     * Implement cocktail sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     *
     * See the PDF for more info on this sort.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void cocktailSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Either one or both of the para"
                    + "meters are null.");
        }
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < arr.length - 1; i++) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    swap(arr, i, i + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                return;
            }
            swapped = false;
            for (int i = arr.length - 2; i > 0; i--) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    swap(arr, i, i + 1);
                    swapped = true;
                }
            }
        } while (swapped);
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     *
     * See the PDF for more info on this sort.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Either one or both of the para"
                    + "meters are null.");
        }
        for (int i = 0; i < arr.length; i++) {
            int j = i;
            while (j > 0 && comparator.compare(arr[j - 1], arr[j]) > 0) {
                swap(arr, j, j - 1);
                j = j - 1;
            }
        }
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n^2)
     *
     * Note that there may be duplicates in the array, but they may not
     * necessarily stay in the same relative order.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Either one or both of the para"
                    + "meters are null.");
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (comparator.compare(arr[j], arr[min]) < 0) {
                    min = j;
                }
            }
            swap(arr, min, i);
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = r.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * Note that there may be duplicates in the array.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not use the one we have taught you!
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null || comparator == null || rand == null) {
            throw new IllegalArgumentException("Either one or all of the para"
                    + "meters are null.");
        }
        quickSortHelper(arr, 0, arr.length - 1, comparator, rand);
    }

    /**
     * Helper method to sort an array using quick sort method
     *
     * @param array the array to be sorted
     * @param start the start of the array
     * @param end the end of the array
     * @param comparator the Comparator used to compare the data in the array
     * @param rand the Random object used to select pivots
     * @param <T> data type to sort
     */
    public static <T> void quickSortHelper(T[] array, int start, int end,
                                           Comparator<T> comparator,
                                           Random rand) {
        if (end > start) {
            int pivotIndex = rand.nextInt(end - start) + start;
            swap(array, pivotIndex, start);
            T pivot = array[start];
            int leftIndex = start + 1;
            int rightIndex = end;
            if (leftIndex == rightIndex) {
                if (comparator.compare(array[rightIndex], pivot) >= 0) {
                    rightIndex = rightIndex - 1;
                }
            }
            while (leftIndex < rightIndex) {
                while (leftIndex < rightIndex
                        && comparator.compare(array[leftIndex], pivot) <= 0) {
                    leftIndex = leftIndex + 1;
                }
                while (leftIndex <= rightIndex
                        && comparator.compare(array[rightIndex], pivot) >= 0) {
                    rightIndex = rightIndex - 1;
                }
                if (leftIndex < rightIndex) {
                     swap(array, leftIndex, rightIndex);
                    leftIndex = leftIndex + 1;
                    rightIndex = rightIndex - 1;
                }
            }
            swap(array, rightIndex, start);
            quickSortHelper(array, start, rightIndex - 1, comparator, rand);
            quickSortHelper(array, rightIndex + 1, end, comparator, rand);
        }
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Either one or both of the para"
                    + "meters are null.");
        }
        mergeSortHelper(arr, comparator);
    }

    /**
     * Helper method to sort an array using the merge sort method
     *
     * @param array the array to be sorted
     * @param comparator the Comparator used to compare the items in the array
     * @param <T> data type to be sorted
     */
    private static <T> void mergeSortHelper(T[] array,
                                            Comparator<T> comparator) {
        int mid = array.length / 2;
        T[] left = (T[]) new Object[mid];
        T[] right = (T[]) new Object[array.length - mid];
        for (int i = 0; i < mid; i++) {
            left[i] = array[i];
        }
        int j = 0;
        for (int i = mid; i < array.length; i++) {
            right[j] = array[i];
            j = j + 1;
        }
        if (array.length > 1) {
            mergeSortHelper(left, comparator);
            mergeSortHelper(right, comparator);
        }
        int leftIndex = 0;
        int rightIndex = 0;
        int current = 0;
        while (leftIndex < mid && rightIndex < (array.length - mid)) {
            if (comparator.compare(left[leftIndex], right[rightIndex]) <= 0) {
                array[current] = left[leftIndex];
                leftIndex = leftIndex + 1;
            } else {
                array[current] = right[rightIndex];
                rightIndex = rightIndex + 1;
            }
            current = current + 1;
        }
        while (leftIndex < mid) {
            array[current] = left[leftIndex];
            leftIndex = leftIndex + 1;
            current = current + 1;
        }
        while (rightIndex < array.length - mid) {
            array[current] = right[rightIndex];
            rightIndex = rightIndex + 1;
            current = current + 1;
        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code!
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * Do NOT use {@code Math.pow()} in your sort. Instead, if you need to, use
     * the provided {@code pow()} method below.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     * @return the sorted array
     */
    public static int[] lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("You should not have a null arr"
                    + "ay passed into the parameter.");
        }
        LinkedList<LinkedList<Integer>> buckets = new LinkedList<>();
        for (int i = 0; i < 19; i++) {
            buckets.add(new LinkedList<Integer>());
        }
        int iterations = 1;
        int bucket;
        for (int i = 0; i < arr.length; i++) {
            int counter = 1;
            int division = arr[i] / pow(10, counter);
            while (division > 0) {
                counter = counter + 1;
                if (counter > iterations) {
                    iterations = counter;
                }
                division = arr[i] / pow(10, counter);
            }
        }
        for (int i = 1; i <= iterations; i++) {
            for (int j = 0; j < arr.length; j++) {
                bucket = ((arr[j] / pow(10, i - 1)) % 10) + 9;
                buckets.get(bucket).add(arr[j]);
            }
            int index = 0;
            for (int b = 0; b < 19; b++) {
                while (!(buckets.get(b).isEmpty())) {
                    arr[index++] = buckets.get(b).removeFirst();
                }
            }
        }
        return arr;
    }

    /**
     * Calculate the result of a number raised to a power. Use this method in
     * your radix sorts instead of {@code Math.pow()}.
     *
     * DO NOT MODIFY THIS METHOD.
     *
     * @throws IllegalArgumentException if both {@code base} and {@code exp} are
     * 0
     * @throws IllegalArgumentException if {@code exp} is negative
     * @param base base of the number
     * @param exp power to raise the base to. Must be 0 or greater.
     * @return result of the base raised to that power
     */
    private static int pow(int base, int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("Exponent cannot be negative.");
        } else if (base == 0 && exp == 0) {
            throw new IllegalArgumentException(
                    "Both base and exponent cannot be 0.");
        } else if (exp == 0) {
            return 1;
        } else if (exp == 1) {
            return base;
        }
        int halfPow = pow(base, exp / 2);
        if (exp % 2 == 0) {
            return halfPow * halfPow;
        } else {
            return halfPow * pow(base, (exp / 2) + 1);
        }
    }

    /**
     * Method that swaps two elements in the array
     *
     * @param array the array containing the elements
     * @param a the index of the first element to be swapped
     * @param b the index of the second element to be swapped
     * @param <T> data type of the elements being swapped
     */
    private static <T> void swap(T[] array, int a, int b) {
        T temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}

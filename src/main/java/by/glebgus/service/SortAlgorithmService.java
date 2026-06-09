package service;

import entity.NumberArray;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Optional;

public class SortAlgorithmService {
    private static final Logger logger = LogManager.getLogger(SortAlgorithmService.class);

    private double[] mergeSortAlgorithm(double[] array) {
        if (array.length <= 1) return array;

        int mid = array.length / 2;
        double[] left = mergeSortAlgorithm(Arrays.copyOfRange(array, 0, mid));
        double[] right = mergeSortAlgorithm(Arrays.copyOfRange(array, mid, array.length));

        return merge(left, right);
    }

    private double[] merge(double[] left, double[] right) {
        double[] result = new double[left.length + right.length];
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            result[k++] = (left[i] <= right[j]) ? left[i++] : right[j++];
        }
        while (i < left.length) result[k++] = left[i++];
        while (j < right.length) result[k++] = right[j++];
        return result;
    }

    public <T extends Number & Comparable<T>> Optional<Double[]> mergeSort(NumberArray<T> entity) {
        return Optional.ofNullable(entity)
                .map(NumberArray::array)
                .filter(arr -> arr.length > 0)
                .map(arr -> {
                    double[] doubles = Arrays.stream(arr).mapToDouble(Number::intValue).toArray();

                    double[] sortedArray = mergeSortAlgorithm(doubles);

                    Optional.of(Arrays.toString(sortedArray))
                            .ifPresent(value -> logger.info("Sorted array: " + value));
                    return Arrays.stream(sortedArray).boxed().toArray(Double[]::new);
                });
    }

    public static <T extends Comparable<T>> void quickSort(NumberArray<T> numberArray) {
        T[] arr = numberArray.array();
        if (arr == null || arr.length <= 1) return;
        sort(arr, 0, arr.length - 1);
    }

    private static <T extends Comparable<T>> void sort(T[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            sort(arr, low, pivotIndex);
            sort(arr, pivotIndex + 1, high);
        }
    }

    private static <T extends Comparable<T>> int partition(T[] arr, int low, int high) {
        T pivot = arr[low + (high - low) / 2];
        int i = low - 1;
        int j = high + 1;

        while (true) {
            do {
                i++;
            } while (arr[i].compareTo(pivot) < 0);
            do {
                j--;
            } while (arr[j].compareTo(pivot) > 0);

            if (i >= j) return j;

            T temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}

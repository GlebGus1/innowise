package service;

import entity.NumberArray;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SortAlgorithmServiceTest {
    SortAlgorithmService sortAlgorithmService = new SortAlgorithmService();

    @Test
    void testMergeSortSuccess() {

        NumberArray<Integer> array = new NumberArray<>(new Integer[]{5, 1, 4, 2, 3});

        Optional<Double[]> result = sortAlgorithmService.mergeSort(array);

        assertTrue(result.isPresent(), "Result should be present");
        Double[] sorted = result.get();

        assertArrayEquals(new Double[]{1.0, 2.0, 3.0, 4.0, 5.0}, sorted, "Array should be sorted");
    }
    @Test
    void testQuickSortWithRandomNumbers() {
        Double[] data = {5.5, 1.2, 8.9, 3.3, 1.2, 0.0, -5.5};

        NumberArray<Double> numberArray = new NumberArray<>(data);

        Double[] expected = {-5.5, 0.0, 1.2, 1.2, 3.3, 5.5, 8.9};

        SortAlgorithmService.quickSort(numberArray);

        assertArrayEquals(expected, numberArray.array());
    }
}

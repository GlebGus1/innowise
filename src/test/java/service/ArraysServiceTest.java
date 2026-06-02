package service;

import entity.NumberArray;
import exception.InvalidArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArraysServiceTest {

    ArraysService arraysService = new ArraysService();

    @Test
    void testFindMinDoubleSuccess() {
        NumberArray<Double> validArray = new NumberArray<>(new Double[]{5.0, 2.0, 8.0, 1.0});

        Double min = arraysService.findMin(validArray);

        assertEquals(1, min, "min must be 1");
    }
    @Test
    void testFindMinIntegerSuccess() {
        NumberArray<Integer> validArray = new NumberArray<>(new Integer[]{1, 4, 5, 6, 3});

        Integer min = arraysService.findMin(validArray);

        assertEquals(1, min, "min must be 1");
    }

    @Test
    void testFindMinThrowsExceptionOnInvalidData() {
        NumberArray<Integer> emptyArray = new NumberArray<>(new Integer[]{});

        assertThrows(InvalidArgumentException.class, () ->
            arraysService.findMin(emptyArray));
    }
    @Test
    void testFindMaxDoubleSuccess() {
        NumberArray<Double> validArray = new NumberArray<>(new Double[]{5.0, 2.0, 8.0, 1.0});

        Double max = arraysService.findMax(validArray);

        assertEquals(8, max, "Max must be 8");
    }
    @Test
    void testFindMaxIntegerSuccess() {
        NumberArray<Integer> validArray = new NumberArray<>(new Integer[]{5, 2, 8, 1});

        Integer max = arraysService.findMax(validArray);

        assertEquals(8, max, "Max must be 8");
    }
    @Test
    void testFindAverageDoubleSuccess() {
        NumberArray<Double> validArray = new NumberArray<>(new Double[]{5.0, 2.0, 8.0, 5.0});

        Double average = arraysService.findAverage(validArray);

        assertEquals(5, average, "Average must be 5");
    }
    @Test
    void testFindAverageIntegerSuccess() {
        NumberArray<Integer> validArray = new NumberArray<>(new Integer[]{5, 2, 8, 5});

        Double average = arraysService.findAverage(validArray);

        assertEquals(5, average, "Average must be 5");
    }
    @Test
    void testFileReadingWithBadData() {
        FileParserService reader = new FileParserService();

        assertThrows(InvalidArgumentException.class, () -> {
            reader.readNumbersFromFile("numbers.txt");
        });
    }

}

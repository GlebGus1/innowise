package service;

import entity.NumberArray;
import exception.InvalidArgumentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Arrays;
import java.util.Comparator;

public class ArraysService {
    private static final Logger logger = LogManager.getLogger(ArraysService.class);

    public <T extends Comparable<T>> T findMin(NumberArray<T> numberArray) {

        validate(numberArray);

        T min = Arrays.stream(numberArray.array())
                .min(Comparator.naturalOrder())
                .orElseThrow(() -> new InvalidArgumentException("Invalid argument"));

        logger.info("Min: {}", min);

        return min;
    }

    public <T extends Comparable<T>> T findMax(NumberArray<T> numberArray) {

        validate(numberArray);

        T max = Arrays.stream(numberArray.array())
                .max(Comparator.naturalOrder())
                .orElseThrow(() -> new InvalidArgumentException("Invalid argument"));

        logger.info("Max: {}", max);

        return max;
    }

    public <T extends Number & Comparable<T>> Double findSum(NumberArray<T> numberArray) {

        validate(numberArray);
        Double sum = Arrays.stream(numberArray.array())
                .map(Number::doubleValue)
                .reduce(Double::sum)
                .orElseThrow(() -> new InvalidArgumentException("Array is empty"));

        logger.info("Sum: {}",sum );

        return  sum;
    }

    public <T extends Number & Comparable<T>> Double findAverage(NumberArray<T> numberArray) {

        validate(numberArray);

        Double average = Arrays.stream(numberArray.array())
                .mapToDouble(Number::doubleValue)
                .average()
                .orElseThrow(() -> new InvalidArgumentException("Array is empty;"));

        logger.info("Average: {}",average );

        return  average;

    }

    private <T extends Comparable<T>> void validate(NumberArray<T> numberArray) {
        if (numberArray == null || numberArray.array() == null) {
            throw new InvalidArgumentException("Array is null");
        }
    }
}

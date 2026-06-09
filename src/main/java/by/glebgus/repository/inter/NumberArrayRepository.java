package repository.inter;

import entity.NumberArray;

import java.util.List;

public interface NumberArrayRepository {
    NumberArray<?> findById(int id);

    List<NumberArray<?>> findAllArraysWithSumGreaterThan(double param);

    List<NumberArray<?>> findAllArraysWithSumLessThan(double param);

    List<NumberArray<?>> findAllArraysWithAverageLessThan(double param);

    List<NumberArray<?>> findAllArraysWithCountGreaterThan(double param);

    List<NumberArray<?>> sortByCount();

    List<NumberArray<?>> getAllArrays();

    List<NumberArray<?>> sortByFirstElement();

    List<NumberArray<?>> sortById();

    void createArray(NumberArray<?> numberArray);

    void deleteArrayById(NumberArray<?> numberArray);
}

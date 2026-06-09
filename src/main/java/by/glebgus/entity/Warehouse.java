package entity;

import repository.NumberArrayRepositoryImpl1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private static Warehouse instance;
    private Warehouse() {}
    public static Warehouse getInstance() {
        if (instance == null) instance = new Warehouse();
        return instance;
    }
    private final Map<Integer, NumberArrayRepositoryImpl1> results = new HashMap<>();

    public void updateCalculations(NumberArray<?> array) {
        double[] vals = Arrays.stream(array.array()).mapToDouble(n -> ((Number)n).doubleValue()).toArray();
        double sum = Arrays.stream(vals).sum();
}

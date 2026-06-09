package by.glebgus.repository;

import by.glebgus.entity.NumberArray;
import by.glebgus.entity.Warehouse;
import by.glebgus.listener.RepositoryChangeListener;
import by.glebgus.repository.inter.NumberArrayRepository;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class NumberArrayRepositoryImpl1 implements NumberArrayRepository {
    private static final Logger LOGGER = Logger.getLogger(NumberArrayRepositoryImpl1.class.getName());
    private static NumberArrayRepositoryImpl1 instance;
    private final Map<Integer, NumberArray<?>> numberArrayRepository = new HashMap<>();
    private final List<RepositoryChangeListener> listeners = new ArrayList<>();

    private NumberArrayRepositoryImpl1() {
    }

    public static NumberArrayRepositoryImpl1 getInstance() {
        if (instance == null) instance = new NumberArrayRepositoryImpl1();
        return instance;
    }
    public void addListener(RepositoryChangeListener listener){
        listeners.add(listener);
    }

    private void notifyListeners(NumberArray<?> array){
        for (RepositoryChangeListener listener : listeners){
            listener.onUpdate(array);
        }
    }

    @Override
    public Optional<NumberArray<?>> findById(int id) {
        return Optional.ofNullable(numberArrayRepository.get(id));
    }

    @Override
    public List<NumberArray<?>> findAllArraysWithSumGreaterThan(double param) {
        Warehouse warehouse = Warehouse.getInstance();
        return numberArrayRepository.values().stream()
                .filter(numberArray -> {
                    Warehouse.CalcResults results = warehouse.getResults().get(numberArray.id());
                    if (results == null) {
                        warehouse.updateCalculations(numberArray);
                        results = warehouse.getResults().get(numberArray.id());
                    }
                    return results.sum() > param;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<NumberArray<?>> findAllArraysWithSumLessThan(double param) {
        Warehouse warehouse = Warehouse.getInstance();
        return numberArrayRepository.values().stream()
                .filter(numberArray -> {
                    Warehouse.CalcResults results = warehouse.getResults().get(numberArray.id());
                    if (results == null) {
                        warehouse.updateCalculations(numberArray);
                        results = warehouse.getResults().get(numberArray.id());
                    }
                    return results.sum() < param;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<NumberArray<?>> findAllArraysWithAverageLessThan(double param) {
        Warehouse warehouse = Warehouse.getInstance();
        return numberArrayRepository.values().stream()
                .filter(numberArray -> {
                    Warehouse.CalcResults results = warehouse.getResults().get(numberArray.id());
                    if (results == null) {
                        warehouse.updateCalculations(numberArray);
                        results = warehouse.getResults().get(numberArray.id());
                    }
                    return results.avg() < param;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<NumberArray<?>> findAllArraysWithCountGreaterThan(double param) {
        return numberArrayRepository.values().stream()
                .filter(numberArray -> {
                    int count = numberArray.array().length;
                    return count > param;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<NumberArray<?>> sortByCount() {
        List<NumberArray<?>> list = getAllArrays();
        list.sort(Comparator.comparingInt(arrays -> arrays.array().length));
        return list;
    }

    @Override
    public List<NumberArray<?>> getAllArrays() {
        return new ArrayList<>(numberArrayRepository.values());
    }

    @Override
    public List<NumberArray<?>> sortByFirstElement() {
        List<NumberArray<?>> list = getAllArrays();
        list.sort(Comparator.comparingDouble(arrays ->
                ((Number) arrays.array()[0]).doubleValue()));
        return list;
    }

    @Override
    public List<NumberArray<?>> sortById() {
        return numberArrayRepository.values().stream().sorted(Comparator.comparingInt(NumberArray::id))
                .collect(Collectors.toList());
    }

    @Override
    public void createArray(NumberArray<?> numberArray) {
        numberArrayRepository.put(numberArray.id(), numberArray);
        notifyListeners(numberArray);
    }

    @Override
    public void deleteArrayById(NumberArray<?> numberArray) {
        numberArrayRepository.remove(numberArray.id());
        notifyListeners(numberArray);
    }
}

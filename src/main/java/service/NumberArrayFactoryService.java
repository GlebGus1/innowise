package service;

import entity.NumberArray;

public class NumberArrayFactoryService {
    public NumberArray<Double> createNewNumberArray(Double[] array){
        return new NumberArray<>(array);
    }
}

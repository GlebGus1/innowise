package entity;

public record NumberArray<T extends Comparable<T>>(T[] array, int id) {

}

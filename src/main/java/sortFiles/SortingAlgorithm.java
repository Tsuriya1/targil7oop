package sortFiles;

public interface SortingAlgorithm <T extends Comparable<T>>{
    void sort(T[] array);
}

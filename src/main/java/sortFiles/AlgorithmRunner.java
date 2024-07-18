package sortFiles;

import jakarta.inject.Inject;

import java.util.Random;

public class AlgorithmRunner {

    @Inject
    @QuadraticAlgorithm
    SortingAlgorithm<Integer> quadraticAlgorithm;

    @Inject
    @NlognAlgorithm
    SortingAlgorithm<Integer> nlognAlgorithm;

    @Inject
    @RandomAlgorithm1
    SortingAlgorithm<Integer> randomAlgorithm1;

    @Inject
    @RandomAlgorithm2
    SortingAlgorithm<Integer> randomAlgorithm2;

    @Inject
    @NumberOfElements
    int numberOfElements;

    public void runAlgorithms() {
        Random rand = new Random();
        Integer[] ints = rand.ints(1, Integer.MAX_VALUE)
                .distinct()
                .limit(numberOfElements)
                .boxed()
                .toArray(Integer[]::new);
        Integer[] intsClone = ints.clone();
        quadraticAlgorithm.sort(intsClone);
        intsClone = ints.clone();
        nlognAlgorithm.sort(intsClone);
        intsClone = ints.clone();
        randomAlgorithm1.sort(intsClone);
        intsClone = ints.clone();
        randomAlgorithm2.sort(intsClone);
    }
}

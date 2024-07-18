package sortFiles;

import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.util.AnnotationLiteral;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.jboss.weld.inject.WeldInstance;

import java.util.Random;

public class MainApp {

    private static Weld weld = new Weld();
    private static WeldContainer container = weld.initialize();

    public static void main(String[] args) {
        AlgorithmRunner algorithmRunner = container.select(AlgorithmRunner.class).get();
        algorithmRunner.runAlgorithms();
        shutdown();
    }

    public static void shutdown() {
        weld.shutdown();
    }

    // Producers for SortingAlgorithms and numberOfElements
    @Produces
    @QuadraticAlgorithm
    public SortingAlgorithm<Integer> produceQuadraticAlgorithm() {
        return container.select(BubbleSort.class).get();
    }

    @Produces
    @NlognAlgorithm
    public SortingAlgorithm<Integer> produceNlognAlgorithm() {
        return container.select(MergeSort.class).get();
    }

    @Produces
    @RandomAlgorithm1
    public SortingAlgorithm<Integer> produceRandomAlgorithm1() {
        return makeRandomSortingAlgorithm();
    }

    @Produces
    @RandomAlgorithm2
    public SortingAlgorithm<Integer> produceRandomAlgorithm2() {
        return makeRandomSortingAlgorithm();
    }

    @Produces
    @NumberOfElements
    public int produceNumberOfElements() {
        return 1000;
    }

    private static SortingAlgorithm<Integer> makeRandomSortingAlgorithm() {
        Random random = new Random(System.currentTimeMillis());
        WeldInstance<Object> instance = container.select(new AnnotationLiteral<Any>(){});
        SortingAlgorithm<Integer> sortAlg = null;

        switch (random.nextInt(4)) {
            case 0:
                sortAlg = instance.select(QuickSort.class).get();
                break;
            case 1:
                sortAlg = instance.select(MergeSort.class).get();
                break;
            case 2:
                sortAlg = instance.select(BubbleSort.class).get();
                break;
            case 3:
                sortAlg = instance.select(InsertionSort.class).get();
                break;
        }

        return sortAlg;
    }
}

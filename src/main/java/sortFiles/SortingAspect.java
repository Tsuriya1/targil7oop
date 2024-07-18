package sortFiles;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class SortingAspect {
    private Map<String, Long> executionTimes = new HashMap<>();
    private Map<String, Integer> callCounts = new HashMap<>();
    private long totalExecutionTime = 0;
    private long startTime = 0;
    @Pointcut("execution(* *.sort(..))")
    public void sortMethod() {}

    @Before("sortMethod()")
    public void beforeSortMethod() {
    }


    @Before("sortMethod()")
    public void beforeSort(JoinPoint jp){
        this.startTime = System.currentTimeMillis();
    }
    @After("sortMethod()")
    public void afterSort(JoinPoint jp){
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - this.startTime;
        String methodName = jp.getSignature().toShortString();
        executionTimes.put(methodName, executionTimes.getOrDefault(methodName, 0L) + executionTime);
        callCounts.put(methodName, callCounts.getOrDefault(methodName, 0) + 1);
        totalExecutionTime += executionTime;
    }

    @Pointcut("execution(* AlgorithmRunner.runAlgorithms(..))")
    public void afterAlgo() {}
    @After("afterAlgo()")
    public void afterAllSortMethods() {
        System.out.println("Total time of running all sort functions was " + totalExecutionTime + " ms");
        System.out.println("In detail:");
        System.out.println("Function QuickSort ran " + callCounts.get("QuickSort.sort(..)") + " times and took in total " + executionTimes.get("QuickSort.sort(..)") + " ms");
        System.out.println("Function BubbleSort ran " + callCounts.get("BubbleSort.sort(..)") + " times and took in total " + executionTimes.get("BubbleSort.sort(..)") + " ms");
        System.out.println("Function InsertionSort ran " + callCounts.get("InsertionSort.sort(..)") + " times and took in total " + executionTimes.get("InsertionSort.sort(..)") + " ms");

    }
}

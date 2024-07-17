package sortFiles;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
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
        // No action required before method execution
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


    @After("execution(* AlgorithmRunner.runAlgorithms(..))")
    public void afterAllSortMethods() {
        System.out.println("Total time of running all sort functions was " + totalExecutionTime + " ms");
        System.out.println("In detail:");
        for (String methodName : callCounts.keySet()) {
            System.out.println("Function " + methodName + " ran " + callCounts.get(methodName) + " times and took in total " + executionTimes.get(methodName) + " ms");
        }
    }
}

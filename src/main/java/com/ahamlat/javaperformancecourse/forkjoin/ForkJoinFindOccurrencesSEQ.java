package com.ahamlat.javaperformancecourse.forkjoin;

public class ForkJoinFindOccurrencesSEQ extends ForkJoinFindOccurences {

    public static void main(final String[] args) {
        long responseTimeSum = 0L;
        long responseTime = 0L;
        System.out.println("Number of list elements: "
                + WORDS_LIST.size());
        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
            responseTime = testCase();
            responseTimeSum +=  responseTime;
        }
        System.out.println(String.format("La moyenne est : %d ms",responseTimeSum / NUMBER_OF_ITERATIONS ));

    }

    private static long testCase() {
        long start = System.nanoTime();
        int length = WORDS_LIST.size();
        int numOfOcc = 0;
        for (int i=0; i < length; i++) {
            if (TO_FIND.equalsIgnoreCase(WORDS_LIST.get(i))) {
                numOfOcc++;
            }
        }
        long responseTime = (System.nanoTime() - start)/1_000_000;
        System.out.println(String.format("**** Temps d'exécution : %d ms", responseTime));

        System.out.println(" Total number of occurrences of the element "
                + TO_FIND + ": " + numOfOcc);
        return responseTime;
    }
}

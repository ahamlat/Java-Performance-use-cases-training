package com.ahamlat.javaperformancecourse.strings;

public class StringBufferCase{

    private static int NUMBER_OF_ITERATIONS = 100;


    public static void main(String[] args) {
        long responseTimeSum = 0;
        long responseTime = 0;
        for (int i = 1; i <= NUMBER_OF_ITERATIONS; i++) {
            System.out.println(String.format("**** Exécution %d *****", i));
            responseTime = testCase();
            responseTimeSum +=  responseTime;
        }
        System.out.println(String.format("La moyenne est : %d ms",responseTimeSum / NUMBER_OF_ITERATIONS ));
    }



    private static long testCase() {
        long start = System.nanoTime();
        StringBuffer chaine = new StringBuffer();
        for (int i = 0; i < 1_000_000; i++) {
            chaine.append(i);
        }
        long responseTime = (System.nanoTime() - start)/1_000_000;
        System.out.println(String.format("**** Temps d'exécution : %d ms", responseTime));
        return responseTime;
    }
}

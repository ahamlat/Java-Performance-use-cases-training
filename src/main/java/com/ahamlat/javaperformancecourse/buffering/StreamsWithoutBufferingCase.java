package com.ahamlat.javaperformancecourse.buffering;

import java.io.*;

public class StreamsWithoutBufferingCase {

    private static int NUMBER_OF_ITERATIONS = 100;

    public static void main(String[] args) throws IOException {
        long responseTimeSum = 0L;
        long responseTime = 0L;
        for (int i = 1; i <= NUMBER_OF_ITERATIONS; i++) {
            System.out.println(String.format("**** Exécution %d *****", i));
            responseTime = testCase("dataset.dat");
            responseTimeSum +=  responseTime;
        }
        System.out.println(String.format("La moyenne est : %d ms",responseTimeSum / NUMBER_OF_ITERATIONS ));
    }

    private static long testCase(String dataset) throws IOException {
        long startTime = System.currentTimeMillis();
        try (InputStream inputStream = new FileInputStream(dataset);
            OutputStream outputStream = new FileOutputStream("new"+dataset)) {
            int data = inputStream.read();
            while (data != -1) {
                int out = transformer(data);
                outputStream.write(out);
                data = inputStream.read();
            }
        }

        long responseTime = System.currentTimeMillis() - startTime;
        System.out.println("Temps de réponse : " + responseTime);
        return responseTime;
    }

    private static int transformer(int data) {
        return Character.isLetter(data) ? data ^ ' ' : data ;
    }
}

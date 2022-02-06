package com.ahamlat.javaperformancecourse.buffering;

import java.io.*;

public class BufferedInputAndOutputStreamsCase {

    private static int NUMBER_OF_ITERATIONS = 100;

    public static void main(String[] args) throws IOException {

        long responseTimeSum = 0;
        long responseTime = 0;
        for (int i = 1; i <= NUMBER_OF_ITERATIONS; i++) {
            System.out.println(String.format("**** Exécution %d *****", i));
            responseTime = testCase("dataset.dat");
            responseTimeSum +=  responseTime;
        }
        System.out.println(String.format("La moyenne est : %d ms",responseTimeSum / NUMBER_OF_ITERATIONS ));
    }

    private static long testCase(String dataset)  {
        long startTime = System.currentTimeMillis();
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(dataset));
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream("new"+dataset))) {
            int data = inputStream.read();
            while (data != -1) {
                int out = transformer(data);
                outputStream.write(out);
                data = inputStream.read();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        long responseTime = System.currentTimeMillis() - startTime;
        System.out.println("Temps de réponse :  " + responseTime);
        return responseTime;

    }

    private static int transformer(int data) {
        return Character.isLetter(data) ? data ^ ' ' : data ;
    }
}

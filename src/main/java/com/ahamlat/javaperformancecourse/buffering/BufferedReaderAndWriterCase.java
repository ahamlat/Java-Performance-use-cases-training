package com.ahamlat.javaperformancecourse.buffering;

import java.io.*;

public class BufferedReaderAndWriterCase {

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

    private static long testCase(String dataset) {
        long startTime = System.currentTimeMillis();
        try (BufferedReader inputStream = new BufferedReader(new FileReader(dataset));
             BufferedWriter outputStream = new BufferedWriter(new FileWriter("new"+dataset)))
        {
            String data = inputStream.readLine();
            while (data != null) {
                char[] lineTransformed = new char[data.length()];
                for (int i=0; i<data.length(); i++) {
                    int character = data.charAt(i);
                    int out = transformer(character);
                    lineTransformed[i] = (char) out;
                }
                outputStream.write(lineTransformed);
                outputStream.newLine();
                data = inputStream.readLine();
            }
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
        long responseTime = System.currentTimeMillis() - startTime;
        System.out.println("Temps de réponse : " + responseTime);
        return responseTime;

    }

    private static int transformer(int data) {
        return Character.isLetter(data) ? data ^ ' ' : data ;
    }
}

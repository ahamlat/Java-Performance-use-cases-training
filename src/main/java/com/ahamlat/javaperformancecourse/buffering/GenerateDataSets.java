package com.ahamlat.javaperformancecourse.buffering;

import org.fluttercode.datafactory.impl.DataFactory;

import java.io.*;

public class GenerateDataSets {

    public static void main(String[] args) throws  IOException {
        generateDataSet("dataset.dat");
    }

    private static void generateDataSet(String datasetName) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(datasetName));

        DataFactory df = new DataFactory();
        for (int i = 0; i < 10_000; i++) {
            String name = df.getFirstName() + " "+ df.getLastName();
            bw.write(name);
            bw.newLine();
        }
        bw.close();
    }
}

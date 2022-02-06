package com.ahamlat.javaperformancecourse.synchronization;

import org.fluttercode.datafactory.impl.DataFactory;

import java.util.Hashtable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MapMultiThreadsTestCase {

    public final static int THREAD_POOL_SIZE = 30;

    public static void main(String[] args) throws InterruptedException {

        System.out.println("**** Implementation Test Case with HashMap and Synchronization *****");
        ListOfEmployees listOfEmployeesMutex = new ListOfEmployeesMutex();
        testCase(listOfEmployeesMutex);

        System.out.println("**** Implementation Test Case with SynchronizedMap *****");
        ListOfEmployees listOfEmployeesSynchronizedMap = new ListOfEmployeesSynchronizedMap();
        testCase(listOfEmployeesSynchronizedMap);

        System.out.println("**** Implementation Test Case with ConcurrentHashMap *****");
        ListOfEmployees listOfEmployeesConcurrentHashMap = new ListOfEmployeesConcurrentHashMap();
        testCase(listOfEmployeesConcurrentHashMap);

    }

    private static void testCase(ListOfEmployees map) throws InterruptedException {
        DataFactory df = new DataFactory();
        for (int i = 0; i < 10; i++) {
            ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
            long startTime = System.nanoTime();
            for (int j = 0; j < THREAD_POOL_SIZE; j++) {
                executorService.execute(() -> {

                    for (int i1 = 0; i1 < 50_000; i1++) {
                        Integer randomNumber1 = (int) Math.ceil(Math.random() * 50_000);
                        Integer randomNumber2 = (int) Math.ceil(Math.random() * 50_000);

                        // Ajout d'un employé
                        map.addEmployee(randomNumber1, new Employee(randomNumber1, df.getFirstName() + " " + df.getLastName()));

                        // Récupération d'un employé
                        map.getEmployee(randomNumber2);
                    }
                });
            }

            executorService.shutdown();

            // Bloque jusqu'à ce que tous les threads se sont terminés ou le timeout est atteint
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

            long entTime = System.nanoTime();
            long totalTime = (entTime - startTime) / 1000000L;
            System.out.println("500K Employees added/retrieved in " + totalTime + " ms");

        }

    }
}

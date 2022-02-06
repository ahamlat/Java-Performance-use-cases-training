package com.ahamlat.javaperformancecourse.completablefuture;

import com.ahamlat.javaperformancecourse.Tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class CryptoWallet {

    private String id;
    private ConcurrentHashMap<String, Double> currencies;
    private static int NUMBER_OF_THREADS = 100;
    private static String DEST_CURR = "EUR";


    public CryptoWallet(String id, ConcurrentHashMap currencies) {
        this.id = id;
        this.currencies = currencies;
    }


    public void addCurrency(String currency, double amount) {

        if (currencies.containsKey(currency))
            this.currencies.put(currency, ((double) currencies.get(currency)) + amount);
        else
            currencies.put(currency, amount);
    }



    public double getValueSequantially() {

        Double result =
                currencies.entrySet().stream()
                        .map(entry -> getCryptoCurrencyValue(entry.getKey(), entry.getValue()))
                        .reduce(0.0, (a, b) -> a + b);
        return result;
    }

    public double getValueWithParallelStream() {

        Double result =
                currencies.entrySet().parallelStream()
                        .map(entry -> getCryptoCurrencyValue(entry.getKey(), entry.getValue()))
                        .reduce(0.0, (a, b) -> a + b);
        return result;
    }

    public double getValueWithCompletableFutures() {
        List<CompletableFuture<Double>> futures =
                currencies.entrySet().stream()
                        .map(entry -> CompletableFuture.supplyAsync(() -> getCryptoCurrencyValue(entry.getKey(), entry.getValue())))
                        .collect(Collectors.toList());
        // du code JAVA

        Double result = futures.stream()
                .map(CompletableFuture::join)
                .reduce(0.0, (a, b) -> a + b);
        return result;
    }

    public double getValueWithCompetableFuturesAndExecutor() {

        ExecutorService executor = Executors.newFixedThreadPool(Math.min(currencies.entrySet().size(), NUMBER_OF_THREADS));
        List<CompletableFuture<Double>> futures =
                currencies.entrySet().stream()
                        .map(entry -> CompletableFuture.supplyAsync(() -> getCryptoCurrencyValue(entry.getKey(), entry.getValue()), executor))
                        .collect(Collectors.toList());
        // du code JAVA

        Double result = futures.stream()
                .map(CompletableFuture::join)
                .reduce(0.0, (a, b) -> a + b);
        executor.shutdown();
        return result;
    }

    public void executeWithCompetableFuturesAndExecutor() {
        long start = System.nanoTime();
        ExecutorService ioBoundExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        CompletableFuture<Void>[] futures =
                currencies.entrySet().stream()
                        .map(entry -> CompletableFuture.supplyAsync(() -> getCryptoCurrencyValue(entry.getKey(), entry.getValue()), ioBoundExecutor))
                        .map(f -> f.thenAccept(
                                s -> System.out.println(Thread.currentThread().getName()+ " " +s + " done in "  +
                                        ((System.nanoTime() - start) / 1_000_000) + " msecs)"))) .toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(futures).join();
        System.out.println("All Competablefures have now responded in "
                + ((System.nanoTime() - start) / 1_000_000) + " msecs");

        ioBoundExecutor.shutdown();
    }

    public double getValueSequantiallyJava7() {

        Double result = 0d;
        for ( Map.Entry<String, Double> entry : currencies.entrySet()) {
            result += getCryptoCurrencyValue (entry.getKey(), entry.getValue());
        }
        return result;
    }

    public double getValueWithFutureAndExecutor() throws ExecutionException, InterruptedException {
        ExecutorService ioBoundExecutor = Executors.newFixedThreadPool(Math.min(currencies.entrySet().size(), NUMBER_OF_THREADS));
        List<Future<Double>> list = new ArrayList<>();

        for ( Map.Entry<String, Double> entry : currencies.entrySet()) {
            Future<Double> future = ioBoundExecutor.submit(new Callable<Double>() {
                @Override
                public Double call() throws Exception {
                    return getCryptoCurrencyValue(entry.getKey(), entry.getValue());
                }
            });
            list.add(future);
        }
        // du code JAVA
        Double globalResult = 0d;
        for (Future<Double> element:list) {
            Double result = element.get();
            globalResult += result;
        }
        ioBoundExecutor.shutdown();

        return globalResult;
    }

    public double getValueWithCompletionService () throws Exception {
        final ExecutorService pool = Executors.newFixedThreadPool(Math.min(currencies.entrySet().size(), NUMBER_OF_THREADS));
        final ExecutorCompletionService<Double> completionService = new ExecutorCompletionService<>(pool);
        for ( Map.Entry<String, Double> entry : currencies.entrySet()) {
            completionService.submit(new Callable<Double>() {
                @Override
                public Double call() throws Exception {
                    return getCryptoCurrencyValue(entry.getKey(), entry.getValue());
                }
            });
        }
        // du code JAVA
        double resultat = 0d;
        for (int i = 0; i < currencies.entrySet().size(); i++) {
            try {
                final Future<Double> future = completionService.take();
                final Double resItem = future.get();
                resultat += resItem;

            }  catch (InterruptedException ex2) {
                Thread.currentThread().interrupt();
            } catch (ExecutionException ex1) {
                throw new Exception(ex1.getCause());
            }

        }
        pool.shutdown();
        return resultat;

    }


    private Double getCryptoCurrencyValue(String sourceCurr, Double amount) {
        Double price = Tools.getConversion (sourceCurr, DEST_CURR, amount);
        System.out.println(Thread.currentThread().getName() + " - GetValue of " + id + ", amount = " + amount+", result = "+price);
        return price;
    }


}

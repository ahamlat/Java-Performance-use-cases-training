package com.ahamlat.javaperformancecourse.completablefuture;

import java.util.concurrent.ConcurrentHashMap;

public class CompletableFutureUseCase {

    public static void main(String[] args) {
        ConcurrentHashMap currencies = new ConcurrentHashMap<String, Double>();
        currencies.put("BTC", 1d);
        currencies.put("ETH", 10d);
        currencies.put("LTC", 9.997d);
        currencies.put("XRP", 1000d);
        currencies.put("FTM", 15_000d);
        currencies.put("INT", 60_500d);
        currencies.put("RVN", 9_874d);
        currencies.put("QKC", 18_493d);
        currencies.put("MIOTA", 399d);
        currencies.put("BAT", 485d);
        currencies.put("EOS", 50d);
        currencies.put("LEND", 199.8d);
        currencies.put("RLC", 99.9d);
        currencies.put("LINK", 9.99d);
        currencies.put("ENJ", 499.5d);
        currencies.put("STEEM", 100d);
        currencies.put("LOOM", 499.5d);
        currencies.put("NEO", 14d);
        currencies.put("UOS", 903d);
        currencies.put("BNB", 4d);
        currencies.put("SATT", 26000d);


        CryptoWallet wallet = new CryptoWallet("MY_WALLET_ID", currencies);

     //   long start1 = System.nanoTime();
     //   System.out.println("[SEQUENTIAL, WITH STREAM] Wallet value = "+wallet.getValueSequantially());
     //   System.out.println("[SEQUENTIAL, WITH STREAM] le temps passé est "+(System.nanoTime()-start1) / 1_000_000 + " ms");

     //   long start4 = System.nanoTime();
     //   System.out.println("[WITH PARALLELSTREAM] Wallet value = "+wallet.getValueWithParallelStream());
     //   System.out.println("[WITH PARALLELSTREAM] Time spent is "+(System.nanoTime()-start4) / 1_000_000 + " ms");

     //   long start2 = System.nanoTime();
     //   System.out.println("[WITH COMPLETABLEFUTURES] Wallet value = "+wallet.getValueWithCompletableFutures());
     //   System.out.println("[WITH COMPLETABLEFUTURES] Time spent is "+(System.nanoTime()-start2) / 1_000_000 + " ms");

        long start3 = System.nanoTime();
        System.out.println("[WITH COMPLETABLEFUTURES AND EXECUTOR] Wallet value = "+wallet.getValueWithCompetableFuturesAndExecutor());
        System.out.println("[WITH COMPLETABLEFUTURES AND EXECUTOR] Time spent is "+(System.nanoTime()-start3) / 1_000_000 + " ms");

    }
}

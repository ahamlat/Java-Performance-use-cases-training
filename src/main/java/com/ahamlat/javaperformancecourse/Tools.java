package com.ahamlat.javaperformancecourse;

import com.ahamlat.javaperformancecourse.completablefuture.HttpClientSingleton;
import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;

public class Tools {

    private final static String API_KEY = "aefa05b2-8451-4671-8936-95e703e1100f";
    private static String URI_CONVERSION = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest";

    public static List<String> initList() throws Exception{
        URI uri = ClassLoader.getSystemResource("Shakespeare_text.txt").toURI();
        CharSequence bytes =
                new String(Files.readAllBytes(Paths.get(uri)));

        List<String> mots = Pattern
                .compile("\\s+")
                .splitAsStream(bytes)
                .filter(((Predicate<String>) String::isEmpty).negate())
                .collect(toList());

        return Collections.synchronizedList(mots);
    }

    public static double getConversion(String from, String to, Double amount) {
        OkHttpClient client = HttpClientSingleton.getInstance();
        Request request = buildRequest(from, to);
        double price = 0d;
        try (Response response = client.newCall(request).execute()) {
            price = getPrice(from, to, response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return price * amount;
    }

    private static Request buildRequest(String from, String to) {
        HttpUrl.Builder urlBuilder
                = HttpUrl.parse(URI_CONVERSION).newBuilder();
        urlBuilder.addQueryParameter("symbol", from);
        urlBuilder.addQueryParameter("convert", to);
        String url = urlBuilder.build().toString();

        return new Request.Builder()
                .url(url)
                .addHeader("Accept","application/json")
                .addHeader("X-CMC_PRO_API_KEY", API_KEY)
                .build();
    }

    private static double getPrice(String from, String to, String response) throws IOException {
        JSONObject responseObject = new JSONObject(response);
        JSONObject dataObject = responseObject.getJSONObject("data");
        JSONObject fromObjet = dataObject.getJSONObject(from);
        JSONObject quote = fromObjet.getJSONObject("quote");
        JSONObject destObject = quote.getJSONObject(to);
        return (Double) destObject.get("price");
    }
}

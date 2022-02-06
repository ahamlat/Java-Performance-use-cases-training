package com.ahamlat.javaperformancecourse.completablefuture;

import okhttp3.*;

public class HttpClientSingleton
{
    /** Constructeur privé */
    private HttpClientSingleton()
    {}

    /** Holder */
    private static class SingletonHolder
    {
        /** Instance unique non préinitialisée */
        private final static OkHttpClient instance = new OkHttpClient();
    }

    /** Point d'accès pour l'instance unique du singleton */
    public static OkHttpClient getInstance()
    {
        return SingletonHolder.instance;
    }
}
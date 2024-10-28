package com.example.papasoftclient.utils;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

public class HttpClient {
    private static CloseableHttpClient client;

    public static CloseableHttpClient getClient() {
        if (client == null) client = HttpClients.createDefault();
        return client;
    }
}

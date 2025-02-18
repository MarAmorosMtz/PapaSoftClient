package com.example.papasoftclient.repositories;


import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.io.File;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BackupsRepository {

    private HttpClient client;
    private String host;

    public BackupsRepository(){
        this.client = HttpClient.newHttpClient();
        this.host = RestAPI.RESTORE_ENDPOINT;
    }

    public void backup(){
    }

    public boolean restore(File backupFile) {
        try {

            HttpRequest.BodyPublisher fileBody = HttpRequest.BodyPublishers.ofFile(backupFile.toPath());

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(host))
                    .header("Content-Type", "multipart/form-data")
                    .POST(fileBody)
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.statusCode() == 200;
        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
            return false;
        }
    }

}

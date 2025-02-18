package com.example.papasoftclient.repositories;

import com.example.papasoftclient.models.LoginModel;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LoginRepository {
    private HttpClient client;
    private ObjectMapper mapper;
    private String host;

    public LoginRepository() {
        this.host = RestAPI.LOGIN_ENDPOINT;
        this.client = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
    }

    public boolean login(LoginModel loginModel) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(host))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(loginModel)))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode() == 200;
        } catch (URISyntaxException | IOException | InterruptedException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}

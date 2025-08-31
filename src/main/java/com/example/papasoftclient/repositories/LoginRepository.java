package com.example.papasoftclient.repositories;

import com.example.papasoftclient.models.LoginModel;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.net.ConnectException;
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
        this.client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();
        this.mapper = new ObjectMapper();
    }

    public int login(LoginModel loginModel) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .uri(new URI(host))
                    .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(loginModel)))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.statusCode();
        } catch (ConnectException cne){
            return 404;
        }
        catch (URISyntaxException | IOException | InterruptedException ex) {
            return 500;
        }
    }
}

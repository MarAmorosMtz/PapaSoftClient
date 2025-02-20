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
        this.client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();
        this.mapper = new ObjectMapper();
    }

    public boolean login(LoginModel loginModel) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .uri(new URI(host))
                    .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(loginModel)))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            /*if (response.statusCode() == 200) {
                JsonNode jsonResponse = mapper.readTree(response.body());
                return jsonResponse.get("success").asBoolean();
            }*/
            return response.statusCode() == 200;
        } catch (URISyntaxException | IOException | InterruptedException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}

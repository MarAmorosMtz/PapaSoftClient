package com.example.papasoftclient.repositories;

import com.example.papasoftclient.models.LoginModel;
import com.example.papasoftclient.utils.HttpClient;
import com.example.papasoftclient.utils.JsonMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.io.entity.StringEntity;

public class LoginRepository {
    private CloseableHttpClient httpClient;
    private ObjectMapper mapper;
    private String host;

    public LoginRepository() {
        this.host = RestAPI.LOGIN_ENDPOINT;
        this.httpClient = HttpClient.getClient();
        this.mapper = JsonMapper.getMapper();
    }

    public boolean login(LoginModel loginModel) {
        try{
            HttpPost request = new HttpPost(host);
            request.setHeader("Content-Type", "application/json");
            request.setEntity(new StringEntity(mapper.writeValueAsString(loginModel)));
            CloseableHttpResponse response = httpClient.execute(request);
            if (response.getCode() == 200) return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
        return false;
    }
}

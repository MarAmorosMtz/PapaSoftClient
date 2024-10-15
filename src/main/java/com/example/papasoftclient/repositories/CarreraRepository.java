package com.example.papasoftclient.repositories;


import com.example.papasoftclient.models.CarreraBase;
import com.example.papasoftclient.models.CarreraModel;
import com.example.papasoftclient.models.CarreraPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.util.UUID;

public class CarreraRepository implements Repository<CarreraBase, CarreraModel>{

    private CloseableHttpClient httpClient;
    private ObjectMapper mapper;
    private String host;

    public CarreraRepository(CloseableHttpClient httpClient, ObjectMapper mapper, String host) {
        this.httpClient = httpClient;
        this.mapper = mapper;
        this.host = host;
    }

    @Override
    public CarreraPage search(int page) {
        try{
            HttpGet request = new HttpGet(host+"?pagina="+page);
            CarreraPage carreraPage = httpClient.execute(request,response->{
                if (response.getCode() != 200) return null;
                return mapper.readValue(EntityUtils.toString(response.getEntity()),CarreraPage.class);
            });

        }catch(Exception e){
            return null;
        }
        return null;
    }

    @Override
    public CarreraModel search(UUID id) {
        try{
            HttpGet request = new HttpGet(host+id.toString());
            CarreraModel carrera = httpClient.execute(request,response->{
                if (response.getCode() == 200) return null;
                return mapper.readValue(EntityUtils.toString(response.getEntity()),CarreraModel.class);
            });
        }catch (Exception ex){
            return null;
        }
        return null;
    }

    @Override
    public UUID save(CarreraBase item) {
        try{
            HttpPost request = new HttpPost(host);
            request.setHeader("Content-Type", "application/json");
            request.setEntity(new StringEntity(mapper.writeValueAsString(item)));
            CloseableHttpResponse response = httpClient.execute(request);
            CarreraModel carrera = mapper.readValue(EntityUtils.toString(response.getEntity()),CarreraModel.class);
            if (response.getCode() == 201) return carrera.getId();
        }catch (Exception ex) {
            return null;
        }
        return null;
    }

    @Override
    public boolean update(UUID id,CarreraBase item) {
        try{
            HttpPut request = new HttpPut(host+id.toString());
            request.setHeader("Content-Type", "application/json");
            request.setEntity(new StringEntity(mapper.writeValueAsString(item)));
            CloseableHttpResponse response = httpClient.execute(request);
            CarreraModel carrera = mapper.readValue(EntityUtils.toString(response.getEntity()),CarreraModel.class);
            if (response.getCode() == 200) return true;
        }catch (Exception ex) {
            return false;
        }
        return false;
    }

    @Override
    public boolean remove(UUID id) {
        try{
            HttpDelete request = new HttpDelete(host+id.toString());
            CloseableHttpResponse response = httpClient.execute(request);
            if(response.getCode() == 204) return true;
        }catch (Exception ex){
            return false;
        }
        return false;
    }


}

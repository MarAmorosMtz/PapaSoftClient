package com.example.papasoftclient.repositories;

import com.example.papasoftclient.models.MaestroBase;
import com.example.papasoftclient.models.MaestroModel;
import com.example.papasoftclient.models.MaestroPage;
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

public class MaestroRepository implements Repository<MaestroBase, MaestroModel>{

    private CloseableHttpClient httpClient;
    private ObjectMapper mapper;
    private String host;

    public MaestroRepository(CloseableHttpClient httpClient, ObjectMapper mapper, String host) {
        this.httpClient = httpClient;
        this.mapper = mapper;
        this.host = host;
    }

    @Override
    public MaestroPage search(int page) {
        try{
            HttpGet request = new HttpGet(host+"?pagina="+page);
            MaestroPage maestroPage = httpClient.execute(request,response->{
                if (response.getCode() != 200) return null;
                return mapper.readValue(EntityUtils.toString(response.getEntity()),MaestroPage.class);
            });
            return maestroPage;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public MaestroModel search(UUID id) {
        try{
            HttpGet request = new HttpGet(host+id.toString());
            MaestroModel maestro = httpClient.execute(request,response->{
                if (response.getCode() == 200) return null;
                return mapper.readValue(EntityUtils.toString(response.getEntity()),MaestroModel.class);
            });
        }catch (Exception ex){
            return null;
        }
        return null;
    }

    @Override
    public UUID save(MaestroBase item) {
        try{
            HttpPost request = new HttpPost(host);
            request.setHeader("Content-Type", "application/json");
            request.setEntity(new StringEntity(mapper.writeValueAsString(item)));
            CloseableHttpResponse response = httpClient.execute(request);
            MaestroModel carrera = mapper.readValue(EntityUtils.toString(response.getEntity()),MaestroModel.class);
            if (response.getCode() == 201) return carrera.getId();
        }catch (Exception ex) {
            return null;
        }
        return null;
    }

    @Override
    public boolean update(UUID id,MaestroBase item) {
        try{
            HttpPut request = new HttpPut(host+id.toString());
            request.setHeader("Content-Type", "application/json");
            request.setEntity(new StringEntity(mapper.writeValueAsString(item)));
            CloseableHttpResponse response = httpClient.execute(request);
            MaestroModel carrera = mapper.readValue(EntityUtils.toString(response.getEntity()),MaestroModel.class);
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

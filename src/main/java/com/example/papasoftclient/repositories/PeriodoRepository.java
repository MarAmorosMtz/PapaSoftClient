package com.example.papasoftclient.repositories;


import com.example.papasoftclient.models.PeriodoBase;
import com.example.papasoftclient.models.PeriodoModel;
import com.example.papasoftclient.models.PeriodoPage;
import com.example.papasoftclient.utils.HttpClient;
import com.example.papasoftclient.utils.JsonMapper;
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

public class PeriodoRepository implements Repository<PeriodoBase, PeriodoModel>{

    private CloseableHttpClient httpClient;
    private ObjectMapper mapper;
    private String host;

    public PeriodoRepository() {
        this.httpClient = HttpClient.getClient();
        this.mapper = JsonMapper.getMapper();
        this.host = RestAPI.PERIODOS_ENDPOINT;
    }

    @Override
    public PeriodoPage search(int page) {
        PeriodoPage periodoPage;
        try{
            HttpGet request = new HttpGet(host+"?pagina="+page);
            periodoPage = httpClient.execute(request,response->{
                if (response.getCode() != 200) return null;
                return mapper.readValue(EntityUtils.toString(response.getEntity()),PeriodoPage.class);
            });

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Se capturó una excepción");
            return null;
        }
        return periodoPage;
    }

    @Override
    public PeriodoModel search(UUID id) {
        try{
            HttpGet request = new HttpGet(host+id.toString());
            PeriodoModel periodoModel = httpClient.execute(request,response->{
                if (response.getCode() != 200) return null;
                return mapper.readValue(EntityUtils.toString(response.getEntity()),PeriodoModel.class);
            });
            return periodoModel;
        }catch (Exception ex){
            return null;
        }
    }

    @Override
    public UUID save(PeriodoBase item) {
        try{
            HttpPost request = new HttpPost(host);
            request.setHeader("Content-Type", "application/json");
            request.setEntity(new StringEntity(mapper.writeValueAsString(item)));
            CloseableHttpResponse response = httpClient.execute(request);
            PeriodoModel periodo = mapper.readValue(EntityUtils.toString(response.getEntity()),PeriodoModel.class);
            if (response.getCode() == 201) return periodo.getId();
        }catch (Exception ex) {
            return null;
        }
        return null;
    }

    @Override
    public boolean update(UUID id,PeriodoBase item) {
        try{
            HttpPut request = new HttpPut(host+id.toString());
            request.setHeader("Content-Type", "application/json");
            request.setEntity(new StringEntity(mapper.writeValueAsString(item)));
            CloseableHttpResponse response = httpClient.execute(request);
            PeriodoModel periodo = mapper.readValue(EntityUtils.toString(response.getEntity()),PeriodoModel.class);
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
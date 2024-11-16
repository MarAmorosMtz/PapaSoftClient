package com.example.papasoftclient.repositories;


import com.example.papasoftclient.models.MateriaBase;
import com.example.papasoftclient.models.MateriaModel;
import com.example.papasoftclient.models.MateriaPage;
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

public class MateriaRepository implements Repository<MateriaBase, MateriaModel>{

    private CloseableHttpClient httpClient;
    private ObjectMapper mapper;
    private String host;

    public MateriaRepository() {
        this.httpClient = HttpClient.getClient();
        this.mapper = JsonMapper.getMapper();
        this.host = RestAPI.MATERIAS_ENDPOINT;
    }

    @Override
    public MateriaPage search(int page) {
        try{
            HttpGet request = new HttpGet(host+"?pagina="+page);
            MateriaPage materiaPage = httpClient.execute(request,response->{
                if (response.getCode() != 200) return null;
                return mapper.readValue(EntityUtils.toString(response.getEntity()),MateriaPage.class);
            });
            return materiaPage;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public MateriaModel search(UUID id) {
        try{
            HttpGet request = new HttpGet(host+id.toString());
            MateriaModel materia = httpClient.execute(request,response->{
                if (response.getCode() == 200) return null;
                return mapper.readValue(EntityUtils.toString(response.getEntity()),MateriaModel.class);
            });
        }catch (Exception ex){
            return null;
        }
        return null;
    }

    @Override
    public UUID save(MateriaBase item) {
        try{
            HttpPost request = new HttpPost(host);
            request.setHeader("Content-Type", "application/json");
            request.setEntity(new StringEntity(mapper.writeValueAsString(item)));
            CloseableHttpResponse response = httpClient.execute(request);
            MateriaModel carrera = mapper.readValue(EntityUtils.toString(response.getEntity()),MateriaModel.class);
            if (response.getCode() == 201) return carrera.getId();
        }catch (Exception ex) {
            return null;
        }
        return null;
    }

    @Override
    public boolean update(UUID id,MateriaBase item) {
        try{
            HttpPut request = new HttpPut(host+id.toString());
            request.setHeader("Content-Type", "application/json");
            request.setEntity(new StringEntity(mapper.writeValueAsString(item)));
            CloseableHttpResponse response = httpClient.execute(request);
            MateriaModel carrera = mapper.readValue(EntityUtils.toString(response.getEntity()),MateriaModel.class);
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

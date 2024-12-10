package com.example.papasoftclient.repositories;

import com.example.papasoftclient.models.*;
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

public class AsesoriaRepository implements Repository<AsesoriaBase, AsesoriaModel>{

    private CloseableHttpClient httpClient;
    private ObjectMapper mapper;
    private String host;

    public AsesoriaRepository() {
        this.httpClient = HttpClient.getClient();
        this.mapper = JsonMapper.getMapper();
        this.host = RestAPI.ASESORIAS_ENDPOINT;
    }

    @Override
    public AsesoriaPage search(int page) {
        try{
            HttpGet request = new HttpGet(host+"?pagina="+page);
            AsesoriaPage asesoriaPage = httpClient.execute(request,response->{
                if (response.getCode() != 200) return null;
                return mapper.readValue(EntityUtils.toString(response.getEntity()),AsesoriaPage.class);
            });
            return asesoriaPage;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public AsesoriaModel search(UUID id) {
        try{
            HttpGet request = new HttpGet(host+id.toString());
            AsesoriaModel asesoriaModel = httpClient.execute(request,response->{
                if (response.getCode() != 200) return null;
                return mapper.readValue(EntityUtils.toString(response.getEntity()),AsesoriaModel.class);
            });
            return asesoriaModel;
        }catch (Exception ex){
            return null;
        }
    }

    @Override
    public UUID save(AsesoriaBase item) {
        try{
            HttpPost request = new HttpPost(host);
            request.setHeader("Content-Type", "application/json");
            request.setEntity(new StringEntity(mapper.writeValueAsString(item)));
            CloseableHttpResponse response = httpClient.execute(request);
            System.out.println(EntityUtils.toString(response.getEntity()));
            AsesoriaModel asesoriaModel = mapper.readValue(EntityUtils.toString(response.getEntity()),AsesoriaModel.class);
            if (response.getCode() == 201) return asesoriaModel.getId();
        }catch (Exception ex) {
            return null;
        }
        return null;
    }

    @Override
    public boolean update(UUID id,AsesoriaBase item) {
        try{
            HttpPut request = new HttpPut(host+id.toString());
            request.setHeader("Content-Type", "application/json");
            request.setEntity(new StringEntity(mapper.writeValueAsString(item)));
            CloseableHttpResponse response = httpClient.execute(request);
            AsesoriaModel asesoriaModel = mapper.readValue(EntityUtils.toString(response.getEntity()),AsesoriaModel.class);
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

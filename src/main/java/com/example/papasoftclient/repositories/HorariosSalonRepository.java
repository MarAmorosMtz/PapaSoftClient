package com.example.papasoftclient.repositories;

import com.example.papasoftclient.models.*;
import com.example.papasoftclient.utils.HttpClient;
import com.example.papasoftclient.utils.JsonMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.util.UUID;

public class HorariosSalonRepository {

    private CloseableHttpClient httpClient;
    private ObjectMapper mapper;
    private String host;

    public HorariosSalonRepository() {
        this.httpClient = HttpClient.getClient();
        this.mapper = JsonMapper.getMapper();
        this.host = RestAPI.HORARIOS_SALONES_ENDPOINT;
    }

    public HorarioSalonPage all(UUID salon,UUID periodo) {
        HorarioSalonPage horarioPage;
        try{
            HttpGet request = new HttpGet(host+"?salon="+salon.toString()+"&periodo="+periodo.toString());
            horarioPage = httpClient.execute(request,response->{
                if (response.getCode() != 200) return null;
                return mapper.readValue(EntityUtils.toString(response.getEntity()),HorarioSalonPage.class);
            });

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return horarioPage;
    }

    public HorarioSalonModel get(UUID id) {
        try{
            HttpGet request = new HttpGet(host+id.toString());
            HorarioSalonModel horario = httpClient.execute(request,response->{
                if (response.getCode() != 200) return null;
                return mapper.readValue(EntityUtils.toString(response.getEntity()),HorarioSalonModel.class);
            });
            return horario;
        }catch (Exception ex){
            return null;
        }
    }

    public UUID save(HorarioSalonBase item) {
        try{
            HttpPost request = new HttpPost(host);
            request.setHeader("Content-Type", "application/json");
            request.setEntity(new StringEntity(mapper.writeValueAsString(item)));
            CloseableHttpResponse response = httpClient.execute(request);
            HorarioSalonModel horario = mapper.readValue(EntityUtils.toString(response.getEntity()),HorarioSalonModel.class);
            if (response.getCode() == 201) return horario.getId();
        }catch (Exception ex) {
            return null;
        }
        return null;
    }

    public boolean update(UUID id, HorarioBase item) {
        try{
            HttpPut request = new HttpPut(host+id.toString());
            request.setHeader("Content-Type", "application/json");
            request.setEntity(new StringEntity(mapper.writeValueAsString(item)));
            CloseableHttpResponse response = httpClient.execute(request);
            HorarioSalonModel horario = mapper.readValue(EntityUtils.toString(response.getEntity()),HorarioSalonModel.class);
            if (response.getCode() == 200) return true;
        }catch (Exception ex) {
            return false;
        }
        return false;
    }

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

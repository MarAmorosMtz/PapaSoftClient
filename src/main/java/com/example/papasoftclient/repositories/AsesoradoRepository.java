package com.example.papasoftclient.repositories;

import com.example.papasoftclient.models.AsesoradoBase;
import com.example.papasoftclient.models.AsesoradoModel;
import com.example.papasoftclient.models.AsesoradoPage;
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

public class AsesoradoRepository implements Repository<AsesoradoBase,AsesoradoModel>{

    private CloseableHttpClient httpClient;
    private ObjectMapper mapper;
    private String host;

    public AsesoradoRepository() {
        this.httpClient = HttpClient.getClient();
        this.mapper = JsonMapper.getMapper();
        this.host = RestAPI.ASESORADOS_ENDPOINT;
    }

    @Override
    public AsesoradoPage search(int page) {
        try{
            HttpGet request = new HttpGet(host+"?pagina="+page);
            AsesoradoPage asesoradoPage = httpClient.execute(request,response->{
                if (response.getCode() != 200) return null;
                return mapper.readValue(EntityUtils.toString(response.getEntity()),AsesoradoPage.class);
            });
            return asesoradoPage;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public AsesoradoModel search(UUID id) {
        try{
            HttpGet request = new HttpGet(host+id.toString());
            AsesoradoModel asesorado = httpClient.execute(request,response->{
                if (response.getCode() != 200) return null;
                return mapper.readValue(EntityUtils.toString(response.getEntity()),AsesoradoModel.class);
            });
            return asesorado;
        }catch (Exception ex){
            return null;
        }
    }

    @Override
    public UUID save(AsesoradoBase item) {
        try{
            HttpPost request = new HttpPost(host);
            request.setHeader("Content-Type", "application/json");
            request.setEntity(new StringEntity(mapper.writeValueAsString(item)));
            System.out.println(mapper.writeValueAsString(item));
            CloseableHttpResponse response = httpClient.execute(request);
            AsesoradoModel asesoradoModel = mapper.readValue(EntityUtils.toString(response.getEntity()),AsesoradoModel.class);
            if (response.getCode() == 201) return asesoradoModel.getId();
        }catch (Exception ex) {
            return null;
        }
        return null;
    }

    @Override
    public boolean update(UUID id,AsesoradoBase item) {
        try{
            HttpPut request = new HttpPut(host+id.toString());
            request.setHeader("Content-Type", "application/json");
            request.setEntity(new StringEntity(mapper.writeValueAsString(item)));
            CloseableHttpResponse response = httpClient.execute(request);
            AsesoradoBase asesoradoBase = mapper.readValue(EntityUtils.toString(response.getEntity()),AsesoradoBase.class);
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

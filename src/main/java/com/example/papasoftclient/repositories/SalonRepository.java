package com.example.papasoftclient.repositories;

import com.example.papasoftclient.models.AsesorPage;
import com.example.papasoftclient.models.SalonBase;
import com.example.papasoftclient.models.SalonModel;
import com.example.papasoftclient.models.SalonPage;
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

public class SalonRepository implements Repository<SalonBase, SalonModel>{

    private CloseableHttpClient httpClient;
    private ObjectMapper mapper;
    private String host;

    public SalonRepository() {
        this.httpClient = HttpClient.getClient();
        this.mapper = JsonMapper.getMapper();
        this.host = RestAPI.SALONES_ENDPOINT;
    }

    public SalonPage search(int page, int day, int month, int year, int hour, int minute) {
        try{
            HttpGet request = new HttpGet(host+"?pagina="+page
                    +"&dia="+day
                    +"&mes="+month+
                    "&ano="+year+
                    "&hora="+hour+
                    "&minuto="+minute);
            SalonPage salonPage = httpClient.execute(request,response->{
                if (response.getCode() != 200) return null;
                return mapper.readValue(EntityUtils.toString(response.getEntity()),SalonPage.class);
            });
            return salonPage;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public SalonPage search(int page) {
        try{
            HttpGet request = new HttpGet(host+"?pagina="+page);
            SalonPage salonPage = httpClient.execute(request,response->{
                if (response.getCode() != 200) return null;
                return mapper.readValue(EntityUtils.toString(response.getEntity()),SalonPage.class);
            });
            return salonPage;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public SalonModel search(UUID id) {
        try{
            HttpGet request = new HttpGet(host+id.toString());
            SalonModel salon = httpClient.execute(request,response->{
                if (response.getCode() == 200) return null;
                return mapper.readValue(EntityUtils.toString(response.getEntity()),SalonModel.class);
            });
        }catch (Exception ex){
            return null;
        }
        return null;
    }

    @Override
    public UUID save(SalonBase item) {
        try{
            HttpPost request = new HttpPost(host);
            request.setHeader("Content-Type", "application/json");
            request.setEntity(new StringEntity(mapper.writeValueAsString(item)));
            CloseableHttpResponse response = httpClient.execute(request);
            SalonModel salon = mapper.readValue(EntityUtils.toString(response.getEntity()),SalonModel.class);
            if (response.getCode() == 201) return salon.getId();
        }catch (Exception ex) {
            return null;
        }
        return null;
    }

    @Override
    public boolean update(UUID id,SalonBase item) {
        try{
            HttpPut request = new HttpPut(host+id.toString());
            request.setHeader("Content-Type", "application/json");
            request.setEntity(new StringEntity(mapper.writeValueAsString(item)));
            CloseableHttpResponse response = httpClient.execute(request);
            if (response.getCode() == 200) return true;
        }catch (Exception ex) {
            ex.printStackTrace();
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

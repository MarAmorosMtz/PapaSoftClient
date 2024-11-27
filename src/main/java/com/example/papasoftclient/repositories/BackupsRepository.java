package com.example.papasoftclient.repositories;

import com.example.papasoftclient.utils.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.FileBody;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.HttpEntity;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class BackupsRepository {
    private CloseableHttpClient httpClient;

    public BackupsRepository(){
        this.httpClient = HttpClient.getClient();
    }

    public void backup(){
    }

    public boolean restore(File backupFile) {
        try{
            HttpPost uploadFile = new HttpPost(RestAPI.RESTORE_ENDPOINT);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            FileBody fileBody = new FileBody(backupFile);
            builder.addPart("file", fileBody);

            HttpEntity multipartEntity = builder.build();
            uploadFile.setEntity(multipartEntity);
            CloseableHttpResponse respuesta = httpClient.execute(uploadFile);
            if(respuesta.getCode()==200)return true;
        }catch (IOException ioex){
            ioex.printStackTrace();
            return false;
        }
        return false;
    }

}

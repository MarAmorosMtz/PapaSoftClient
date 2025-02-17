package com.example.papasoftclient.repositories;

import com.example.papasoftclient.models.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.UUID;

public class HorariosSalonRepository {

    private HttpClient client;
    private ObjectMapper mapper;
    private String host;

    public HorariosSalonRepository() {
        this.client = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
        this.host = RestAPI.HORARIOS_SALONES_ENDPOINT;
    }

    public HorarioSalonPage all(UUID salon,UUID periodo) {
        try{
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(host+"?salon="+salon.toString()+"&periodo="+periodo.toString())).GET().build();
            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
            if(response.statusCode()==200) return mapper.readValue(response.body(), HorarioSalonPage.class);
        }catch (URISyntaxException urisex){
            System.err.println("El URI no es valido");
        }catch (IOException ioex){
            System.err.println("Ocurrio un error de E/S o el cliente se cerro inesperadamente.");
        }
        catch (InterruptedException intex){
            System.err.println("Se interrumpio la operacion.");
        }
        return null;
    }

    public HorarioSalonModel get(UUID id) {
        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(host+id.toString()))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
            if(response.statusCode()==200) return mapper.readValue(response.body(), HorarioSalonModel.class);
        }catch (URISyntaxException urisex){
            System.err.println("El URI no es valido");
        }catch (IOException ioex){
            System.err.println("Ocurrio un error de E/S o el cliente se cerro inesperadamente.");
        }
        catch (InterruptedException intex){
            System.err.println("Se interrumpio la operacion.");
        }
        return null;
    }

    public UUID save(HorarioSalonBase item) {
        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .uri(new URI(host))
                    .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(item)))
                    .build();
            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
            HorarioSalonModel horarioModel = mapper.readValue(response.body(), HorarioSalonModel.class);
            if(response.statusCode()==201) return horarioModel.getId();
        }catch (URISyntaxException urisex){
            System.err.println("El URI no es valido");
        }catch (IOException ioex){
            System.err.println("Ocurrio un error de E/S o el cliente se cerro inesperadamente.");
        }
        catch (InterruptedException intex){
            System.err.println("Se interrumpio la operacion.");
        }
        return null;
    }

    public boolean update(UUID id, HorarioBase item) {
        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .uri(new URI(host+id))
                    .PUT(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(item)))
                    .build();
            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
            return response.statusCode()==200;
        }catch (URISyntaxException urisex){
            System.err.println("El URI no es valido");
        }catch (IOException ioex){
            System.err.println("Ocurrio un error de E/S o el cliente se cerro inesperadamente.");
        }
        catch (InterruptedException intex){
            System.err.println("Se interrumpio la operacion.");
        }
        return false;
    }

    public boolean remove(UUID id) {
        try{
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(host+id)).DELETE().build();
            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
            return response.statusCode() == 204;
        }catch (URISyntaxException urisex){
            System.err.println("El URI no es valido");
        }catch (IOException ioex){
            System.err.println("Ocurrio un error de E/S o el cliente se cerro inesperadamente.");
        }
        catch (InterruptedException intex){
            System.err.println("Se interrumpio la operacion.");
        }
        return false;
    }
}

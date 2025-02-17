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

public class AsesoriaRepository implements Repository<AsesoriaBase, AsesoriaModel>{

    private java.net.http.HttpClient client;
    private ObjectMapper mapper;
    private String host;

    public AsesoriaRepository() {
        this.client = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
        this.host = RestAPI.ASESORIAS_ENDPOINT;
    }

    @Override
    public AsesoriaPage search(int page) {
        try{
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(host+"?pagina="+page)).GET().build();
            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
            if(response.statusCode()==200) return mapper.readValue(response.body(), AsesoriaPage.class);
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

    @Override
    public AsesoriaModel search(UUID id) {
        try{
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(host+id.toString())).GET().build();
            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
            if(response.statusCode()==200) return mapper.readValue(response.body(), AsesoriaModel.class);
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

    @Override
    public UUID save(AsesoriaBase item) {
        try{
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(host)).POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(item))).build();
            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
            AsesoriaModel asesoriaModel = mapper.readValue(response.body(), AsesoriaModel.class);
            if(response.statusCode()==201) return asesoriaModel.getId();
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

    @Override
    public boolean update(UUID id,AsesoriaBase item) {
        try{
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(host)).POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(item))).build();
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

    @Override
    public boolean remove(UUID id) {
        try{
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(host+"/"+id)).DELETE().build();
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

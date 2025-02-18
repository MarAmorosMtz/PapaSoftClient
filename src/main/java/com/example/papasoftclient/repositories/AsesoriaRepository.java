package com.example.papasoftclient.repositories;


import com.example.papasoftclient.models.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

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
        mapper.registerModule(new JavaTimeModule());
        this.host = RestAPI.ASESORIAS_ENDPOINT;
    }

    @Override
    public AsesoriaPage search(int page) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .uri(new URI(host + "?pagina=" + page))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return mapper.readValue(response.body(), AsesoriaPage.class);
            } else {
                System.err.println("Error: Código de respuesta HTTP " + response.statusCode());
            }
        } catch (URISyntaxException urisex) {
            System.err.println("El URI no es válido: " + urisex.getMessage());
            urisex.printStackTrace();
        } catch (IOException ioex) {
            System.err.println("Ocurrió un error de E/S o el cliente se cerró inesperadamente.");
            ioex.printStackTrace();
        } catch (InterruptedException intex) {
            System.err.println("Se interrumpió la operación.");
            intex.printStackTrace();
            Thread.currentThread().interrupt(); // Restablecer el estado de interrupción
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
            HttpRequest request = HttpRequest.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .uri(new URI(host))
                    .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(item)))
                    .build();
            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
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

    @Override
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

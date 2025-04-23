package com.example.papasoftclient.repositories;

import com.example.papasoftclient.models.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public class AsesorRepository implements Repository<AsesorBase, AsesorModel>{

    private HttpClient client;
    private ObjectMapper mapper;
    private String host;

    public AsesorRepository() {
        this.client = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        this.host = RestAPI.ASESORES_ENDPOINT;
    }

    @Override
    public AsesorPage search(int page) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .uri(new URI(host + "?pagina=" + page))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return mapper.readValue(response.body(), AsesorPage.class);
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



    public AsesorPage search(int page, int day, int month, int year, int hour, int minute) {
        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .uri(new URI(host+"?pagina="+page
                    +"&dia="+day
                    +"&mes="+month+
                    "&ano="+year+
                    "&hora="+hour+
                    "&minuto="+minute)).GET().build();
            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
            if(response.statusCode()==200) return mapper.readValue(response.body(), AsesorPage.class);
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
    public AsesorModel search(UUID id) {
        try{
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(host+id.toString())).GET().build();
            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
            if(response.statusCode()==200) return mapper.readValue(response.body(), AsesorModel.class);
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
    public UUID save(AsesorBase item) {
        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .uri(new URI(host))
                    .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(item)))
                    .build();
            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            AsesorModel asesorModel = mapper.readValue(response.body(), AsesorModel.class);
            if(response.statusCode()==201) return asesorModel.getId();
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
    public boolean update(UUID id,AsesorBase item) {
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

    public boolean uploadArchivosAsesor(String numCtrl, Path fotoPath, Path contratoPath) throws IOException, InterruptedException, URISyntaxException {
        String boundary = UUID.randomUUID().toString();
        String CRLF = "\r\n";

        var byteStream = new ByteArrayOutputStream();
        var writer = new StringBuilder();

        // Campo de formulario num_ctrl
        writer.append("--").append(boundary).append(CRLF);
        writer.append("Content-Disposition: form-data; name=\"num_ctrl\"").append(CRLF).append(CRLF);
        writer.append(numCtrl).append(CRLF);

        // Campo de archivo foto
        writer.append("--").append(boundary).append(CRLF);
        writer.append("Content-Disposition: form-data; name=\"foto\"; filename=\"")
                .append(fotoPath.getFileName()).append("\"").append(CRLF);
        writer.append("Content-Type: ").append(Files.probeContentType(fotoPath)).append(CRLF).append(CRLF);
        byteStream.write(writer.toString().getBytes());
        byteStream.write(Files.readAllBytes(fotoPath));
        byteStream.write(CRLF.getBytes());
        writer.setLength(0);

        // Campo de archivo contrato
        writer.append("--").append(boundary).append(CRLF);
        writer.append("Content-Disposition: form-data; name=\"contrato\"; filename=\"")
                .append(contratoPath.getFileName()).append("\"").append(CRLF);
        writer.append("Content-Type: ").append(Files.probeContentType(contratoPath)).append(CRLF).append(CRLF);
        byteStream.write(writer.toString().getBytes());
        byteStream.write(Files.readAllBytes(contratoPath));
        byteStream.write(CRLF.getBytes());
        writer.setLength(0);

        // Fin del multipart
        writer.append("--").append(boundary).append("--").append(CRLF);
        byteStream.write(writer.toString().getBytes());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(host + "/upload/"))
                .header("Content-Type", "multipart/form-data; boundary=" + boundary)
                .POST(HttpRequest.BodyPublishers.ofByteArray(byteStream.toByteArray()))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status: " + response.statusCode());
        System.out.println("Body: " + response.body());

        return response.statusCode() == 201;
    }

}


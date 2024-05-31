package br.org.teophilo.carquote.service;

import br.org.teophilo.carquote.modal.Marca;
import br.org.teophilo.carquote.modal.Modelo;
import br.org.teophilo.carquote.modal.Ano;
import br.org.teophilo.carquote.modal.Veiculo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Arrays;

@Service
public class ApiService {
    private final String API_BASE_URL = "https://parallelum.com.br/fipe/api/v1/carros/marcas";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ConverteDados converteDados = new ConverteDados();

    private String fetchJson(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public List<Marca> getMarcas() throws IOException, InterruptedException, JsonProcessingException {
        String json = fetchJson(API_BASE_URL);
        Marca[] marcas = converteDados.converte(json, Marca[].class);
        return Arrays.asList(marcas);
    }

    public List<Modelo> getModelos(String codigoMarca) throws IOException, InterruptedException, JsonProcessingException {
        String url = API_BASE_URL + "/" + codigoMarca + "/modelos";
        String json = fetchJson(url);
        String modelosJson = json.substring(json.indexOf('['), json.lastIndexOf(']') + 1); // Extrair o array de modelos
        Modelo[] modelos = converteDados.converte(modelosJson, Modelo[].class);
        return Arrays.asList(modelos);
    }

    public List<Ano> getAnos(String codigoMarca, String codigoModelo) throws IOException, InterruptedException, JsonProcessingException {
        String url = API_BASE_URL + "/" + codigoMarca + "/modelos/" + codigoModelo + "/anos";
        String json = fetchJson(url);
        Ano[] anos = converteDados.converte(json, Ano[].class);
        return Arrays.asList(anos);
    }

    public Veiculo getVeiculo(String codigoMarca, String codigoModelo, String codigoAno) throws IOException, InterruptedException, JsonProcessingException {
        String url = API_BASE_URL + "/" + codigoMarca + "/modelos/" + codigoModelo + "/anos/" + codigoAno;
        String json = fetchJson(url);
        return converteDados.converte(json, Veiculo.class);
    }
}



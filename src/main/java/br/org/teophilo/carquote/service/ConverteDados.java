package br.org.teophilo.carquote.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ConverteDados implements IConverteDados {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T converte(String json, Class<T> classe) throws JsonProcessingException {
        return mapper.readValue(json, classe);
    }
}


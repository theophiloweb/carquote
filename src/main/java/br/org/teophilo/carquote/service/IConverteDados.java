package br.org.teophilo.carquote.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IConverteDados {
    <T> T converte(String json, Class<T> classe) throws JsonProcessingException;
}


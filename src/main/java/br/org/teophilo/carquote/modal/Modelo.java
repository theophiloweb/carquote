package br.org.teophilo.carquote.modal;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

public record Modelo(
        @JsonAlias("codigo") String codigo,
        @JsonAlias("nome") String nome
) {}



package br.org.teophilo.carquote.modal;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public record Marca(
        @JsonAlias("codigo") String codigo,
        @JsonAlias("nome") String nome
) {}


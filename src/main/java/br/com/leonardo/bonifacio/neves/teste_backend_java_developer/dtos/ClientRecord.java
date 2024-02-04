package br.com.leonardo.bonifacio.neves.teste_backend_java_developer.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record ClientRecord(

        @NotNull
        @JsonProperty("Nome")
        String name,

        @JsonProperty("Nome Social")
        String socialName,

        @CPF(message = "cpf inválido")
        @JsonProperty("CPF")
        String cpf
) {
}

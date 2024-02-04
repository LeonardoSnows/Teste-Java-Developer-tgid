package br.com.leonardo.bonifacio.neves.teste_backend_java_developer.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransactionDto(

        @NotNull
        @JsonProperty("Valor")
        BigDecimal value,

        @NotNull
        @JsonProperty("Cliente")
        Long cliente,

        @NotNull
        @JsonProperty("Empresa")
        Long empresa
) {
}

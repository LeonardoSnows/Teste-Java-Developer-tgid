package br.com.leonardo.bonifacio.neves.teste_backend_java_developer.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransactionCompanyDto(
        @NotNull
        @JsonProperty("Valor")
        BigDecimal value,

        @NotNull
        @JsonProperty("ID Cliente")
        Long clientId,

        @NotNull
        @JsonProperty("Empresa")
        Long company,

        @NotNull
        @JsonProperty("Empresa Alvo")
        Long targetCompany) {
}

package br.com.leonardo.bonifacio.neves.teste_backend_java_developer.dtos;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransactionDto(

        @NotNull
        BigDecimal value,

        @NotNull
        Long cliente,

        @NotNull
        Long empresa
) {
}

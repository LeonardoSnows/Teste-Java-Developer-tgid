package br.com.leonardo.bonifacio.neves.teste_backend_java_developer.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;

public record ClientRecord(

        @NotNull
        @JsonProperty("Nome")
        String name,

        @JsonProperty("Nome Social")
        String socialName,

        @CPF(message = "cpf inválido")
        @JsonProperty("CPF")
        String cpf,

        @DecimalMin(value = "0.00", inclusive = false, message = "O valor deve ser maior que {value}")
        @NotNull
        @JsonProperty("Saldo")
        BigDecimal balance
) {
}

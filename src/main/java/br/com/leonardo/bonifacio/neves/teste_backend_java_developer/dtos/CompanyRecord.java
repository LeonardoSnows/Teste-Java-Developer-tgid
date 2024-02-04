package br.com.leonardo.bonifacio.neves.teste_backend_java_developer.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;

import java.math.BigDecimal;

public record CompanyRecord(
        @NotNull
        @JsonProperty("Empresa")
        String nameCompany,

        @CNPJ
        @NotNull
        @JsonProperty("CNPJ")
        String cnpj,

        @JsonProperty("Saldo")
        @DecimalMin(value = "0.00", inclusive = false, message = "O valor deve ser maior que {value}")
        BigDecimal balance
) {
}

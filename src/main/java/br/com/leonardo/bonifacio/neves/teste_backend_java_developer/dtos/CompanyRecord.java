package br.com.leonardo.bonifacio.neves.teste_backend_java_developer.dtos;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;

import java.math.BigDecimal;

public record CompanyRecord(
        @NotNull
        String nameCompany,

        @CNPJ
        @NotNull
        String cnpj,

        BigDecimal saldo
) {
}

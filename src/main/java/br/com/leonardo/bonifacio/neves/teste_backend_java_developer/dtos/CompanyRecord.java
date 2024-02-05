package br.com.leonardo.bonifacio.neves.teste_backend_java_developer.dtos;

import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.models.ClienteModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.aspectj.weaver.ast.Not;
import org.hibernate.validator.constraints.br.CNPJ;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public record CompanyRecord(
        @NotBlank(message = "Campo Empresa não pode ser vazio")
        @JsonProperty("Empresa")
        String nameCompany,

        @CNPJ
        @NotBlank(message = "Campo CNPJ não  pode ser nulo")
        @JsonProperty("CNPJ")
        String cnpj,

        @NotNull(message = "Campo Saldo não  pode ser nulo")
        @JsonProperty("Saldo")
        @DecimalMin(value = "0.00", inclusive = false, message = "O valor deve ser maior que {value}")
        BigDecimal balance,

        @JsonProperty("Clientes")
        List<ClienteModel> clientes
) {
}

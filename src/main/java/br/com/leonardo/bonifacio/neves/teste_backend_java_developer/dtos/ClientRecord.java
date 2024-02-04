package br.com.leonardo.bonifacio.neves.teste_backend_java_developer.dtos;

import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.models.EmpresaModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.util.List;

public record ClientRecord(


        @NotNull(message = "Campo Nome nao pode ser nulo")
        @NotEmpty(message = "Campo Nome nao pode ser vazio")
        @JsonProperty("Nome")
        String name,

        @JsonProperty("Nome Social")
        String socialName,

        @CPF(message = "cpf inválido")
        @NotNull(message = "Campo CPF nao pode ser nulo")
        @NotEmpty(message = "Campo CPF nao pode ser vazio")
        @JsonProperty("CPF")
        String cpf,

        @DecimalMin(value = "0.00", inclusive = false, message = "O valor deve ser maior que {value}")
        @NotNull(message = "Campo Saldo nao pode ser nulo")
        @JsonProperty("Saldo")
        BigDecimal balance,

        @NotNull(message = "Campo Celular nao pode ser nulo")
        @NotEmpty(message = "Campo Celular nao pode ser vazio")
        @JsonProperty("Celular")
        @Pattern(regexp = "^\\+[1-9]\\d{1,14}$", message ="O numero deve seguir o padrão [+] [Código do país] [número de assinante incluindo código de área]")
        String clientNumber,

        @NotNull
        @JsonProperty("Empresa")
        List<EmpresaModel> empresas
) {
}

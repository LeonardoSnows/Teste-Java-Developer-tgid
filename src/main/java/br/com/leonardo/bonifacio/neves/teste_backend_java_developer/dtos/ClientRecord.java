package br.com.leonardo.bonifacio.neves.teste_backend_java_developer.dtos;

import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.models.EmpresaModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.util.List;

public record ClientRecord(
        @NotBlank(message = "Campo Nome nao pode ser vazio")
        @JsonProperty("Nome")
        String name,

        @JsonProperty("Nome Social")
        String socialName,

        @CPF(message = "cpf inválido")
        @NotBlank(message = "Campo CPF não pode ser vazio")
        @JsonProperty("CPF")
        String cpf,

        @DecimalMin(value = "0.00", inclusive = false, message = "O valor deve ser maior que {value}")
        @JsonProperty("Saldo")
        BigDecimal balance,

        @NotBlank(message = "Campo Celular não pode ser vazio")
        @JsonProperty("Celular")
        @Pattern(regexp = "^\\+[1-9]\\d{1,14}$", message = "O numero deve seguir o padrão [+] [Código do país] [número de assinante incluindo código de área]")
        String clientNumber,

        @JsonProperty("Empresa")
        @NotEmpty(message = "A lista de empresas não pode estar vazia, cadastre uma e apos isto, inclua seu valor aqui")
        List<EmpresaModel> empresas
) {
}

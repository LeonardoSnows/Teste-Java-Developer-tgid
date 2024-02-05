package br.com.leonardo.bonifacio.neves.teste_backend_java_developer.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_COMPANY")
@Getter
@Setter
public class EmpresaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameCompany;

    private String cnpj;

    private BigDecimal taxas;

    private BigDecimal balance;

    @JsonIgnore
    @ManyToMany(mappedBy = "empresas")
    @NotBlank(message = "Campo clientes nao pode ser vazio")
    private List<ClienteModel> clientes = new ArrayList<>();
}

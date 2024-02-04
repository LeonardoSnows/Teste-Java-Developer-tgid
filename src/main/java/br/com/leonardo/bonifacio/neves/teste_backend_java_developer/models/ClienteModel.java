package br.com.leonardo.bonifacio.neves.teste_backend_java_developer.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_CLIENT")
@Getter
@Setter
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private String socialName;

    private BigDecimal balance;

    private String cpf;

    @ManyToMany
    @JoinTable(name = "cliente_empresa",
            joinColumns = {@JoinColumn(name = "cliente_id", referencedColumnName = "id")},
            inverseJoinColumns = @JoinColumn(name = "empresa_id", referencedColumnName = "id"))
    private List<EmpresaModel> empresas = new ArrayList<>();


}

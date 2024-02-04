package br.com.leonardo.bonifacio.neves.teste_backend_java_developer.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    private String name;

    private String socialName;

    private BigDecimal balance;

    private String cpf;

    private String clientNumber;

    @ManyToMany
    @JoinTable(name = "cliente_empresa",
            joinColumns = {@JoinColumn(name = "cliente_id", referencedColumnName = "id")},
            inverseJoinColumns = @JoinColumn(name = "empresa_id", referencedColumnName = "id"))
    private List<EmpresaModel> empresas = new ArrayList<>();


}

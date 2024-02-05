package br.com.leonardo.bonifacio.neves.teste_backend_java_developer.repository;

import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

    ClienteModel findClienteById(Long cliente);
}

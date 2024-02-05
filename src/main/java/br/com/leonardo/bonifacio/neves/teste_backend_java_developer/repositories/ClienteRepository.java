package br.com.leonardo.bonifacio.neves.teste_backend_java_developer.repositories;

import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

    ClienteModel findClienteById(Long cliente);
}

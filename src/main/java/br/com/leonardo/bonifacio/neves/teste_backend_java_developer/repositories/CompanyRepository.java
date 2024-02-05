package br.com.leonardo.bonifacio.neves.teste_backend_java_developer.repositories;

import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.models.EmpresaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<EmpresaModel, Long> {
    EmpresaModel findEmpresaById(Long empresa);
}

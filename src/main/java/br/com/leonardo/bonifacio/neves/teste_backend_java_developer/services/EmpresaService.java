package br.com.leonardo.bonifacio.neves.teste_backend_java_developer.services;

import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.models.EmpresaModel;
import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.repositories.CompanyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {

    private final CompanyRepository repository;


    public EmpresaService(CompanyRepository repository) {
        this.repository = repository;
    }

    public EmpresaModel registerCompany(EmpresaModel company) {
        return repository.save(company);
    }

    public Page<EmpresaModel> findAllClients(Pageable pageable) {
        return repository.findAll(pageable);
    }
}

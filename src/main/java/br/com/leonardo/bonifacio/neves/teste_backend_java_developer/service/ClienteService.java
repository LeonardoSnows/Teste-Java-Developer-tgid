package br.com.leonardo.bonifacio.neves.teste_backend_java_developer.service;

import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.dtos.TransactionDto;
import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.models.ClienteModel;
import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.repository.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ClienteModel register(ClienteModel client) {
        return repository.save(client);
    }

    public Page<ClienteModel> findAllClients(Pageable pageable) {
        return repository.findAll(pageable);
    }

    
}

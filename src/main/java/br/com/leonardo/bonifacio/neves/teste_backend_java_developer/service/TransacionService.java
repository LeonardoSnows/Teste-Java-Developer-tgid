package br.com.leonardo.bonifacio.neves.teste_backend_java_developer.service;

import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.dtos.TransactionDto;
import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.models.ClienteModel;
import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.models.EmpresaModel;
import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.repository.ClienteRepository;
import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class TransacionService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public void transactionWithdrawals(TransactionDto transactionDto) {
        ClienteModel client = this.clienteRepository.findClienteById(transactionDto.cliente());
        EmpresaModel company = this.companyRepository.findEmpresaById(transactionDto.empresa());

        var taxaCompany = company.getTaxas();
        if (!validaValores(taxaCompany, transactionDto.value())) {
            throw new RuntimeException("Valor de Taxa maior do que o valor enviado para saque");
        }
        client.setBalance(client.getBalance().subtract(transactionDto.value().subtract(taxaCompany)).setScale(2, RoundingMode.HALF_EVEN));
        company.setBalance(company.getBalance().subtract(transactionDto.value()).setScale(2, RoundingMode.HALF_EVEN));
    }

    private boolean validaValores(BigDecimal taxas, BigDecimal valor) {
        return taxas.compareTo(valor) <= 0;
    }
}

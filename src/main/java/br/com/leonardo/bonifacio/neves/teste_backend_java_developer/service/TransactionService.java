package br.com.leonardo.bonifacio.neves.teste_backend_java_developer.service;

import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.dtos.TransactionCompanyDto;
import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.dtos.TransactionDto;
import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.dtos.TransactionWithdrawalsCompany;
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
public class TransactionService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public void transactionWithdrawalsClient(TransactionDto transactionDto) {
        ClienteModel client = this.clienteRepository.findClienteById(transactionDto.cliente());
        EmpresaModel company = this.companyRepository.findEmpresaById(transactionDto.empresa());

        var taxaCompany = company.getTaxas();
        var balanceCliente = client.getBalance();
        var balanceCompany = company.getBalance();

        if (!validaSaldo(balanceCliente, transactionDto.value()) || !validaSaldo(balanceCompany, transactionDto.value())) {
            throw new RuntimeException("Valor do saque maior do que o saldo da conta empresa/cliente");
        }

        var validaTaxa = validaValoresTaxas(transactionDto.value(), taxaCompany);
        if (!validaTaxa) {
            throw new RuntimeException("Valor de Taxa maior do que o valor enviado para saque");
        }

        company.setBalance(balanceCompany.subtract(transactionDto.value()).setScale(2, RoundingMode.HALF_EVEN));
        client.setBalance(balanceCliente.add(transactionDto.value().subtract(taxaCompany)).setScale(2, RoundingMode.HALF_EVEN));
    }

    @Transactional
    public void transactionDepositClient(TransactionDto transactionDto) {
        ClienteModel client = this.clienteRepository.findClienteById(transactionDto.cliente());
        EmpresaModel company = this.companyRepository.findEmpresaById(transactionDto.empresa());

        var taxaCompany = company.getTaxas();
        var balanceCliente = client.getBalance();
        var balanceCompany = company.getBalance();

        if (!validaValoresTaxas(transactionDto.value(), taxaCompany)) {
            throw new RuntimeException("Valor de Taxa maior do que o valor enviado para saque");
        }

        client.setBalance(balanceCliente.subtract(transactionDto.value()).setScale(2, RoundingMode.HALF_EVEN));
        company.setBalance(balanceCompany.add(transactionDto.value().subtract(taxaCompany)).setScale(2, RoundingMode.HALF_EVEN));
    }

    @Transactional
    public void transactionWithdrawalsCompany(TransactionWithdrawalsCompany transactionCompanyDto) {
        ClienteModel client = this.clienteRepository.findClienteById(transactionCompanyDto.clientId());
        EmpresaModel company = this.companyRepository.findEmpresaById(transactionCompanyDto.company());

        if (!companyContainsClient(client, company)) {
            throw new RuntimeException("Cliente nao cadastrado na empresa.");
        }

        var taxaCompany = company.getTaxas();

        var balanceCompany = company.getBalance();

        if (!validaSaldo(balanceCompany, transactionCompanyDto.value())) {
            throw new RuntimeException("O valor do saque é maior que o saldo da conta da empresa.");
        }

        if (!validaValoresTaxas(transactionCompanyDto.value(), taxaCompany)) {
            throw new RuntimeException("O valor da taxa é superior ao valor enviado para saque.");
        }

        company.setBalance(balanceCompany.add(transactionCompanyDto.value().subtract(taxaCompany)).setScale(2, RoundingMode.HALF_EVEN));
    }

    @Transactional
    public void transactionDepositCompany(TransactionCompanyDto transactionCompanyDto) {
        ClienteModel client = this.clienteRepository.findClienteById(transactionCompanyDto.clientId());
        EmpresaModel company = this.companyRepository.findEmpresaById(transactionCompanyDto.company());
        EmpresaModel targetCompany = this.companyRepository.findEmpresaById(transactionCompanyDto.targetCompany());

        if (!companyContainsClient(client, company)) {
            throw new RuntimeException("Cliente nao cadastrado na empresa.");
        }

        var taxaCompany = company.getTaxas();

        var balanceCompany = company.getBalance();
        var balanceTargetCompany = targetCompany.getBalance();

        if (!validaSaldo(balanceCompany, transactionCompanyDto.value())) {
            throw new RuntimeException("O valor do saque é maior que o saldo da conta da empresa.");
        }

        if (!validaValoresTaxas(transactionCompanyDto.value(), taxaCompany)) {
            throw new RuntimeException("O valor da taxa é superior ao valor enviado para saque.");
        }

        company.setBalance(balanceTargetCompany.subtract(transactionCompanyDto.value()).setScale(2, RoundingMode.HALF_EVEN));
        targetCompany.setBalance(balanceCompany.add(transactionCompanyDto.value().subtract(taxaCompany)).setScale(2, RoundingMode.HALF_EVEN));
    }

    public boolean companyContainsClient(ClienteModel client, EmpresaModel company) {
        return client.getEmpresas().contains(company);
    }

    private boolean validaSaldo(BigDecimal balance, BigDecimal valorDoSaque) {
        return balance.compareTo(valorDoSaque) > 0;
    }

    private boolean validaValoresTaxas(BigDecimal valorSaque, BigDecimal taxa) {
        return valorSaque.compareTo(taxa) > 0;
    }
}

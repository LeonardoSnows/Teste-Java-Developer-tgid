package br.com.leonardo.bonifacio.neves.teste_backend_java_developer.service;

import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.dtos.TransactionCompanyDto;
import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.dtos.TransactionDto;
import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.dtos.TransactionWithdrawalsCompany;
import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.exceptions.CompanyContainsClientException;
import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.exceptions.InvalidBalanceClientException;
import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.exceptions.InvalidBalanceCompanyException;
import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.exceptions.InvalidValueException;
import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.models.ClienteModel;
import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.models.EmpresaModel;
import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.repository.ClienteRepository;
import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.repository.CompanyRepository;
import com.twilio.exception.ApiException;
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

    @Autowired
    private SMSService smsService;

    @Transactional
    public void transactionWithdrawalsClient(TransactionDto transactionDto) {
        ClienteModel client = this.clienteRepository.findClienteById(transactionDto.cliente());
        EmpresaModel company = this.companyRepository.findEmpresaById(transactionDto.empresa());

        var taxaCompany = company.getTaxas();
        var balanceCliente = client.getBalance();
        var balanceCompany = company.getBalance();

        if (!validaSaldo(balanceCliente, transactionDto.value()) || !validaSaldo(balanceCompany, transactionDto.value())) {
            throw new InvalidBalanceCompanyException();
        }

        var validaTaxa = validaValoresTaxas(transactionDto.value(), taxaCompany);
        if (!validaTaxa) {
            throw new InvalidValueException();
        }

        company.setBalance(balanceCompany.subtract(transactionDto.value()).setScale(2, RoundingMode.HALF_EVEN));
        client.setBalance(balanceCliente.add(transactionDto.value().subtract(taxaCompany)).setScale(2, RoundingMode.HALF_EVEN));
        enviaSmsCliente(client);
    }

    private void enviaSmsCliente(ClienteModel client) {
        try {
            smsService.send(client);
        } catch (ApiException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void transactionDepositClient(TransactionDto transactionDto) {
        ClienteModel client = this.clienteRepository.findClienteById(transactionDto.cliente());
        EmpresaModel company = this.companyRepository.findEmpresaById(transactionDto.empresa());

        var taxaCompany = company.getTaxas();
        var balanceCliente = client.getBalance();
        var balanceCompany = company.getBalance();

        if (!validaSaldo(balanceCliente, transactionDto.value()))
            throw new InvalidBalanceClientException();

        if (!validaValoresTaxas(transactionDto.value(), taxaCompany)) {
            throw new InvalidValueException();
        }

        client.setBalance(balanceCliente.subtract(transactionDto.value()).setScale(2, RoundingMode.HALF_EVEN));
        company.setBalance(balanceCompany.add(transactionDto.value().subtract(taxaCompany)).setScale(2, RoundingMode.HALF_EVEN));

        enviaSmsCliente(client);
    }

    @Transactional
    public void transactionWithdrawalsCompany(TransactionWithdrawalsCompany transactionCompanyDto) {

        ClienteModel client = this.clienteRepository.findClienteById(transactionCompanyDto.clientId());
        EmpresaModel company = this.companyRepository.findEmpresaById(transactionCompanyDto.company());

        if (!companyContainsClient(client, company))
            throw new CompanyContainsClientException();

        var taxaCompany = company.getTaxas();

        var balanceCompany = company.getBalance();

        if (!validaSaldo(balanceCompany, transactionCompanyDto.value())) {
            throw new InvalidBalanceCompanyException();
        }

        if (!validaValoresTaxas(transactionCompanyDto.value(), taxaCompany)) {
            throw new InvalidValueException();
        }

        company.setBalance(balanceCompany.add(transactionCompanyDto.value().subtract(taxaCompany)).setScale(2, RoundingMode.HALF_EVEN));

        enviaSmsCliente(client);
    }

    @Transactional
    public void transactionDepositCompany(TransactionCompanyDto transactionCompanyDto) {

        ClienteModel client = this.clienteRepository.findClienteById(transactionCompanyDto.clientId());
        EmpresaModel company = this.companyRepository.findEmpresaById(transactionCompanyDto.company());
        EmpresaModel targetCompany = this.companyRepository.findEmpresaById(transactionCompanyDto.targetCompany());

        if (!companyContainsClient(client, company)) throw new RuntimeException("Cliente não cadastrado na empresa.");

        var taxaCompany = company.getTaxas();

        var balanceCompany = company.getBalance();
        var balanceTargetCompany = targetCompany.getBalance();

        if (!validaSaldo(balanceCompany, transactionCompanyDto.value()))
            throw new InvalidBalanceCompanyException();

        if (!validaValoresTaxas(transactionCompanyDto.value(), taxaCompany))
            throw new InvalidValueException();

        company.setBalance(balanceTargetCompany.subtract(transactionCompanyDto.value()).setScale(2, RoundingMode.HALF_EVEN));
        targetCompany.setBalance(balanceCompany.add(transactionCompanyDto.value().subtract(taxaCompany)).setScale(2, RoundingMode.HALF_EVEN));

        enviaSmsCliente(client);
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

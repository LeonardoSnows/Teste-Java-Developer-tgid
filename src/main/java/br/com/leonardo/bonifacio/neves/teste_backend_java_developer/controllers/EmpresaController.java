package br.com.leonardo.bonifacio.neves.teste_backend_java_developer.controllers;

import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.dtos.CompanyRecord;
import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.dtos.TransactionCompanyDto;
import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.dtos.TransactionWithdrawalsCompany;
import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.enums.TaxaEmpresa;
import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.models.EmpresaModel;
import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.service.EmpresaService;
import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.service.SMSService;
import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.service.TransactionService;
import com.twilio.exception.ApiException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/servico/empresa")
public class EmpresaController {

    @Autowired
    private TransactionService transactionService;



    @Autowired
    private EmpresaService empresaService;

    @PostMapping("/registrar")
    public ResponseEntity companyRegister(@RequestBody @Valid CompanyRecord company) {
        var empresaModel = new EmpresaModel();
        BeanUtils.copyProperties(company, empresaModel);
        empresaModel.setTaxas(TaxaEmpresa.getRandomValorEnum().getValor());
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaService.registerCompany(empresaModel));
    }

    @GetMapping
    public ResponseEntity<Page<EmpresaModel>> getAllCompanys(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(empresaService.findAllClients(pageable));
    }

    @PostMapping("/transacao/saque")
    public ResponseEntity companyTransactionWithdrawals(@RequestBody @Valid TransactionWithdrawalsCompany data) {
        transactionService.transactionWithdrawalsCompany(data);
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @PostMapping("/transacao/deposito")
    public ResponseEntity clientTransactionDeposit(@RequestBody @Valid TransactionCompanyDto data) {
        transactionService.transactionDepositCompany(data);
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }
}

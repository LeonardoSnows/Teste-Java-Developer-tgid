package br.com.leonardo.bonifacio.neves.teste_backend_java_developer.controllers;

import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.dtos.ClientRecord;
import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.dtos.TransactionDto;
import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.models.ClienteModel;
import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.services.ClienteService;
import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.services.TransactionService;
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
@RequestMapping("/servico/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/registrar")
    public ResponseEntity clientRegister(@RequestBody @Valid ClientRecord data) {
        var clientModel = new ClienteModel();
        BeanUtils.copyProperties(data, clientModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.register(clientModel));
    }

    @GetMapping
    public ResponseEntity<Page<ClienteModel>> getAllClients(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAllClients(pageable));
    }


    @PostMapping("/transacao/saque")
    public ResponseEntity clientTransactionWithdrawals(@RequestBody @Valid TransactionDto data) {
        transactionService.transactionWithdrawalsClient(data);
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @PostMapping("/transacao/deposito")
    public ResponseEntity clientTransactionDeposit(@RequestBody @Valid TransactionDto data) {
        transactionService.transactionDepositClient(data);
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

}

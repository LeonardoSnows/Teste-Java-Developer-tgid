package br.com.leonardo.bonifacio.neves.teste_backend_java_developer.exceptions;

public class InvalidBalanceClientException extends RuntimeException{
    public InvalidBalanceClientException() {
        super("O valor do saque é maior que o saldo da conta do cliente.");
    }

    public InvalidBalanceClientException(String msg) {
        super(msg);
    }
}

package br.com.leonardo.bonifacio.neves.teste_backend_java_developer.exceptions;

public class InvalidBalanceCompanyException extends RuntimeException{
    public InvalidBalanceCompanyException() {
        super("O valor do saque é maior que o saldo da conta da empresa.");
    }

    public InvalidBalanceCompanyException(String msg) {
        super(msg);
    }
}

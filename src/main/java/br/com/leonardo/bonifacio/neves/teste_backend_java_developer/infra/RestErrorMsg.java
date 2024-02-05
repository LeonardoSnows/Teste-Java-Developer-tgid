package br.com.leonardo.bonifacio.neves.teste_backend_java_developer.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class RestErrorMsg {
    private HttpStatus status;
    private String message;
}

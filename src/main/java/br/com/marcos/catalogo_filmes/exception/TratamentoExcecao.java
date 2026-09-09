package br.com.marcos.catalogo_filmes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratamentoExcecao {
    @ExceptionHandler(FilmeNaoEncontradoException.class)
    public ResponseEntity<String> tratarFilmeNaoEncontrado(FilmeNaoEncontradoException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
package br.com.marcos.catalogo_filmes.exception;

public class FilmeNaoEncontradoException extends RuntimeException {
    public FilmeNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}

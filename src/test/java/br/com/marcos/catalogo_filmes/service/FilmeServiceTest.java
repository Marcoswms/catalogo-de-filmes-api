package br.com.marcos.catalogo_filmes.service;

import br.com.marcos.catalogo_filmes.model.Filme;
import br.com.marcos.catalogo_filmes.repository.FilmeRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FilmeServiceTest {

    @Mock
    private FilmeRepository repository;

    @InjectMocks
    FilmeService service;

    @Test
    void deveSalvarFilme() {
        Filme filme = new Filme();
        filme.setTitulo("Matrix");

        when(repository.save(filme)).thenReturn(filme);

        Filme resultado = service.salvar(filme);
        assertEquals(filme, resultado);
        verify(repository).save(filme);
    }
}
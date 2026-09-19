package br.com.marcos.catalogo_filmes.service;

import br.com.marcos.catalogo_filmes.exception.FilmeNaoEncontradoException;
import br.com.marcos.catalogo_filmes.model.Filme;
import br.com.marcos.catalogo_filmes.repository.FilmeRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    @Test
    void deveBuscarFilmePorId() {
        Filme filme = new Filme();
        filme.setId(1L);
        filme.setTitulo("Matrix");;

        when(repository.findById(1L)).thenReturn(Optional.of(filme));

        Filme resultado = service.buscarPorId(1L);

        assertEquals(filme, resultado);
        verify(repository).findById(1L);
    }

    @Test
    void deveLancarExcecaoQuandoFilmeNaoExistir() {

        when(repository.findById(1L)).thenReturn(Optional.empty());

        FilmeNaoEncontradoException exception = assertThrows(
                FilmeNaoEncontradoException.class, () -> service.buscarPorId(1L)
        );

        assertEquals("Filme não encontrado.", exception.getMessage());
    }

    @Test
    void deveAtualizarFilmePorId() {
        Filme filmeExistente = new Filme();
        filmeExistente.setId(1L);
        filmeExistente.setTitulo("Matrix");

        Filme filmeAtualizado = new Filme();
        filmeAtualizado.setTitulo("Matrix Reloaded");

        when(repository.findById(1L)).thenReturn(Optional.of(filmeExistente));

        when(repository.save(filmeExistente)).thenReturn(filmeExistente);

        Optional<Filme> resultado = service.atualizarPorId(1L, filmeAtualizado);

        assertTrue(resultado.isPresent());
        assertEquals("Matrix Reloaded", resultado.get().getTitulo());

        verify(repository).findById(1L);
        verify(repository).save(filmeExistente);
    }

    @Test
    void deveRetornarVazioAoAtualizarFilmeInexistente() {

        when(repository.findById(1L)).thenReturn(Optional.empty());

        Optional<Filme> resultado = service.atualizarPorId(1L, new Filme());

        assertTrue(resultado.isEmpty());

        verify(repository).findById(1L);
        verify(repository, never()).save(any(Filme.class));
    }

    @Test
    void deveDeletarFilmePorId() {

        when(repository.existsById(1L)).thenReturn(true);

        boolean resultado = service.deletarPorId(1L);

        assertTrue(resultado);

        verify(repository).existsById(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    void naoDeveDeletarFilmeInexistende() {

        when(repository.existsById(1L)).thenReturn(false);

        boolean resultado = service.deletarPorId(1L);

        assertFalse(resultado);

        verify(repository).existsById(1L);
        verify(repository, never()).deleteById(1L);
    }
}
package br.com.marcos.catalogo_filmes.controller;

import br.com.marcos.catalogo_filmes.exception.FilmeNaoEncontradoException;
import br.com.marcos.catalogo_filmes.model.Filme;
import br.com.marcos.catalogo_filmes.service.FilmeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FilmeController.class)
class FilmeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FilmeService service;

    @Test
    void deveListarTodosOsFilmes() throws Exception {

        Filme filme = new Filme();
        filme.setId(1L);
        filme.setTitulo("Matrix");

        when(service.listarTodos()).thenReturn(List.of(filme));

        mockMvc.perform(get("/filmes")).andExpect(status().isOk());
    }

    @Test
    void deveBuscarFilmesPorId() throws Exception {

        Filme filme = new Filme();
        filme.setId(1L);
        filme.setTitulo("Matrix");

        when(service.buscarPorId(1L)).thenReturn(filme);

        mockMvc.perform(get("/filmes/1")).andExpect(status().isOk());
    }

    @Test
    void deveRetornar404QuandoFilmeNaoExistir() throws Exception {

        when(service.buscarPorId(999L)).thenThrow(new FilmeNaoEncontradoException("Filme não encontrado."));

        mockMvc.perform(get("/filmes/999")).andExpect(status().isNotFound());
    }

    @Test
    void deveSalvarFilme() throws Exception {

        Filme filme = new Filme();
        filme.setId(1L);
        filme.setTitulo("Matrix");

        when(service.salvar(any(Filme.class))).thenReturn(filme);

        mockMvc.perform(post("/filmes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "titulo": "Matrix"
                                }
                                """))
                .andExpect(status().isOk());
    }

    @Test
    void deveAtualizarFilme() throws Exception {

        Filme filme = new Filme();
        filme.setId(1L);
        filme.setTitulo("Matrix Reloaded");

        when(service.atualizarPorId(eq(1L), any(Filme.class)))
                .thenReturn(Optional.of(filme));

        mockMvc.perform(put("/filmes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "titulo": "Matrix Reloaded"
                                }
                                """))
                .andExpect(status().isOk());
    }

    @Test
    void deveRetornar404AoAtualizarFilmeInexistente() throws Exception {

        when(service.atualizarPorId(eq(999L), any(Filme.class))).thenReturn(Optional.empty());

        mockMvc.perform(put("/filmes/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "titulo": "Filme inexistente"
                                }
                                """))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveExcluirFilme() throws Exception {

        when(service.deletarPorId(1L)).thenReturn(true);

        mockMvc.perform(delete("/filmes/1")).andExpect(status().isNoContent());
    }

    @Test
    void deveRetornar404AoExcluirFilmeInexistente() throws Exception {

        when(service.deletarPorId(999L)).thenReturn(false);

        mockMvc.perform(delete("/filmes/999")).andExpect(status().isNotFound());
    }
}
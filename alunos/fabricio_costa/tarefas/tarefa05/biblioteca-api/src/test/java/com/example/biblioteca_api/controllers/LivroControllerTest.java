package com.example.biblioteca_api.controllers;

import com.example.biblioteca_api.controllers.request.LivroRequest;
import com.example.biblioteca_api.controllers.response.LivroResponse;
import com.example.biblioteca_api.dtos.LivroDTO;
import com.example.biblioteca_api.mappers.LivroMapper;
import com.example.biblioteca_api.services.LivroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Testes unitários do LivroController.
 *
 * Espelham exatamente os cenários da Biblioteca_API.postman_collection.json:
 *  - POST /livros          → 201 + id não nulo + header Location + título aleatório
 *  - POST /livros          → 400 com campos de validação
 *  - GET  /livros          → 200 paginado + lista não vazia
 *  - GET  /livros/{id}     → 200 + id/titulo/autor não nulos
 *  - GET  /livros/{id}     → 404 + mensagem contendo o id
 *  - GET  /livros/titulo   → 200 + array + lista não vazia + filtragem correta
 *  - PUT  /livros/{id}     → 200 + título atualizado + mesmo id
 *  - DELETE /livros/{id}   → 204 + body vazio
 */
@WebMvcTest(LivroController.class)
@DisplayName("LivroController — Testes Unitários")
class LivroControllerTest {

    @Autowired MockMvc     mockMvc;
    @Autowired ObjectMapper objectMapper;

    @MockBean LivroService livroService;
    @MockBean LivroMapper  livroMapper;

    // ----------------------------------------------------------------
    // POST /livros
    // ----------------------------------------------------------------

    @Nested
    @DisplayName("POST /livros")
    class CadastrarLivro {

        @Test
        @DisplayName("Deve retornar 201, id não nulo e header Location ao cadastrar livro")
        void cadastrar_retorna201_comIdELocation() throws Exception {
            // Simula título "aleatório" como o pre-request script do Postman faz
            String tituloAleatorio = "Effective Java #4271";

            LivroRequest  request  = new LivroRequest(tituloAleatorio, "Joshua Bloch");
            LivroDTO      dto      = new LivroDTO(null, tituloAleatorio, "Joshua Bloch");
            LivroDTO      salvo    = new LivroDTO(1L,   tituloAleatorio, "Joshua Bloch");
            LivroResponse response = new LivroResponse(1L, tituloAleatorio, "Joshua Bloch");

            when(livroMapper.toDTO(any(LivroRequest.class))).thenReturn(dto);
            when(livroService.cadastrar(dto)).thenReturn(salvo);
            when(livroMapper.toResponse(salvo)).thenReturn(response);

            mockMvc.perform(post("/livros")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isCreated())                          // 201
                    .andExpect(jsonPath("$.id").isNotEmpty())                 // id não nulo
                    .andExpect(jsonPath("$.titulo").value(tituloAleatorio))   // título mantido
                    .andExpect(jsonPath("$.autor").value("Joshua Bloch"))     // autor correto
                    .andExpect(header().exists("Location"));                  // header Location presente
        }

        @Test
        @DisplayName("Deve retornar 201 para diferentes títulos simulando aleatoriedade")
        void cadastrar_aceitaTitulosVariados() throws Exception {
            // Garante que o endpoint não rejeita títulos com sufixo numérico (#XXXX)
            String[] titulos = {
                    "Clean Code #1234",
                    "Design Patterns #9999",
                    "Refactoring #0001"
            };

            for (String titulo : titulos) {
                LivroDTO dto   = new LivroDTO(null, titulo, "Autor");
                LivroDTO salvo = new LivroDTO(1L,   titulo, "Autor");
                LivroResponse resp = new LivroResponse(1L, titulo, "Autor");

                when(livroMapper.toDTO(any(LivroRequest.class))).thenReturn(dto);
                when(livroService.cadastrar(dto)).thenReturn(salvo);
                when(livroMapper.toResponse(salvo)).thenReturn(resp);

                mockMvc.perform(post("/livros")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"titulo\":\"" + titulo + "\",\"autor\":\"Autor\"}"))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.id").isNotEmpty());
            }
        }

        @Test
        @DisplayName("Deve retornar 400 e campos de erro quando titulo e autor estão vazios")
        void cadastrar_retorna400_camposVazios() throws Exception {
            // Espelha: POST — Validation Error (campos vazios)
            mockMvc.perform(post("/livros")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"titulo\":\"\",\"autor\":\"\"}"))
                    .andExpect(status().isBadRequest())                       // 400
                    .andExpect(jsonPath("$.campos").exists())                 // campo "campos" presente
                    .andExpect(jsonPath("$.campos.titulo").exists())          // erro em titulo
                    .andExpect(jsonPath("$.campos.autor").exists());          // erro em autor
        }

        @Test
        @DisplayName("Deve retornar 400 quando corpo da requisição é nulo")
        void cadastrar_retorna400_bodyNulo() throws Exception {
            mockMvc.perform(post("/livros")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"titulo\":null,\"autor\":null}"))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.campos").exists());
        }
    }

    // ----------------------------------------------------------------
    // GET /livros  (paginado)
    // ----------------------------------------------------------------

    @Nested
    @DisplayName("GET /livros — Listar Todos (paginado)")
    class ListarTodos {

        @Test
        @DisplayName("Deve retornar 200 com página contendo content, totalElements e totalPages")
        void listar_retorna200_estruturaDePagina() throws Exception {
            // Espelha: GET — Listar Todos (paginado)
            var dtos = List.of(
                    new LivroDTO(1L, "Clean Code",    "Robert C. Martin"),
                    new LivroDTO(2L, "Design Patterns","Gang of Four"),
                    new LivroDTO(3L, "Effective Java", "Joshua Bloch")
            );
            var pagina = new PageImpl<>(dtos, PageRequest.of(0, 5, Sort.by("titulo")), 3);

            when(livroService.listarTodos(any(PageRequest.class))).thenReturn(pagina);
            when(livroMapper.toResponse(any(LivroDTO.class))).thenAnswer(inv -> {
                LivroDTO d = inv.getArgument(0);
                return new LivroResponse(d.getId(), d.getTitulo(), d.getAutor());
            });

            mockMvc.perform(get("/livros")
                            .param("page", "0")
                            .param("size", "5")
                            .param("sort", "titulo"))
                    .andExpect(status().isOk())                              // 200
                    .andExpect(jsonPath("$.content").isArray())              // content é array
                    .andExpect(jsonPath("$.totalElements").exists())         // totalElements presente
                    .andExpect(jsonPath("$.totalPages").exists());           // totalPages presente
        }

        @Test
        @DisplayName("Deve retornar lista não vazia (totalElements > 0 e content não vazio)")
        void listar_retorna200_listaNaoVazia() throws Exception {
            // Espelha: test 'Lista não está vazia'
            var dtos = List.of(new LivroDTO(1L, "Livro A", "Autor"));
            var pagina = new PageImpl<>(dtos, PageRequest.of(0, 5), 1);

            when(livroService.listarTodos(any(PageRequest.class))).thenReturn(pagina);
            when(livroMapper.toResponse(any(LivroDTO.class)))
                    .thenReturn(new LivroResponse(1L, "Livro A", "Autor"));

            mockMvc.perform(get("/livros").param("page", "0").param("size", "5"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.content", hasSize(greaterThan(0))))    // content não vazio
                    .andExpect(jsonPath("$.totalElements", greaterThan(0)));      // totalElements > 0
        }
    }

    // ----------------------------------------------------------------
    // GET /livros/{id}
    // ----------------------------------------------------------------

    @Nested
    @DisplayName("GET /livros/{id} — Buscar por ID")
    class BuscarPorId {

        @Test
        @DisplayName("Deve retornar 200 com id, titulo e autor não nulos para ID existente")
        void buscarPorId_retorna200_camposNaoNulos() throws Exception {
            // Espelha: GET — Buscar por ID
            LivroDTO      dto      = new LivroDTO(1L, "Effective Java", "Joshua Bloch");
            LivroResponse response = new LivroResponse(1L, "Effective Java", "Joshua Bloch");

            when(livroService.buscarPorId(1L)).thenReturn(dto);
            when(livroMapper.toResponse(dto)).thenReturn(response);

            mockMvc.perform(get("/livros/1"))
                    .andExpect(status().isOk())                              // 200
                    .andExpect(jsonPath("$.id").isNotEmpty())                // id não nulo
                    .andExpect(jsonPath("$.titulo").isNotEmpty())            // titulo não nulo/vazio
                    .andExpect(jsonPath("$.autor").isNotEmpty());            // autor não nulo/vazio
        }

        @Test
        @DisplayName("Deve retornar 404 com mensagem contendo o id para ID inexistente")
        void buscarPorId_retorna404_idInexistente() throws Exception {
            // Espelha: GET — Buscar por ID inexistente (404)
            when(livroService.buscarPorId(9999L))
                    .thenThrow(new EntityNotFoundException("Livro com id 9999 não encontrado"));

            mockMvc.perform(get("/livros/9999"))
                    .andExpect(status().isNotFound())                        // 404
                    .andExpect(jsonPath("$.mensagem", containsString("9999"))); // mensagem com o id
        }
    }

    // ----------------------------------------------------------------
    // GET /livros/titulo
    // ----------------------------------------------------------------

    @Nested
    @DisplayName("GET /livros/titulo — Buscar por Título")
    class BuscarPorTitulo {

        @Test
        @DisplayName("Deve retornar 200 com array não vazio ao buscar por título existente")
        void buscarPorTitulo_retorna200_arrayNaoVazio() throws Exception {
            // Espelha: GET — Buscar por Título (todos os tests do endpoint novo)
            var dtos = List.of(
                    new LivroDTO(1L, "Effective Java",             "Joshua Bloch"),
                    new LivroDTO(2L, "Effective Java — 3ª Edição", "Joshua Bloch")
            );

            when(livroService.buscarPorTitulo("Effective")).thenReturn(dtos);
            when(livroMapper.toResponse(any(LivroDTO.class))).thenAnswer(inv -> {
                LivroDTO d = inv.getArgument(0);
                return new LivroResponse(d.getId(), d.getTitulo(), d.getAutor());
            });

            mockMvc.perform(get("/livros/titulo").param("titulo", "Effective"))
                    .andExpect(status().isOk())                              // 200
                    .andExpect(jsonPath("$").isArray())                      // é um array
                    .andExpect(jsonPath("$", hasSize(greaterThan(0))))       // lista não vazia
                    .andExpect(jsonPath("$[0].titulo",
                            containsStringIgnoringCase("effective")))       // títulos contêm a busca
                    .andExpect(jsonPath("$[1].titulo",
                            containsStringIgnoringCase("effective")));
        }

        @Test
        @DisplayName("Deve retornar 200 com array vazio quando nenhum livro é encontrado")
        void buscarPorTitulo_retorna200_arrayVazio() throws Exception {
            when(livroService.buscarPorTitulo("TituloInexistente")).thenReturn(List.of());
            when(livroMapper.toResponse(any())).thenReturn(null);

            mockMvc.perform(get("/livros/titulo").param("titulo", "TituloInexistente"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$", hasSize(0)));
        }

        @Test
        @DisplayName("Todos os livros retornados devem conter o termo buscado no título (case-insensitive)")
        void buscarPorTitulo_todosResultadosContemTermo() throws Exception {
            // Espelha: 'Livros encontrados contêm o título buscado'
            var dtos = List.of(
                    new LivroDTO(1L, "Effective Java",             "Joshua Bloch"),
                    new LivroDTO(2L, "effective patterns",         "Autor B"),
                    new LivroDTO(3L, "EFFECTIVE Programming",      "Autor C")
            );

            when(livroService.buscarPorTitulo("effective")).thenReturn(dtos);
            when(livroMapper.toResponse(any(LivroDTO.class))).thenAnswer(inv -> {
                LivroDTO d = inv.getArgument(0);
                return new LivroResponse(d.getId(), d.getTitulo(), d.getAutor());
            });

            mockMvc.perform(get("/livros/titulo").param("titulo", "effective"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[*].titulo",
                            everyItem(containsStringIgnoringCase("effective")))); // todos contêm
        }
    }

    // ----------------------------------------------------------------
    // PUT /livros/{id}
    // ----------------------------------------------------------------

    @Nested
    @DisplayName("PUT /livros/{id} — Atualizar Livro")
    class AtualizarLivro {

        @Test
        @DisplayName("Deve retornar 200 com título atualizado e mesmo id")
        void atualizar_retorna200_tituloAtualizadoEMesmoId() throws Exception {
            // Espelha: PUT — Atualizar Livro (ambos os tests: título atualizado + ID permanece)
            LivroRequest  request  = new LivroRequest("Effective Java — 3ª Edição", "Joshua Bloch");
            LivroDTO      dto      = new LivroDTO(null, "Effective Java — 3ª Edição", "Joshua Bloch");
            LivroDTO      atualizado = new LivroDTO(1L,  "Effective Java — 3ª Edição", "Joshua Bloch");
            LivroResponse response  = new LivroResponse(1L, "Effective Java — 3ª Edição", "Joshua Bloch");

            when(livroMapper.toDTO(any(LivroRequest.class))).thenReturn(dto);
            when(livroService.atualizar(eq(1L), any(LivroDTO.class))).thenReturn(atualizado);
            when(livroMapper.toResponse(atualizado)).thenReturn(response);

            mockMvc.perform(put("/livros/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isOk())                                            // 200
                    .andExpect(jsonPath("$.titulo").value("Effective Java — 3ª Edição"))   // título atualizado
                    .andExpect(jsonPath("$.id").value(1));                                 // mesmo id
        }

        @Test
        @DisplayName("Deve retornar 404 ao tentar atualizar ID inexistente")
        void atualizar_retorna404_idInexistente() throws Exception {
            LivroDTO dto = new LivroDTO(null, "Qualquer Titulo", "Autor");

            when(livroMapper.toDTO(any(LivroRequest.class))).thenReturn(dto);
            when(livroService.atualizar(eq(9999L), any(LivroDTO.class)))
                    .thenThrow(new EntityNotFoundException("Livro com id 9999 não encontrado"));

            mockMvc.perform(put("/livros/9999")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"titulo\":\"Qualquer Titulo\",\"autor\":\"Autor\"}"))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.mensagem", containsString("9999")));
        }
    }

    // ----------------------------------------------------------------
    // DELETE /livros/{id}
    // ----------------------------------------------------------------

    @Nested
    @DisplayName("DELETE /livros/{id} — Deletar Livro")
    class DeletarLivro {

        @Test
        @DisplayName("Deve retornar 204 com body vazio ao deletar livro existente")
        void deletar_retorna204_bodyVazio() throws Exception {
            // Espelha: DELETE — Deletar Livro
            doNothing().when(livroService).deletar(1L);

            mockMvc.perform(delete("/livros/1"))
                    .andExpect(status().isNoContent())          // 204
                    .andExpect(content().string(isEmptyString())); // body vazio
        }

        @Test
        @DisplayName("Deve retornar 404 ao tentar deletar ID inexistente")
        void deletar_retorna404_idInexistente() throws Exception {
            doThrow(new EntityNotFoundException("Livro com id 9999 não encontrado"))
                    .when(livroService).deletar(9999L);

            mockMvc.perform(delete("/livros/9999"))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.mensagem", containsString("9999")));
        }
    }
}
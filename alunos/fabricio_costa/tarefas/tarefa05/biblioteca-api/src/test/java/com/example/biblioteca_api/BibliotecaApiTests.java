package com.example.biblioteca_api;

import com.example.biblioteca_api.controllers.request.LivroRequest;
import com.example.biblioteca_api.controllers.response.LivroResponse;
import com.example.biblioteca_api.dtos.LivroDTO;
import com.example.biblioteca_api.mappers.LivroMapper;
import com.example.biblioteca_api.models.Livro;
import com.example.biblioteca_api.repositories.LivroRepository;
import com.example.biblioteca_api.services.LivroService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Testes de integração para a camada de serviço e repositório.
 *
 * Cobre:
 *  - Mapper (Request → DTO, DTO → Entity, Entity → DTO, DTO → Response)
 *  - Repository (CRUD + busca por título)
 *  - Service (cadastrar, listar, buscar, atualizar, deletar)
 *  - Paginação
 */
@SpringBootTest
@Transactional
@DisplayName("Testes — API Biblioteca")
class BibliotecaApiTests {

    @Autowired LivroService    livroService;
    @Autowired LivroRepository livroRepository;
    @Autowired LivroMapper     livroMapper;

    // ================================================================
    // MAPPER
    // ================================================================

    @Nested
    @DisplayName("LivroMapper")
    class MapperTests {

        @Test
        @DisplayName("Request → DTO deve mapear campos corretamente")
        void requestParaDTO() {
            LivroRequest request = new LivroRequest("Clean Code", "Robert C. Martin");

            LivroDTO dto = livroMapper.toDTO(request);

            assertThat(dto.getTitulo()).isEqualTo("Clean Code");
            assertThat(dto.getAutor()).isEqualTo("Robert C. Martin");
            assertThat(dto.getId()).isNull(); // ID não vem do request
        }

        @Test
        @DisplayName("DTO → Entity deve mapear campos corretamente")
        void dtoParaEntity() {
            LivroDTO dto = new LivroDTO(null, "Domain-Driven Design", "Eric Evans");

            Livro entity = livroMapper.toEntity(dto);

            assertThat(entity.getTitulo()).isEqualTo("Domain-Driven Design");
            assertThat(entity.getAutor()).isEqualTo("Eric Evans");
        }

        @Test
        @DisplayName("Entity → DTO deve incluir o ID")
        void entityParaDTO() {
            Livro livro = new Livro("Refactoring", "Martin Fowler");
            livro.setId(42L);

            LivroDTO dto = livroMapper.toDTO(livro);

            assertThat(dto.getId()).isEqualTo(42L);
            assertThat(dto.getTitulo()).isEqualTo("Refactoring");
        }

        @Test
        @DisplayName("DTO → Response deve mapear todos os campos")
        void dtoParaResponse() {
            LivroDTO dto = new LivroDTO(1L, "The Pragmatic Programmer", "Andrew Hunt");

            LivroResponse response = livroMapper.toResponse(dto);

            assertThat(response.getId()).isEqualTo(1L);
            assertThat(response.getTitulo()).isEqualTo("The Pragmatic Programmer");
            assertThat(response.getAutor()).isEqualTo("Andrew Hunt");
        }
    }

    // ================================================================
    // REPOSITORY
    // ================================================================

    @Nested
    @DisplayName("LivroRepository")
    class RepositoryTests {

        @Test
        @DisplayName("Deve salvar e recuperar livro pelo ID")
        void salvarERecuperar() {
            Livro livro = livroRepository.save(new Livro("Spring in Action", "Craig Walls"));

            assertThat(livroRepository.findById(livro.getId()))
                    .isPresent()
                    .get()
                    .extracting(Livro::getTitulo)
                    .isEqualTo("Spring in Action");
        }

        @Test
        @DisplayName("Deve buscar livros por título parcial (case-insensitive)")
        void buscarPorTitulo() {
            livroRepository.save(new Livro("Titulo Unico ABC", "Autor 1"));
            livroRepository.save(new Livro("Titulo Unico ABCDEF", "Autor 2"));
            livroRepository.save(new Livro("Outro Completamente Diferente", "Autor 3"));

            List<Livro> resultado =
                    livroRepository.findByTituloContainingIgnoreCase("Titulo Unico ABC");

            assertThat(resultado).hasSize(2);
        }

        @Test
        @DisplayName("Deve paginar corretamente os resultados")
        void paginacao() {
            for (int i = 1; i <= 10; i++) {
                livroRepository.save(new Livro("Livro " + i, "Autor"));
            }

            PageRequest pageable = PageRequest.of(0, 3, Sort.by("titulo"));
            Page<Livro> pagina   = livroRepository.findAll(pageable);

            assertThat(pagina.getContent()).hasSize(3);
            assertThat(pagina.getTotalElements()).isGreaterThanOrEqualTo(10);
            assertThat(pagina.getTotalPages()).isGreaterThanOrEqualTo(4);
        }
    }

    // ================================================================
    // SERVICE
    // ================================================================

    @Nested
    @DisplayName("LivroService")
    class ServiceTests {

        @Test
        @DisplayName("Deve cadastrar um livro e retornar DTO com ID")
        void cadastrarLivro() {
            LivroDTO dto    = new LivroDTO(null, "Design Patterns", "Gang of Four");
            LivroDTO salvo  = livroService.cadastrar(dto);

            assertThat(salvo.getId()).isNotNull();
            assertThat(salvo.getTitulo()).isEqualTo("Design Patterns");
        }

        @Test
        @DisplayName("Deve listar livros paginados")
        void listarPaginado() {
            livroService.cadastrar(new LivroDTO(null, "Livro A", "Autor"));
            livroService.cadastrar(new LivroDTO(null, "Livro B", "Autor"));
            livroService.cadastrar(new LivroDTO(null, "Livro C", "Autor"));

            Page<LivroDTO> pagina =
                    livroService.listarTodos(PageRequest.of(0, 2));

            assertThat(pagina.getContent()).hasSize(2);
            assertThat(pagina.getTotalElements()).isGreaterThanOrEqualTo(3);
        }

        @Test
        @DisplayName("Deve buscar livro por ID existente")
        void buscarPorIdExistente() {
            LivroDTO salvo = livroService.cadastrar(new LivroDTO(null, "Effective Java", "Joshua Bloch"));

            LivroDTO encontrado = livroService.buscarPorId(salvo.getId());

            assertThat(encontrado.getTitulo()).isEqualTo("Effective Java");
        }

        @Test
        @DisplayName("Deve lançar EntityNotFoundException para ID inexistente")
        void buscarPorIdInexistente() {
            assertThatThrownBy(() -> livroService.buscarPorId(9999L))
                    .isInstanceOf(EntityNotFoundException.class)
                    .hasMessageContaining("9999");
        }

        @Test
        @DisplayName("Deve buscar livros por título")
        void buscarPorTitulo() {
            livroService.cadastrar(new LivroDTO(null, "Java Concurrency in Practice", "Brian Goetz"));
            livroService.cadastrar(new LivroDTO(null, "Java Performance", "Scott Oaks"));

            List<LivroDTO> resultado = livroService.buscarPorTitulo("java");

            assertThat(resultado).hasSize(2);
        }

        @Test
        @DisplayName("Deve atualizar título e autor do livro")
        void atualizarLivro() {
            LivroDTO original  = livroService.cadastrar(new LivroDTO(null, "Título Antigo", "Autor Antigo"));
            LivroDTO atualizado = livroService.atualizar(
                    original.getId(),
                    new LivroDTO(null, "Título Novo", "Autor Novo")
            );

            assertThat(atualizado.getTitulo()).isEqualTo("Título Novo");
            assertThat(atualizado.getAutor()).isEqualTo("Autor Novo");
        }

        @Test
        @DisplayName("Deve deletar livro e lançar exceção na busca posterior")
        void deletarLivro() {
            LivroDTO salvo = livroService.cadastrar(new LivroDTO(null, "Para Deletar", "Autor"));

            livroService.deletar(salvo.getId());

            assertThatThrownBy(() -> livroService.buscarPorId(salvo.getId()))
                    .isInstanceOf(EntityNotFoundException.class);
        }

        @Test
        @DisplayName("Deve lançar exceção ao tentar deletar ID inexistente")
        void deletarInexistente() {
            assertThatThrownBy(() -> livroService.deletar(9999L))
                    .isInstanceOf(EntityNotFoundException.class);
        }
    }
}
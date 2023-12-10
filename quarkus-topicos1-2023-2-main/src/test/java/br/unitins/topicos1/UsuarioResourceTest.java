package br.unitins.topicos1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.usuario.UsuarioDTO;
import br.unitins.topicos1.dto.usuario.UsuarioResponseDTO;
import br.unitins.topicos1.service.usuario.UsuarioService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class UsuarioResourceTest {

        @Inject
        UsuarioService usuarioService;

        @Test
        public void testFindAll() {
                given()
                                .when().get("/usuarios")
                                .then()
                                .statusCode(200);
        }

        @Test
        public void testInsert() {
                List<TelefoneDTO> telefones = new ArrayList<TelefoneDTO>();
                telefones.add(new TelefoneDTO("63", "5555-5555"));

                EnderecoDTO endereco = new EnderecoDTO("Estado", "Cidade", "Quadra", "Rua",
                                123);

                UsuarioDTO dto = new UsuarioDTO(
                                "Mark Zuckerberg Insert",
                                "marquinho",
                                "333",
                                1,
                                telefones,
                                endereco);

                given()
                                .contentType(ContentType.JSON)
                                .body(dto)
                                .when().post("/usuarios")
                                .then()
                                .statusCode(201)
                                .body(
                                                "id", notNullValue(),
                                                "nome", is("Mark Zuckerberg Insert"),
                                                "login", is("marquinho"));
        }

        @Test
        public void testUpdate() {
                List<TelefoneDTO> telefones = new ArrayList<TelefoneDTO>();
                telefones.add(new TelefoneDTO("63", "5555-5555"));

                UsuarioDTO novoUsuario = new UsuarioDTO(
                                "Usuário Teste", "usuario_teste", "senha123", 1, telefones,
                                new EnderecoDTO("Estado", "Cidade", "Quadra", "Rua", 123));
                UsuarioResponseDTO usuarioInserido = usuarioService.insert(novoUsuario);

                UsuarioDTO usuarioAtualizado = new UsuarioDTO(
                                "Usuário Teste Atualizado", "usuario_teste", "senha123", 1, new ArrayList<>(),
                                new EnderecoDTO("Estado", "Cidade", "Quadra", "Rua", 123));
                given()
                                .contentType(ContentType.JSON)
                                .body(usuarioAtualizado)
                                .when().put("/usuarios/" + usuarioInserido.id())
                                .then()
                                .statusCode(204);
        }

        @Test
        void testDelete() {
                List<TelefoneDTO> telefones = new ArrayList<TelefoneDTO>();
                telefones.add(new TelefoneDTO("63", "5555-5555"));

                EnderecoDTO endereco = new EnderecoDTO("Estado", "Cidade", "Quadra", "Rua",
                                123);

                UsuarioDTO dto = new UsuarioDTO(
                                "Mark Zuckerberg Delete",
                                "marquinho",
                                "333",
                                1,
                                telefones,
                                endereco);

                UsuarioResponseDTO usuarioTest = usuarioService.insert(dto);
                Long id = usuarioTest.id();

                given()
                                .when().delete("/usuarios/" + id)
                                .then()
                                .statusCode(204);
        }

        @Test
        public void testFindById() {
                Long id = 1L;
                given()
                                .when()
                                .get("/usuarios/" + id)
                                .then()
                                .statusCode(200)
                                .body("id", is(id.intValue()));
        }
}
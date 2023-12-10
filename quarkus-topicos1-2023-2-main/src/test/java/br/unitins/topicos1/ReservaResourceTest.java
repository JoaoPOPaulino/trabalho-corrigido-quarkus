package br.unitins.topicos1;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.reserva.ReservaDTO;
import br.unitins.topicos1.dto.reserva.ReservaResponseDTO;
import br.unitins.topicos1.service.quarto.QuartoService;
import br.unitins.topicos1.service.reserva.ReservaService;
import br.unitins.topicos1.service.usuario.UsuarioService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class ReservaResourceTest {

        @Inject
        ReservaService reservaService;

        @Inject
        UsuarioService usuarioService;

        @Inject
        QuartoService quartoService;

        @Test
        public void testFindAll() {
                given()
                                .when().get("/reservas")
                                .then()
                                .statusCode(200);
        }

        @Test
        public void testInsert() {

                Long idQuarto = 1L;
                Long idUsuario = 1L;

                LocalDate dataInicio = LocalDate.now();
                LocalDate dataFim = dataInicio.plusDays(5);
                ReservaDTO dto = new ReservaDTO(dataInicio, dataFim, idQuarto,
                                idUsuario);

                given()
                                .contentType(ContentType.JSON)
                                .body(dto)
                                .when().post("/reservas")
                                .then()
                                .statusCode(201)
                                .body("dataInicio", notNullValue())
                                .body("dataFim", notNullValue())
                                .body("preco", equalTo(150.0F));
        }

        @Test
        public void testUpdate() {

                Long idQuarto = 1L;
                Long idUsuario = 1L;

                LocalDate dataInicio = LocalDate.now();
                LocalDate dataFim = dataInicio.plusDays(5);
                ReservaDTO dtoInsert = new ReservaDTO(dataInicio, dataFim, idQuarto,
                                idUsuario);

                ReservaResponseDTO reservaTest = reservaService.insert(dtoInsert);
                Long id = reservaTest.id();

                LocalDate newDataFim = dataFim.plusDays(3);
                ReservaDTO dtoUpdate = new ReservaDTO(dataInicio, newDataFim, idQuarto,
                                idUsuario);
                given()
                                .contentType(ContentType.JSON)
                                .body(dtoUpdate)
                                .when().put("/reservas/" + id)
                                .then()
                                .statusCode(204);
        }

        @Test
        public void testDelete() {

                Long idQuarto = 1L;
                Long idUsuario = 1L;

                LocalDate dataInicio = LocalDate.now();
                LocalDate dataFim = dataInicio.plusDays(5);
                ReservaDTO dtoInsert = new ReservaDTO(dataInicio, dataFim, idQuarto,
                                idUsuario);
                ReservaResponseDTO reservaTest = reservaService.insert(dtoInsert);

                Long id = reservaTest.id();
                given()
                                .when().delete("/reservas/" + id)
                                .then()
                                .statusCode(204);

                given()
                                .when().get("/reservas/" + id)
                                .then()
                                .statusCode(404);
        }
}

// @Test
// public void testHistoricoReservas() {
// given()
// .auth().preemptive()
// .oauth2("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c")
// .when().get("/reservas/historico")
// .then()
// .statusCode(200);
// }

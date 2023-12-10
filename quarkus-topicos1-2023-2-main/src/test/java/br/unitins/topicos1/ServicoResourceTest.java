package br.unitins.topicos1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.servico.ServicoDTO;
import br.unitins.topicos1.dto.servico.ServicoResponseDTO;
import br.unitins.topicos1.service.servico.ServicoService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class ServicoResourceTest {

        @Inject
        ServicoService servicoService;

        @Test
        public void testFindAll() {
                given()
                                .when().get("/servico")
                                .then()
                                .statusCode(200);
        }

        @Test
        public void testInsert() {
                LocalTime horaInicio = LocalTime.of(7, 30);
                LocalTime horaFim = LocalTime.of(10, 45);

                ServicoDTO dto = new ServicoDTO(
                                "Café da Manhã",
                                "Serviço de café da manhã",
                                horaInicio,
                                horaFim);

                given()
                                .contentType(ContentType.JSON)
                                .body(dto)
                                .when().post("/servico")
                                .then()
                                .statusCode(201)
                                .body(
                                                "id", notNullValue(),
                                                "nome", is("Café da Manhã"),
                                                "descricao", is("Serviço de café da manhã"));
        }

        @Test
        public void testUpdate() {
                LocalTime horaInicio = LocalTime.of(7, 30);
                LocalTime horaFim = LocalTime.of(10, 45);

                ServicoDTO dtoInsert = new ServicoDTO(
                                "Café da Manhã",
                                "Descrição Original",
                                horaInicio,
                                horaFim);

                ServicoResponseDTO servicoTest = servicoService.insert(dtoInsert);
                Long id = servicoTest.id();

                LocalTime horaInicioUpdate = LocalTime.of(8, 30);
                LocalTime horaFimUpdate = LocalTime.of(11, 00);

                ServicoDTO dtoUpdate = new ServicoDTO(
                                "Café da Manhã Atualizado",
                                "Descrição Atualizada",
                                horaInicioUpdate,
                                horaFimUpdate);

                given()
                                .contentType(ContentType.JSON)
                                .body(dtoUpdate)
                                .when().put("/servico/" + id)
                                .then()
                                .statusCode(204);
        }

        @Test
        void testDelete() {

                LocalTime horaInicio = LocalTime.of(8, 30);
                LocalTime horaFim = LocalTime.of(11, 00);

                ServicoDTO dto = new ServicoDTO(
                                "Café da Manhã",
                                "Serviço de café da manhã",
                                horaInicio,
                                horaFim);

                ServicoResponseDTO servicoTest = servicoService.insert(dto);
                Long id = servicoTest.id();

                given()
                                .when().delete("/servico/" + id)
                                .then()
                                .statusCode(204);
        }

}

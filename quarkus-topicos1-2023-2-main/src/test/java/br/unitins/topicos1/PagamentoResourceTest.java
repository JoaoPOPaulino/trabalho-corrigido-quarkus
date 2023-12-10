package br.unitins.topicos1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.pagamento.PagamentoDTO;
import br.unitins.topicos1.dto.pagamento.PagamentoResponseDTO;
import br.unitins.topicos1.dto.quarto.QuartoDTO;
import br.unitins.topicos1.dto.quarto.QuartoResponseDTO;
import br.unitins.topicos1.dto.reserva.ReservaDTO;
import br.unitins.topicos1.dto.reserva.ReservaResponseDTO;
import br.unitins.topicos1.dto.tipo.TipoPagamentoDTO;
import br.unitins.topicos1.dto.tipo.TipoQuartoDTO;
import br.unitins.topicos1.dto.usuario.UsuarioDTO;
import br.unitins.topicos1.dto.usuario.UsuarioResponseDTO;
import br.unitins.topicos1.service.pagamento.PagamentoService;
import br.unitins.topicos1.service.quarto.QuartoService;
import br.unitins.topicos1.service.reserva.ReservaService;
import br.unitins.topicos1.service.usuario.UsuarioService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class PagamentoResourceTest {

        @Inject
        PagamentoService pagamentoService;

        @Inject
        ReservaService reservaService;

        @Inject
        QuartoService quartoService;

        @Inject
        UsuarioService usuarioService;

        @Test
        public void testFindAll() {
                given()
                                .when().get("/pagamentos")
                                .then()
                                .statusCode(200);
        }

        @Test
        public void testInsert() {

                TipoQuartoDTO tipoQuarto = new TipoQuartoDTO(1, "Casual");
                QuartoDTO quartoDTO = new QuartoDTO(1, 150.0, true, tipoQuarto);
                QuartoResponseDTO quartoCriado = quartoService.insert(quartoDTO);

                List<TelefoneDTO> telefones = new ArrayList<>();
                telefones.add(new TelefoneDTO("63", "5555-5555"));
                EnderecoDTO endereco = new EnderecoDTO("Estado", "Cidade", "Quadra", "Rua",
                                123);
                UsuarioDTO usuarioDTO = new UsuarioDTO("Mark Zuckerberg Update", "GH",
                                "333", 1, telefones,
                                endereco);
                UsuarioResponseDTO usuarioInsert = usuarioService.insert(usuarioDTO);

                LocalDate dataInicio = LocalDate.now();
                LocalDate dataFim = dataInicio.plusDays(5);
                ReservaDTO reservaDTO = new ReservaDTO(dataInicio, dataFim,
                                quartoCriado.id(), usuarioInsert.id());
                ReservaResponseDTO reservaCriada = reservaService.insert(reservaDTO);

                TipoPagamentoDTO tipoPagamento = new TipoPagamentoDTO(1, "Cartão de Crédito");
                LocalDateTime dataPagamento = LocalDateTime.now();
                PagamentoDTO pagamentoDTO = new PagamentoDTO(dataPagamento,
                                reservaCriada.id(), tipoPagamento);

                given()
                                .contentType(ContentType.JSON)
                                .body(pagamentoDTO)
                                .when().post("/pagamentos")
                                .then()
                                .statusCode(201)
                                .body("dataPagamento", notNullValue())
                                .body("idReserva", equalTo(reservaCriada.id().intValue()))
                                .body("valor", equalTo(150.0F));
        }

        @Test
        public void testUpdate() {
                TipoQuartoDTO tipoQuarto = new TipoQuartoDTO(1, "Casual");
                QuartoDTO quartoDTO = new QuartoDTO(1, 150.0, true, tipoQuarto);
                QuartoResponseDTO quartoCriado = quartoService.insert(quartoDTO);

                List<TelefoneDTO> telefones = new ArrayList<>();
                telefones.add(new TelefoneDTO("63", "5555-5555"));
                EnderecoDTO endereco = new EnderecoDTO("Estado", "Cidade", "Quadra", "Rua",
                                123);
                UsuarioDTO usuarioDTO = new UsuarioDTO("Mark Zuckerberg Update", "Marq",
                                "333", 1, telefones,
                                endereco);
                UsuarioResponseDTO usuarioUpdate = usuarioService.insert(usuarioDTO);

                LocalDate dataInicio = LocalDate.now();
                LocalDate dataFim = dataInicio.plusDays(5);
                ReservaDTO reservaDTO = new ReservaDTO(dataInicio, dataFim,
                                quartoCriado.id(), usuarioUpdate.id());
                ReservaResponseDTO reservaCriada = reservaService.insert(reservaDTO);

                TipoPagamentoDTO tipoPagamento = new TipoPagamentoDTO(1, "Cartão de Crédito");
                LocalDateTime dataPagamento = LocalDateTime.now();
                PagamentoDTO pagamentoDTO = new PagamentoDTO(dataPagamento,
                                reservaCriada.id(), tipoPagamento);

                PagamentoResponseDTO pagamentoTest = pagamentoService.insert(pagamentoDTO);

                Long id = pagamentoTest.id();

                TipoPagamentoDTO novoTipoPagamento = new TipoPagamentoDTO(2, "Dinheiro");
                LocalDateTime novaDataPagamento = LocalDateTime.now().plusHours(1);

                PagamentoDTO pagamentoAtualizado = new PagamentoDTO(novaDataPagamento,
                                pagamentoDTO.idReserva(),
                                novoTipoPagamento);

                given()
                                .contentType(ContentType.JSON)
                                .body(pagamentoAtualizado)
                                .when().put("/pagamentos/" + id)
                                .then()
                                .statusCode(204);
        }

        @Test
        public void testDelete() {
                TipoQuartoDTO tipoQuarto = new TipoQuartoDTO(1, "Casual");
                QuartoDTO quartoDTO = new QuartoDTO(1, 150.0, true, tipoQuarto);
                QuartoResponseDTO quartoCriado = quartoService.insert(quartoDTO);

                List<TelefoneDTO> telefones = new ArrayList<>();
                telefones.add(new TelefoneDTO("63", "5555-5555"));
                EnderecoDTO endereco = new EnderecoDTO("Estado", "Cidade", "Quadra", "Rua",
                                123);
                UsuarioDTO usuarioDTO = new UsuarioDTO("Mark Zuckerberg Delete", "MarkD",
                                "333", 1, telefones,
                                endereco);
                UsuarioResponseDTO usuarioCriado = usuarioService.insert(usuarioDTO);

                LocalDate dataInicio = LocalDate.now();
                LocalDate dataFim = dataInicio.plusDays(5);
                ReservaDTO reservaDTO = new ReservaDTO(dataInicio, dataFim,
                                quartoCriado.id(), usuarioCriado.id());
                ReservaResponseDTO reservaCriada = reservaService.insert(reservaDTO);

                TipoPagamentoDTO tipoPagamento = new TipoPagamentoDTO(1, "Cartão de Crédito");
                LocalDateTime dataPagamento = LocalDateTime.now();
                PagamentoDTO pagamentoDTO = new PagamentoDTO(dataPagamento,
                                reservaCriada.id(), tipoPagamento);
                PagamentoResponseDTO pagamentoCriado = pagamentoService.insert(pagamentoDTO);

                Long id = pagamentoCriado.id();

                given()
                                .when().delete("/pagamentos/" + id)
                                .then()
                                .statusCode(204);
                given()
                                .when().get("/pagamentos/" + id)
                                .then()
                                .statusCode(404);
        }

        @Test
        public void testFindById() {
                TipoQuartoDTO tipoQuarto = new TipoQuartoDTO(1, "Casual");
                QuartoDTO quartoDTO = new QuartoDTO(1, 150.0, true, tipoQuarto);
                QuartoResponseDTO quartoCriado = quartoService.insert(quartoDTO);

                List<TelefoneDTO> telefones = new ArrayList<>();
                telefones.add(new TelefoneDTO("63", "5555-5555"));
                EnderecoDTO endereco = new EnderecoDTO("Estado", "Cidade", "Quadra", "Rua",
                                123);
                UsuarioDTO usuarioDTO = new UsuarioDTO("Mark Zuckerberg Update", "marquinho",
                                "333", 1, telefones,
                                endereco);
                UsuarioResponseDTO usuarioCriado = usuarioService.insert(usuarioDTO);

                LocalDate dataInicio = LocalDate.now();
                LocalDate dataFim = dataInicio.plusDays(5);
                ReservaDTO reservaDTO = new ReservaDTO(dataInicio, dataFim,
                                quartoCriado.id(), usuarioCriado.id());
                ReservaResponseDTO reservaCriada = reservaService.insert(reservaDTO);

                TipoPagamentoDTO tipoPagamento = new TipoPagamentoDTO(1, "Cartão de Crédito");
                LocalDateTime dataPagamento = LocalDateTime.now();
                PagamentoDTO pagamentoDTO = new PagamentoDTO(dataPagamento,
                                reservaCriada.id(), tipoPagamento);

                PagamentoResponseDTO pagamentoCriado = pagamentoService.insert(pagamentoDTO);
                Long id = pagamentoCriado.id();

                given()
                                .when().get("/pagamentos/" + id)
                                .then()
                                .statusCode(200)
                                .body("id", equalTo(id.intValue()));
        }

}

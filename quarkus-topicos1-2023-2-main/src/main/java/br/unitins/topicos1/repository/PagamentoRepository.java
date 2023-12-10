package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Pagamento;
import br.unitins.topicos1.model.TipoPagamento;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PagamentoRepository implements PanacheRepository<Pagamento> {

    public List<Pagamento> findByReservaId(Long reservaId) {
        return list("reserva.id", reservaId);
    }

    public List<Pagamento> findByTipoPagamento(TipoPagamento tipoPagamento) {
        return list("tipoPagamento", tipoPagamento);
    }
}

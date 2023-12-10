package br.unitins.topicos1.repository;

import java.util.List;
import br.unitins.topicos1.model.Reserva;
import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class ReservaRepository implements PanacheRepositoryBase<Reserva, Long> {
    public List<Reserva> findByUsuario(Long usuarioId) {
        return list("usuario.id", usuarioId);
    }

}

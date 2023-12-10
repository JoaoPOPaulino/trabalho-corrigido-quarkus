package br.unitins.topicos1.repository;

import java.time.LocalDateTime;
import java.util.List;

import br.unitins.topicos1.model.Comentario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ComentarioRepository implements PanacheRepository<Comentario> {
    public List<Comentario> findComentariosByData(LocalDateTime data) {
        return list("dataCriacao >= ?1 AND dataCriacao < ?2", data, data.plusDays(1));
    }
}

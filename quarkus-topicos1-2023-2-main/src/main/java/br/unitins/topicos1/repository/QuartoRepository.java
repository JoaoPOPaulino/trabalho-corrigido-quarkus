package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Quarto;
import br.unitins.topicos1.model.TipoQuarto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QuartoRepository implements PanacheRepository<Quarto> {

    public List<Quarto> findByTipo(TipoQuarto tipoQuarto) {
        return find("tipoQuarto", tipoQuarto).list();
    }

}
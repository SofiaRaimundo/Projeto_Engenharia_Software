package pt.ufp.info.esof.projeto.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ufp.info.esof.projeto.models.TarefaEfetiva;

import java.util.Optional;

@Repository
public interface TarefaEfetivaRepository extends CrudRepository<TarefaEfetiva, Long> {
    Optional<TarefaEfetiva> findByNome(String nome);
}

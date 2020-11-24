package pt.ufp.info.esof.projeto.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ufp.info.esof.projeto.models.Tarefa;

@Repository
public interface TarefaPrevistaRepository extends CrudRepository<Tarefa, Long> {

}

package pt.ufp.info.esof.projeto.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ufp.info.esof.projeto.models.GestorProjeto;

@Repository
public interface GestorProjetoRepository extends CrudRepository<GestorProjeto, Long> {

}

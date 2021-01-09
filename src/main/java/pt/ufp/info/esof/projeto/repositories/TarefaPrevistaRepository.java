package pt.ufp.info.esof.projeto.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;

@Repository
public interface TarefaPrevistaRepository extends CrudRepository<TarefaPrevista, Long> {
    TarefaPrevista findByNome(String nome);

    TarefaPrevista findByProjeto(Projeto projeto);

    TarefaPrevista findByEmpregado(Empregado empregado);
}
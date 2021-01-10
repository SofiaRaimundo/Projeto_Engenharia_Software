package pt.ufp.info.esof.projeto.services;

import pt.ufp.info.esof.projeto.models.TarefaEfetiva;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import java.util.List;
import java.util.Optional;

public interface TarefaPrevistaService {
    List<TarefaPrevista> findAll();

    Optional<TarefaPrevista> findById(Long id);

    Optional<TarefaPrevista> criarTarefaPrevista(TarefaPrevista converter);

    Optional<TarefaPrevista> adicionaTarefaEfetiva(Long tarefaPrevistaId, TarefaEfetiva tarefaEfetiva);
}
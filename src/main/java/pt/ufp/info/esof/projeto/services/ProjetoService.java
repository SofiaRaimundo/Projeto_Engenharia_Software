package pt.ufp.info.esof.projeto.services;

import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import java.util.List;
import java.util.Optional;

public interface ProjetoService {
    List<Projeto> findAll();

    Optional<Projeto> findById(Long id);

    Optional<Projeto> criarProjeto(Projeto converter);

    Optional<Projeto> adicionaTarefaPrevista(Long projetoId, TarefaPrevista tarefaPrevista);
}
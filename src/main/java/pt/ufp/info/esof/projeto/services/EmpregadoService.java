package pt.ufp.info.esof.projeto.services;

import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import java.util.List;
import java.util.Optional;

public interface EmpregadoService {
    List<Empregado> findAll();

    Optional<Empregado> findById(Long id);

    Optional<Empregado> criarEmpregado(Empregado converter);

    Optional<Empregado> adicionaTarefaPrevista(Long empregadoId, TarefaPrevista tarefaPrevista);
}
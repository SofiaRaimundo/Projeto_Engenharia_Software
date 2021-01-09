package pt.ufp.info.esof.projeto.services;

import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;

import java.util.List;
import java.util.Optional;

public interface TarefaPrevistaService {
    List<TarefaPrevista> findAll();

    Optional<TarefaPrevista> findByNome(String nome);
    Optional<TarefaPrevista> findByProjeto(Projeto projeto);
    Optional<TarefaPrevista> findByEmpregado(Empregado empregado);

}

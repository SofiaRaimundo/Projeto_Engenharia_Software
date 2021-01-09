package pt.ufp.info.esof.projeto.services;

import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.models.TarefaEfetiva;

import java.util.List;
import java.util.Optional;

public interface ProjetoService {
    List<Projeto> findAll();

    Optional<Projeto> findByNome(String nome);
}

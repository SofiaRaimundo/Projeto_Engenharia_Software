package pt.ufp.info.esof.projeto.services;

import pt.ufp.info.esof.projeto.models.TarefaEfetiva;

import java.util.List;
import java.util.Optional;

public interface TarefaEfetivaService {
    List<TarefaEfetiva> findAll();

    Optional<TarefaEfetiva> findByNome(String nome);
    Optional<TarefaEfetiva> createTarefaEfetiva (TarefaEfetiva converter);
}

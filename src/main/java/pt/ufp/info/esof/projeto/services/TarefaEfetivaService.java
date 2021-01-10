package pt.ufp.info.esof.projeto.services;

import pt.ufp.info.esof.projeto.models.TarefaEfetiva;

import java.nio.file.LinkOption;
import java.util.List;
import java.util.Optional;

public interface TarefaEfetivaService {
    List<TarefaEfetiva> findAll();

    Optional<TarefaEfetiva> findById(Long id);

    Optional<TarefaEfetiva> criarTarefaEfetiva (TarefaEfetiva converter);
}
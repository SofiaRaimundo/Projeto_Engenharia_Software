package pt.ufp.info.esof.projeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.TarefaEfetiva;
import pt.ufp.info.esof.projeto.repositories.TarefaEfetivaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaEfetivaServiceImpl implements TarefaEfetivaService {

    private TarefaEfetivaRepository tarefaEfetivaRepository;

    @Autowired
    public TarefaEfetivaServiceImpl(TarefaEfetivaRepository tarefaEfetivaRepository) {
        this.tarefaEfetivaRepository = tarefaEfetivaRepository;
    }

    @Override
    public List<TarefaEfetiva> findAll() {
        List<TarefaEfetiva> tarefasEfetivas = new ArrayList<>(); //cria uma lista de tarefas efetivas
        tarefaEfetivaRepository.findAll().forEach(tarefasEfetivas::add); //vai ao repositório buscar as tarefas efetivas e adiciona na lista criada
        return tarefasEfetivas;
    }

    @Override
    public Optional<TarefaEfetiva> findById(Long id) {
        return tarefaEfetivaRepository.findById(id); //retorna a tarefa efetiva encontrada
    }

    @Override
    public Optional<TarefaEfetiva> criarTarefaEfetiva(TarefaEfetiva tarefaEfetiva) {
        Optional<TarefaEfetiva> optionalTarefaEfetiva = tarefaEfetivaRepository.findById(tarefaEfetiva.getId());
        if(optionalTarefaEfetiva.isEmpty()){
            tarefaEfetivaRepository.save(tarefaEfetiva);
            return Optional.of(tarefaEfetivaRepository.save(tarefaEfetiva));
        }
        return Optional.empty();
    }
}

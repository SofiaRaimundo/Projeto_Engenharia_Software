package pt.ufp.info.esof.projeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.models.TarefaEfetiva;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import pt.ufp.info.esof.projeto.repositories.TarefaEfetivaRepository;
import pt.ufp.info.esof.projeto.repositories.TarefaPrevistaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaPrevistaServiceImpl implements TarefaPrevistaService {

    private TarefaPrevistaRepository tarefaPrevistaRepository;
    private TarefaEfetivaRepository tarefaEfetivaRepository;

    @Autowired
    public TarefaPrevistaServiceImpl(TarefaPrevistaRepository tarefaPrevistaRepository, TarefaEfetivaRepository tarefaEfetivaRepository) {
        this.tarefaPrevistaRepository = tarefaPrevistaRepository;
        this.tarefaEfetivaRepository = tarefaEfetivaRepository;
    }

    @Override
    public List<TarefaPrevista> findAll() {
        List<TarefaPrevista> tarefasPrevistas = new ArrayList<>(); //cria uma lista de tarefas previstas
        tarefaPrevistaRepository.findAll().forEach(tarefasPrevistas::add); //vai ao repositório buscar as tarefas previstas e adiciona na lista criada
        return tarefasPrevistas;
    }

    @Override
    public Optional<TarefaPrevista> findById(Long id) {
        return tarefaPrevistaRepository.findById(id); //retorna a tarefa prevista encontrada
    }

    @Override
    public Optional<TarefaPrevista> criarTarefaPrevista(TarefaPrevista tarefaPrevista) {
        Optional<TarefaPrevista> optionalTarefaPrevista = tarefaPrevistaRepository.findById(tarefaPrevista.getId());
        if(optionalTarefaPrevista.isEmpty()){
            tarefaPrevistaRepository.save(tarefaPrevista);
            return Optional.of(tarefaPrevistaRepository.save(tarefaPrevista));
        }
        return Optional.empty();
    }

    @Override
    public Optional<TarefaPrevista> adicionaTarefaEfetiva(Long tarefaPrevistaId, TarefaEfetiva tarefaEfetiva) {
        Optional<TarefaPrevista> optionalTarefaPrevista = this.tarefaPrevistaRepository.findById(tarefaPrevistaId);
        if(optionalTarefaPrevista.isPresent()){
            TarefaPrevista tarefaPrevista = optionalTarefaPrevista.get();
            int quantidadeDeTarefasAntes = tarefaPrevista.getTarefasEfetivas().size();
            tarefaPrevista.adicionaTarefaEfetiva(tarefaEfetiva);
            int quantidadedeTarefasDepois = tarefaPrevista.getTarefasEfetivas().size();
            if(quantidadeDeTarefasAntes != quantidadedeTarefasDepois) {
                return Optional.of(tarefaPrevista);
            }
        }
        return Optional.empty();
    }
}

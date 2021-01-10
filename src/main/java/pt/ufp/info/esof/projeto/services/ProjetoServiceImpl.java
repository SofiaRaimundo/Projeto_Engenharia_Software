package pt.ufp.info.esof.projeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import pt.ufp.info.esof.projeto.repositories.ProjetoRepository;
import pt.ufp.info.esof.projeto.repositories.TarefaPrevistaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjetoServiceImpl implements ProjetoService {

    private ProjetoRepository projetoRepository;
    private TarefaPrevistaRepository tarefaPrevistaRepository;

    @Autowired
    public ProjetoServiceImpl(ProjetoRepository projetoRepository, TarefaPrevistaRepository tarefaPrevistaRepository) {
        this.projetoRepository = projetoRepository;
        this.tarefaPrevistaRepository = tarefaPrevistaRepository;
    }

    @Override
    public List<Projeto> findAll() {
        List<Projeto> projetos = new ArrayList<>(); //cria uma lista de projetos
        projetoRepository.findAll().forEach(projetos::add); //vai ao repositório buscar os projetos e adiciona na lista criada
        return projetos;
    }

    @Override
    public Optional<Projeto> findById(Long id) {
        return projetoRepository.findById(id); //retorna o projeto encontrado
    }

    @Override
    public Optional<Projeto> criarProjeto(Projeto projeto) {
        Optional<Projeto> optionalProjeto = projetoRepository.findById(projeto.getId());
        if(optionalProjeto.isEmpty()){
            projetoRepository.save(projeto);
            return Optional.of(projetoRepository.save(projeto));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Projeto> adicionaTarefaPrevista(Long projetoId, TarefaPrevista tarefaPrevista) {
        Optional<Projeto> optionalProjeto = this.projetoRepository.findById(projetoId);
        if(optionalProjeto.isPresent()){
            Projeto projeto = optionalProjeto.get();
            int quantidadeDeTarefasAntes = projeto.getTarefasPrevistas().size();
            projeto.adicionaTarefaPrevista(tarefaPrevista);
            int quantidadedeTarefasDepois = projeto.getTarefasPrevistas().size();
            if(quantidadeDeTarefasAntes != quantidadedeTarefasDepois) {
                return Optional.of(projeto);
            }
        }
        return Optional.empty();
    }
}

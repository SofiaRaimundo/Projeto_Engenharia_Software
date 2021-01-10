package pt.ufp.info.esof.projeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import pt.ufp.info.esof.projeto.repositories.EmpregadoRepository;
import pt.ufp.info.esof.projeto.repositories.TarefaPrevistaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpregadoServiceImpl implements EmpregadoService {

    private EmpregadoRepository empregadoRepository;
    private TarefaPrevistaRepository tarefaPrevistaRepository;

    @Autowired
    public EmpregadoServiceImpl(EmpregadoRepository empregadoRepository, TarefaPrevistaRepository tarefaPrevistaRepository) {
        this.empregadoRepository = empregadoRepository;
        this.tarefaPrevistaRepository = tarefaPrevistaRepository;
    }

    @Override
    public List<Empregado> findAll() {
        List<Empregado> empregados = new ArrayList<>(); //cria uma lista de empregados
        empregadoRepository.findAll().forEach(empregados::add); //vai ao repositório buscar os empregados e adiciona na lista criada
        return empregados;
    }

    @Override
    public Optional<Empregado> findById(Long id) {
        return empregadoRepository.findById(id); //retorna o empregado encontrado
    }

    @Override
    public Optional<Empregado> criarEmpregado(Empregado empregado) {
        Optional<Empregado> optionalEmpregado = empregadoRepository.findById(empregado.getId());
        if(optionalEmpregado.isEmpty()){
            empregadoRepository.save(empregado);
            return Optional.of(empregadoRepository.save(empregado));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Empregado> adicionaTarefaPrevista(Long empregadoId, TarefaPrevista tarefaPrevista) {
        Optional<Empregado> optionalEmpregado = this.empregadoRepository.findById(empregadoId);
        if(optionalEmpregado.isPresent()){
            Empregado empregado = optionalEmpregado.get();
            int quantidadeDeTarefasAntes = empregado.getTarefasPrevistas().size();
            empregado.adicionaTarefaP(tarefaPrevista);
            int quantidadedeTarefasDepois = empregado.getTarefasPrevistas().size();
            if(quantidadeDeTarefasAntes != quantidadedeTarefasDepois) {
                return Optional.of(empregado);
            }
        }
        return Optional.empty();
    }
}

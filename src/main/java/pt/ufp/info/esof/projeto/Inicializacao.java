package pt.ufp.info.esof.projeto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pt.ufp.info.esof.projeto.models.*;
import pt.ufp.info.esof.projeto.repositories.*;

@Component
public class Inicializacao implements ApplicationListener<ContextRefreshedEvent> {

    private final ClienteRepository clienteRepository;

    private final ProjetoRepository projetoRepository;

    private final EmpregadoRepository empregadoRepository;

    private final TarefaEfetivaRepository tarefaEfetivaRepository;

    private final TarefaPrevistaRepository tarefaPrevistaRepository;

    @Autowired
    public Inicializacao(ClienteRepository clienteRepository, ProjetoRepository projetoRepository, EmpregadoRepository empregadoRepository, TarefaEfetivaRepository tarefaEfetivaRepository, TarefaPrevistaRepository tarefaPrevistaRepository) {
        this.clienteRepository = clienteRepository;
        this.projetoRepository = projetoRepository;
        this.empregadoRepository = empregadoRepository;
        this.tarefaEfetivaRepository = tarefaEfetivaRepository;
        this.tarefaPrevistaRepository = tarefaPrevistaRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("\n\n\nInicializou\n\n\n");

        Cliente cliente = new Cliente();
        cliente.setNome("Cliente teste");

        Projeto projeto = new Projeto();
        projeto.setNome("Projeto teste");
        cliente.adicionaProjeto(projeto);
        projeto.setCliente(cliente);

        TarefaPrevista tarefaPrevista = new TarefaPrevista();
        tarefaPrevista.setNome("Tarefa prevista teste");
        tarefaPrevista.setProjeto(projeto);
        tarefaPrevista.setTempoPrevisto(4);

        Empregado empregado = new Empregado();
        empregado.setNome("Empregado teste");
        tarefaPrevista.setEmpregado(empregado);
        empregado.setCargo(Cargo.DEV_JR);
        empregado.adicionaTarefaP(tarefaPrevista);

        TarefaEfetiva tarefaEfetiva = new TarefaEfetiva();
        tarefaEfetiva.setNome("Tarefa efetiva");
        tarefaPrevista.adicionaTarefaEfetiva(tarefaEfetiva);
        tarefaEfetiva.setProgresso(20);
        tarefaEfetiva.setTempoTrabalhado(4);

        this.clienteRepository.save(cliente);
        this.projetoRepository.save(projeto);
        this.empregadoRepository.save(empregado);
        this.tarefaPrevistaRepository.save(tarefaPrevista);
        this.tarefaEfetivaRepository.save(tarefaEfetiva);
    }
}

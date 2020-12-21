package pt.ufp.info.esof.projeto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pt.ufp.info.esof.projeto.models.*;
import pt.ufp.info.esof.projeto.repositories.*;

@Component
public class Inicializacao implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private EmpregadoRepository empregadoRepository;

    @Autowired
    private TarefaEfetivaRepository tarefaEfetivaRepository;

    @Autowired
    private TarefaPrevistaRepository tarefaPrevistaRepository;

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

        TarefaEfetiva tarefaEfetiva = new TarefaEfetiva();
        tarefaEfetiva.setNome("Tarefa efetiva");
        tarefaPrevista.adicionaTarefaEfetiva(tarefaEfetiva);
        tarefaEfetiva.setProgresso(20);
        tarefaEfetiva.setTempoTrabalhado(4);

        this.clienteRepository.save(cliente);
        this.projetoRepository.save(projeto);
        this.empregadoRepository.save(empregado);
        this.tarefaPrevistaRepository.save(tarefaPrevista);
    }
}

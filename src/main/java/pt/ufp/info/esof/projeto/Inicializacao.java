package pt.ufp.info.esof.projeto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pt.ufp.info.esof.projeto.daos.*;

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
    }
}

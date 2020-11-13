package pt.ufp.info.esof.projeto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pt.ufp.info.esof.projeto.daos.*;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.models.GestorProjeto;
import pt.ufp.info.esof.projeto.models.Projeto;

@Component
public class Inicializacao implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EmpregadoRepository empregadoRepository;
    @Autowired
    private GestorProjetoRepository gestorProjetoRepository;
    @Autowired
    private ProjetoRepository projetoRepository;
    @Autowired
    private TarefaPrevistaRepository tarefaPrevistaRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("\n\n\nInicializou\n\n\n");

        //criamos um gestor de projeto e guardamos no repositorio deste
        GestorProjeto gp = new GestorProjeto();

        //cria um projeto
        Projeto p1 = new Projeto();
        p1.setNome("projeto1"); //nome do projeto
        p1.setGestorProjeto(gp); //associa o gestor de projeto

        this.projetoRepository.save(p1); //guarda no repositório de projetos
        this.gestorProjetoRepository.save(gp); //guarda no repositório de gestor de projetos

    }
}

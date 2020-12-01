package pt.ufp.info.esof.projeto.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "tarefa_prevista")
public class TarefaPrevista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //id da tarefa prevista para a base de dados

    @Column(name = "tempo_previsto")
    private float tempoPrevisto; //tempo previsto para a realização da tarefa

    @Column(name = "nome")
    private String nome; //nome da tarefa

    @ManyToOne
    private Projeto projeto; //projeto ao qual pertence a tarefa

    @ManyToOne
    private Empregado empregado; //empregado que vai realizar a tarefa

    @OneToMany(cascade = CascadeType.ALL) //esta relação cria a tabela tarefa_prevista_tarefas_efetivas com tarefa_prevista_id e tarefas_efetivas_id
    private List<TarefaEfetiva> tarefasEfetivas = new ArrayList<>(); //arraylist de tarefas efetivas, para guardar o tempo trabalhado em cada dia

    /**
     * Função que permite mostrar o custo de uma tarefa
     * @return - retorna o custo da tarefa
     */
    public float mostraCustoTarefaP() {
        //o custo da tarefa depende do salário do empregado que a realiza
        return empregado.salarioValorHora();
    }
}

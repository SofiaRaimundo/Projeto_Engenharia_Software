package pt.ufp.info.esof.projeto.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "tarefa_prevista")
@EqualsAndHashCode(onlyExplicitlyIncluded =true)
public class TarefaPrevista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //id da tarefa prevista para a base de dados

    @Column(name = "tempo_previsto")
    private float tempoPrevisto; //tempo previsto para a realização da tarefa

    @Column(name = "nome")
    @EqualsAndHashCode.Include
    private String nome; //nome da tarefa

    @ManyToOne
    private Projeto projeto; //projeto ao qual pertence a tarefa

    @ManyToOne
    private Empregado empregado; //empregado que vai realizar a tarefa

    @OneToMany(cascade = CascadeType.ALL) //esta relação cria a tabela tarefa_prevista_tarefas_efetivas com tarefa_prevista_id e tarefas_efetivas_id
    private List<TarefaEfetiva> tarefasEfetivas = new ArrayList<>(); //arraylist de tarefas efetivas, para guardar o tempo trabalhado em cada dia

    /**
     * Função que permite mostrar o custo de uma tarefa.
     * @return - retorna o custo da tarefa.
     */
    public float mostraCustoTarefaP() {
        //o custo da tarefa depende do salário do empregado que a realiza
        return empregado.salarioValorHora();
    }

    /**
     * Função que permite adicionar uma tarefa efetiva.
     * @param tarefaEfetiva - tarefa a ser adicionada.
     */
    public void adicionaTarefaEfetiva(TarefaEfetiva tarefaEfetiva) {
        //verifica se ainda não existe a tarefa na lista
        if(!this.tarefasEfetivas.contains(tarefaEfetiva)) {
            this.tarefasEfetivas.add(tarefaEfetiva); //adiciona a tarefa
            tarefaEfetiva.setTarefaPrevista(this); //associa a tarefa prevista com a tarefa efetiva
        }
    }

    /**
     * Função que permite remover uma tarefa efetiva.
     * @param tarefaEfetiva - tarefa a ser removida.
     */
    public void removeTarefaEfetiva(TarefaEfetiva tarefaEfetiva) {
        //verifica se já existe a tarefa na lista
        if(this.tarefasEfetivas.contains(tarefaEfetiva)) {
            this.tarefasEfetivas.remove(tarefaEfetiva); //remove a tarefa
            tarefaEfetiva.setTarefaPrevista(null); //desassocia as tarefas
        }
    }
}

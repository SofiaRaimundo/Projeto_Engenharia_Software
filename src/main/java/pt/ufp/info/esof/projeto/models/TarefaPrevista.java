package pt.ufp.info.esof.projeto.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
public class TarefaPrevista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tempo previsto")
    private float tempoPrevisto;

    @Column(name = "nome")
    private String nome;

    @Column(name = "projeto")
    private Projeto projeto;

    @Column(name = "empregado")
    private Empregado empregado;

    @OneToMany(cascade = CascadeType.ALL)
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

package pt.ufp.info.esof.projeto.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity
public class TarefaEfetiva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //id da tarefa para a base de dados

    @Column(name = "nome")
    private String nome; //nome da tarefa

    @Column(name = "progresso")
    private float progresso; //progresso da tarefa

    @Column(name = "tempo_trabalhado")
    private float tempoTrabalhado; //tempo trabalhado na tarefa

    @ManyToOne
    private TarefaPrevista tarefaPrevista; //tarefa prevista à qual pertence esta tarefa efetiva

    /**
     * Função para registar a conclusao de uma tarefa
     * em progresso colocar 100 => 100%
     */
    public void registaConclusaoTarefa(){
        setProgresso(100);
    }

    /**
     * Função que permite registar o tempo de execução desta tarefa efetiva
     * @param tempo - tempo de execução
     */
    public void registaTempoExecucao(float tempo) {
        setTempoTrabalhado(tempo);
    }
}

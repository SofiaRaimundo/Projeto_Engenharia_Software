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
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "progresso")
    private float progresso;

    @Column(name = "tempo trabalhado")
    private float tempoTrabalhado;

    /**
     * Função para registar a conclusao de uma tarefa
     * em progresso colocar 1 => 100%
     */
    public void registaConclusaoTarefa(){
        setProgresso(1);
    }

    /**
     * Função que permite registar o tempo de execução desta tarefa efetiva
     * @param tempo - tempo de execução
     */
    public void registaTempoExecucao(float tempo) {
        setTempoTrabalhado(tempo);
    }
}

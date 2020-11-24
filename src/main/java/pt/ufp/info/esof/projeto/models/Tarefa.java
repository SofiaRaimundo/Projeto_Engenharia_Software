package pt.ufp.info.esof.projeto.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "tempoPrevisto")
    private float tempoPrevisto;

    @Column(name = "progresso")
    private float progresso;

    @Column(name = "tempoTrabalho")
    private float tempoTrabalho;

    //relacao tarefa-empregado gera uma coluna na tabela projeto (coluna tarefa_empregado_id)
    @OneToOne(cascade = CascadeType.ALL)
    private Empregado empregado;

    public void registaTempoExecucao() {

    }

    public void registaConclusaoTarefa() {

    }
}

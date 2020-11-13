package pt.ufp.info.esof.projeto.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Empregado extends Pessoa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "cargo")
  public String cargo;

  @Column(name = "valor_hora")
  public int valor_hora;

  //esta relação cria a tabela empregado_tarefas com empregado_id e tarefas_id
  @OneToMany(cascade = CascadeType.ALL)
  private List<TarefaPrevista> tarefas = new ArrayList<>(); //array list de tarefas do empregado

  public void registaTempoExecucao(TarefaEfetiva t) {
  }

  public void registaConclusaoTarefa(TarefaEfetiva t) {
  }

}
package pt.ufp.info.esof.projeto.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class TarefaPrevista extends TarefaEfetiva{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "tempo_previsto")
  private int tempo_previsto;

  @Column(name = "nome")
  public String nome;

  //esta relação cria a coluna empregado_id na tabela Tarefas
  @OneToOne(cascade = CascadeType.ALL)
  private Empregado empregado;
}
package pt.ufp.info.esof.projeto.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity
public abstract class TarefaEfetiva {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="nome")
  public String nome;

  @Column(name="progresso")
  public float progresso;

  @Column(name="Tempo_trabalhado")
  public int tempo_trabalhado;
}
package pt.ufp.info.esof.projeto.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Empregado extends Pessoa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  public String cargo;
  public int valor_hora;

  public Empregado(String nome, String cargo, int valor_hora) {
    super(nome);
    this.cargo = cargo;
    this.valor_hora = valor_hora;
  }

  public void registaTempoExecucao(TarefaEfetiva t) {
  }

  public void registaConclusaoTarefa(TarefaEfetiva t) {
  }

}
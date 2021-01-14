package pt.ufp.info.esof.projeto.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "empregado")
@EqualsAndHashCode(callSuper = true)
public class Empregado extends Pessoa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id; //id do empregado para a base de dados

  @Column(name = "cargo")
  private Cargo cargo;

  @OneToMany(cascade = CascadeType.ALL) //esta relação cria a tabela empregado_tarefas_previstas com empregado_id e tarefas_previstas_id
  private List<TarefaPrevista> tarefasPrevistas = new ArrayList<>(); //tarefas previstas do empregado

  /**
   * Método para atribuir o salário a um empregado mediante o seu cargo
   * @return - retorna o salário do empregado
   */
  public float salarioValorHora() {
    //se for dev jr recebe 10 euros/hora
    if(cargo.equals(Cargo.DEV_JR)) {
      return 10;
    }else
      //se for dev sr recebe 40 euros/hora
      if(cargo.equals(Cargo.DEV_SR)){
        return 40;
      }else
        //se for an jr recebe 20 euros/hora
        if(cargo.equals(Cargo.AN_JR)) {
          return 20;
        }else
          return 80; //se for an sr recebe 80 euros/hora
  }

  /**
   * Função que permite adiconar uma tarefa a um empregado.
   * @param tarefaPrevista - tarefa a ser adicionada.
   */
  public TarefaPrevista adicionaTarefaPrevista(TarefaPrevista tarefaPrevista) {
    //verifica se a tarefa ainda não existe na lista
    if(!this.tarefasPrevistas.contains(tarefaPrevista)) {
      this.tarefasPrevistas.add(tarefaPrevista); //adiciona a tarefa
      tarefaPrevista.setEmpregado(this); //associa o empregado à tarefa
      return tarefaPrevista;
    }
    return null;
  }

  /**
   * Função que permite remover uma tarefa de um empregado.
   * @param tarefaPrevista - tarefa a ser removida.
   */
  public void removeTarefaPrevista(TarefaPrevista tarefaPrevista) {
    //verifica se a tarefa existe na lista
    if(this.tarefasPrevistas.contains(tarefaPrevista)) {
      this.tarefasPrevistas.remove(tarefaPrevista); //remove a tarefa
      tarefaPrevista.setEmpregado(null); //desassocia o empregado à tarefa
    }
  }
}
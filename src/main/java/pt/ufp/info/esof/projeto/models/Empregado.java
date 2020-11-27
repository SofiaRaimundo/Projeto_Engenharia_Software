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
  private Cargo cargo;

  //esta relação cria a tabela empregado_tarefaprevista com empregado_id e tarefa_prevista_id
  @OneToMany(cascade = CascadeType.ALL)
  private List<TarefaPrevista> tarefasPrevistas = new ArrayList<>(); //array list de tarefas previstas do empregado

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
}
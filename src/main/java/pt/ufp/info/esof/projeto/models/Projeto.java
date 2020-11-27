package pt.ufp.info.esof.projeto.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Projeto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="nome")
  private String nome;

  //esta relação cria a tabela projeto_tarefas com projeto_id e tarefas_id
  @OneToMany(cascade = CascadeType.ALL)
  private List<TarefaPrevista> tarefasPrevistas = new ArrayList<>(); //array list de tarefas previstas do projeto

  /**
   * Função que permite mostrar o estado do projeto
   * @return - retorna o estado do projeto
   */
  public float mostraEstadoProjeto() {
    float count = 0;

    //para cada tarefa prevista do arraylist
    for (TarefaPrevista tp: tarefasPrevistas) {
      //para cada tarefa efetiva de cada tarefa prevista do projeto, armazena o progresso de cada tarefa efetiva
      for(TarefaEfetiva te: tp.getTarefasEfetivas()) {
        //conta o progresso de cada tarefa efetiva e armazena em count
        count = count + te.getProgresso();
      }
    }

    float progress = count * 100; //para ficar em percentagem

    return progress; //retorna o progresso calculado
  }

  /**
   * Função que permite mostrar o custo do projeto
   * @return - retorna o custo do projeto
   */
  public float mostraCustoProjeto() {
    float custo = 0;

    //ciclo foreach para cada tarefa prevista, armazena o custo da tarefa prevista
    for (TarefaPrevista tp : tarefasPrevistas) {
      custo = custo + tp.mostraCustoTarefaP();
    }

    return custo; //retorna o custo do projeto
  }

  /**
   * Função que permite mostrar o tempo previsto que demora um projeto
   * @return - retorna o tempo
   */
  public float mostraTempoProjeto() {
    float tempo = 0;

    //para cada tarefa prevista, armazena o tempo previsto
    for (TarefaPrevista tp : tarefasPrevistas) {
      tempo = tempo + tp.getTempoPrevisto();
    }

    return tempo; //retorna o tempo previsto
  }

}
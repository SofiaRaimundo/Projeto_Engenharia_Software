package pt.ufp.info.esof.projeto.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "projeto")
@EqualsAndHashCode(onlyExplicitlyIncluded =true) //o que precisavamos para resolver o erro do cliente controller test
public class Projeto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id; //id do projeto para a base de dados

  @Column(name = "nome")
  @EqualsAndHashCode.Include //para comparar o objeto projeto pelo nome para não dar erro no cliente controller test
  private String nome; //nome do projeto

  @OneToMany(cascade = CascadeType.ALL) //esta relação cria a tabela projeto_tarefas_previstas com projeto_id e tarefas_previstas_id
  private List<TarefaPrevista> tarefasPrevistas = new ArrayList<>(); //tarefas previstas do projeto

  @ManyToOne
  private Cliente cliente;

  /**
   * Função que permite mostrar o estado do projeto.
   * @return - retorna o estado do projeto.
   */
  public float mostraEstadoProjeto() {
    float count = 0, counte = 0, x = 0, y = 0, total = tarefasPrevistas.size() * 100, totale = 0;

    //para cada tarefa prevista do arraylist
    for (TarefaPrevista tp: tarefasPrevistas) {
      //para cada tarefa efetiva de cada tarefa prevista do projeto, armazena o progresso de cada tarefa efetiva
      totale = tp.getTarefasEfetivas().size() * 100; //numero de tarefas efetivas de uma tarefa prevista
      for (TarefaEfetiva te : tp.getTarefasEfetivas()) {
        //conta o progresso de cada tarefa efetiva e armazena em count
        counte = counte + te.getProgresso();
      }

      if(counte == 100) {
        count = count + 100; //a tarefa está concluida
      }else {
        y = (100 * counte) / totale;
        count = count + y;
      }
    }

    //se todas as tarefas já estiverem terminadas
    if (count == total) {
      return 100;
    }else {
      x = (100 * count) / total; //regra de 3 simples para saber o progresso total do projeto
      return x; //retorna o progresso
    }
  }

  /**
   * Função que permite mostrar o custo do projeto.
   * @return - retorna o custo do projeto.
   */
  public float mostraCustoProjeto() {
    float custo = 0;

    //ciclo foreach para cada tarefa prevista, armazena o custo da tarefa prevista
    for (TarefaPrevista tp : tarefasPrevistas) {
      custo = custo + (tp.mostraCustoTarefaP() * tp.getTempoPrevisto());
    }

    return custo; //retorna o custo do projeto
  }

  /**
   * Função que permite mostrar o tempo previsto que demora um projeto.
   * @return - retorna o tempo.
   */
  public float mostraTempoProjeto() {
    float tempo = 0;

    //para cada tarefa prevista, armazena o tempo previsto
    for (TarefaPrevista tp : tarefasPrevistas) {
      tempo = tempo + tp.getTempoPrevisto();
    }

    return tempo; //retorna o tempo previsto
  }

  /**
   * Função para adicionar uma tarefa prevista a um projeto.
   * @param tarefaPrevista - tarefa a ser adicionada.
   */
  public void adicionaTarefaPrevista(TarefaPrevista tarefaPrevista) {
    //verifica se ainda não existe a tarefa na lista
    if(!this.tarefasPrevistas.contains(tarefaPrevista)) {
      this.tarefasPrevistas.add(tarefaPrevista); //adiciona a tarefa
      tarefaPrevista.setProjeto(this); //associa o projeto à tarefa
    }
  }

  /**
   * Função para remover uma tarefa prevista a um projeto.
   * @param tarefaPrevista - tarefa a ser removida.
   */
  public void removeTarefaPrevista(TarefaPrevista tarefaPrevista) {
    //verifica se existe a tarefa
    if(this.tarefasPrevistas.contains(tarefaPrevista)) {
      this.tarefasPrevistas.remove(tarefaPrevista); //remove a tarefa
      tarefaPrevista.setProjeto(null); //desassocia o projeto da tarefa
    }
  }
}
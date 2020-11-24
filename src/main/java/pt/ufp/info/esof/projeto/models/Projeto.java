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

  //relacao gestor-projeto gera uma coluna na tabela projeto (coluna gestor_projeto_id)
  @OneToOne(cascade = CascadeType.ALL)
  private GestorProjeto gestorProjeto;

  //esta relação cria a tabela projeto_tarefas com projeto_id e tarefas_id
  @OneToMany(cascade = CascadeType.ALL)
  private List<Tarefa> tarefas = new ArrayList<>(); //array list de tarefas do projeto

  /**
   * Função que permite mostrar o estado do projeto
   */
  public float mostraEstadoProjeto() {
    float count = 0;

    //para cada tarefa efetiva do arraylist
    for (Tarefa t: tarefas) {
       //conta o progresso de cada tarefa e armazena em count
      count = count + t.getProgresso();
    }

    float progress = count * 100; //para ficar em percentagem

    return progress; //retorna o progresso calculado
  }

  /**
   * Função que permite mostrar o valor final do projeto
   */
  public float mostraValorFinalProjeto() {
    //ciclo a percorrer as tarefas previstas
    //nas tarefas conseguimos chegar ao empregado para chegar ao valor-hora
    //nas tarefas conseguimos chegar ao tempo-trabalhado
    //conta = valor: tempo_trabalhado * valor_hora

    float valor = 0;

    //ciclo foreach para cada tarefa
    for (Tarefa t: tarefas) {
      valor = valor + (t.getTempoTrabalho() * t.getEmpregado().getValor_hora());
    }

    return valor; //retorna o valor final do projeto
  }

  /**
   * Função que retorna o orçamento do projeto
   * @return - orçamento
   */
  public float orcamentoProjeto() {
    float orc = 0;

    //ciclo foreach para cada tarefa
    for (Tarefa t: tarefas) {
      orc = orc + (t.getTempoPrevisto() * t.getEmpregado().getValor_hora());
    }

    return orc; //retorna o orçamento do projeto
  }

}
package pt.ufp.info.esof.projeto.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="projeto")
public class Projeto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="nome")
  private String nome;

  //relacao gestor-projeto
  /*@OneToOne(mappedBy = "gestor_projeto", cascade = CascadeType.ALL)
  private GestorProjeto gestorProjeto;

  @OneToMany(mappedBy = "tarefa_efetiva", cascade = CascadeType.ALL)
  private List<TarefaPrevista> tarefaprevista = new ArrayList<>(); //array list de tarefas previstas do projeto*/

  /**
   * Função que permite mostrar o estado do projeto
   */
  /*public float mostraEstadoProjeto() {
    float count = 0;

    //para cada tarefa efetiva do arraylist
    for (TarefaPrevista tp: tarefaprevista) {
       //conta o progresso de cada tarefa e armazena em count
    }

    float progress = count * 100; //para ficar em percentagem

    return progress; //retorna o progresso calculado
  }*/

  /**
   * Função que permite mostrar o valor do projeto
   */
  public void mostraValorProjeto() {
    //ciclo a percorrer as tarefas efetivas
    //nas tarefas conseguimos chegar ao tempo
    //
  }

}
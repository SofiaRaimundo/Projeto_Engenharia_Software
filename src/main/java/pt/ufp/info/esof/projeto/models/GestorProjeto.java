package pt.ufp.info.esof.projeto.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class GestorProjeto extends Pessoa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  //esta relação cria a tabela gestor_projeto_projetos com gestor_projeto_id e projetos_id
  @OneToMany(cascade = CascadeType.ALL)
  private List<Projeto> projetos = new ArrayList<>(); //array list de projetos do gestor

  /**
   * Função que permite ao gestor do projeto registar o progresso de um projeto
   * @param p - projeto
   */
  public float registaProgresso(Projeto p) {
    //tem de verificar se existe esse projeto
    //percorrer a lista de projetos
    //tem de somar todos os progressos de cada tarefa do projeto
    //e no fim essa soma equivale ao progresso do projeto

    //progP = x
    int numTarefas = 0, i = 0, progT = 0, progTotalFixoP = 0, progP = 0;

    //se existir o projeto
    if(projetos.contains(p)) {
      //percorre a lista de projetos
      for (Projeto pp:projetos) {
        //verifica se já está no projeto pretendido
        if (pp == p) {
          numTarefas = p.getTarefas().size(); //número de tarefas do projeto
          for(i = 0; i < numTarefas; i++) {
            progT += p.getTarefas().get(i).getProgresso(); //acumula o progresso das tarefas
          }

          progTotalFixoP = numTarefas;
          progP = (progT * 1) / progTotalFixoP; //para sabermos o progresso do projeto

          return progP * 100; //retorna o progresso em percentagem
        }
      }
    }

    return 0; //se não encontrar o projeto pretendido
  }
}
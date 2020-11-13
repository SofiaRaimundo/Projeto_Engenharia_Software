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

  public void registaProgresso(Projeto p) {
  }

}
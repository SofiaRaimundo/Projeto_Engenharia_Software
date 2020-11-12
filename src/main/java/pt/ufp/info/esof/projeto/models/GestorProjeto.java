package pt.ufp.info.esof.projeto.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="gestor_projeto")
public class GestorProjeto extends Pessoa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /*@OneToMany(mappedBy = "gestorprojeto", cascade = CascadeType.ALL)
  private List<Projeto> projetos = new ArrayList<>(); //array list de projetos do gestor*/

  public GestorProjeto(String nome) {
    super(nome);
  }

  public void registaProgresso(Projeto p) {
  }

}
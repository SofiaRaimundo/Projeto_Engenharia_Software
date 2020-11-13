package pt.ufp.info.esof.projeto.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
public abstract class Pessoa {

  @Column(name = "nome")
  public String nome;
}
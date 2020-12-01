package pt.ufp.info.esof.projeto.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //id do cliente para a base de dados

    @OneToMany(cascade = CascadeType.ALL) //esta relação cria a tabela cliente_projetos com cliente_id e projetos_id
    private List<Projeto> projetos = new ArrayList<>(); //projetos do cliente
}
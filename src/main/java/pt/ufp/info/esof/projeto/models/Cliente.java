package pt.ufp.info.esof.projeto.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Cliente extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //id_client (para a base de dados)

    //!!!!!!perguntar se elas concordam!!!!!!!!
    @Column(name = "nome")
    private String nome;

    //esta relação cria a tabela cliente_projetos com cliente_id e projetos_id
    @OneToMany(cascade = CascadeType.ALL)
    private List<Projeto> projetos = new ArrayList<>(); //array list de projetos do cliente

    public Cliente(String nome) {
        super(nome);
    }
}
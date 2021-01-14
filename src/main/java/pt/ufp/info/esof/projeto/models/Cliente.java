package pt.ufp.info.esof.projeto.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@EqualsAndHashCode(callSuper = true)
@Table(name = "cliente")
public class Cliente extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //id do cliente para a base de dados

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL) //esta relação cria a tabela cliente_projetos com cliente_id e projetos_id
    private List<Projeto> projetos = new ArrayList<>(); //projetos do cliente

    /**
     * Função para adicionar um projeto à lista de projetos de um cliente.
     * @param projeto - projeto a ser adicionado.
     */
    public void adicionaProjeto(Projeto projeto) {
        //verifica se ainda não tem o projeto
        if(!this.projetos.contains(projeto)){
            this.projetos.add(projeto); //se não tiver adiciona à lista de projetos do cliente
            projeto.setCliente(this); //associa o cliente ao projeto
        }
    }

    /**
     * Função para remover um projeto à lista de projetos de um cliente.
     * @param projeto - projeto a ser removido.
     */
    public void removeProjeto(Projeto projeto) {
        //verifica se o cliente tem o projeto
        if(this.projetos.contains(projeto)){
            this.projetos.remove(projeto); //se tiver remove da lista de projetos do cliente
            projeto.setCliente(null); //desassocia o cliente do projeto
        }
    }
}
package pt.ufp.info.esof.projeto.design_patterns.builder;

import lombok.Getter;
import lombok.Setter;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.models.Projeto;
import java.util.ArrayList;

@Getter
@Setter
public class ClienteBuilder {

    private Cliente cliente;

    public ClienteBuilder() {
        this.cliente = new Cliente();
    }

    public ClienteBuilder setNome(String nome){
        this.cliente.setNome(nome);
        return this;
    }

    public Cliente build(){
        return this.cliente;
    }

    public ClienteBuilder adicionaProjeto(Projeto projeto) {
        //se estiver vazio
        if(this.cliente.getProjetos() == null) {
            this.cliente.setProjetos(new ArrayList<>()); //cria o array list
        }

        this.cliente.adicionaProjeto(projeto); //adiciona o projeto
        return this;
    }

    public static void main(String[] args) {
        Projeto projeto = new Projeto();
        Cliente cliente = new ClienteBuilder().setNome("Cliente teste 1").adicionaProjeto(projeto).build();
    }
}

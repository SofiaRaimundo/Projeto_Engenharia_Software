package pt.ufp.info.esof.projeto.design_patterns;

import java.util.ArrayList;

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

    public ClienteBuilder adicionaProjeto(String projeto1) {
        //se estiver vazio
        if(this.cliente.getNomesProjetos() == null) {
            this.cliente.setNomesProjetos(new ArrayList<>()); //cria o array list
        }

        this.cliente.getNomesProjetos().add(projeto1); //adiciona o projeto
        return this;
    }

    public static void main(String[] args) {
        Cliente cliente = new ClienteBuilder().setNome("Cliente teste 1").adicionaProjeto("Projeto teste 1").build();
    }
}

package pt.ufp.info.esof.projeto.design_patterns;

import java.util.ArrayList;

public class ProjetoBuilder {

    private Projeto projeto;

    public void Projeto(String nome){
        this.projeto=new Projeto();
    }

    public ProjetoBuilder setNome(String nome) {
        this.projeto.setNome(nome);
        return this;
    }

    public Projeto build() {
        return this.projeto;
    }

    public ProjetoBuilder adicionaTarefaPrevista(String tarefaPrevista1) {
        //se estiver vazio
        if(this.projeto.getNomesTarefasPrevistas() == null) {
            this.projeto.setNomesTarefasPrevistas(new ArrayList<>()); //cria o array list
        }

        this.projeto.getNomesTarefasPrevistas().add(tarefaPrevista1); //adiciona a tarefa
        return this;
    }

    public static void main(String[] args) {
        Projeto projeto = new ProjetoBuilder().setNome("Projeto teste 1").adicionaTarefaPrevista("Tarefa Prevista teste 1").build();
    }
}
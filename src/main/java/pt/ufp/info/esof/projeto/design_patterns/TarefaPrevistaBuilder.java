package pt.ufp.info.esof.projeto.design_patterns;

import java.util.ArrayList;

public class TarefaPrevistaBuilder {

    private TarefaPrevista tarefaPrevista;

    public TarefaPrevistaBuilder() {
        this.tarefaPrevista = new TarefaPrevista();
    }

    public TarefaPrevistaBuilder setNome(String nome){
        this.tarefaPrevista.setNome(nome);
        return this;
    }

    public TarefaPrevistaBuilder setTempoPrevisto(float tempoPrevisto){
        this.tarefaPrevista.setTempoPrevisto(tempoPrevisto);
        return this;
    }

    public TarefaPrevista build() {
        return this.tarefaPrevista;
    }

    public TarefaPrevistaBuilder adicionaTarefaEfetiva(String tarefaEfetiva1) {
        //se estiver vazio
        if(this.tarefaPrevista.getNomesTarefasEfetivas() == null) {
            this.tarefaPrevista.setNomesTarefasEfetivas(new ArrayList<>()); //cria o array list
        }

        this.tarefaPrevista.getNomesTarefasEfetivas().add(tarefaEfetiva1); //adiciona a tarefa
        return this;
    }

    public static void main(String[] args) {
        TarefaPrevista tarefaPrevista = new TarefaPrevistaBuilder().setNome("Tarefa Prevista teste 1").setTempoPrevisto(8).adicionaTarefaEfetiva("Tarefa efetiva teste 1").build();
    }
}

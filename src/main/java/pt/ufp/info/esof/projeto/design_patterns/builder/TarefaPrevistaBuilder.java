package pt.ufp.info.esof.projeto.design_patterns.builder;

import lombok.Getter;
import lombok.Setter;
import pt.ufp.info.esof.projeto.models.TarefaEfetiva;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import java.util.ArrayList;

@Getter
@Setter
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

    public TarefaPrevistaBuilder adicionaTarefaEfetiva(TarefaEfetiva tarefaEfetiva) {
        //se estiver vazio
        if(this.tarefaPrevista.getTarefasEfetivas() == null) {
            this.tarefaPrevista.setTarefasEfetivas(new ArrayList<>()); //cria o array list
        }

        this.tarefaPrevista.adicionaTarefaEfetiva(tarefaEfetiva); //adiciona a tarefa
        return this;
    }

    public static void main(String[] args) {
        TarefaEfetiva tarefaEfetiva = new TarefaEfetiva();
        TarefaPrevista tarefaPrevista = new TarefaPrevistaBuilder().setNome("Tarefa Prevista teste 1").setTempoPrevisto(8).adicionaTarefaEfetiva(tarefaEfetiva).build();
    }
}

package pt.ufp.info.esof.projeto.design_patterns.builder;

import lombok.Getter;
import lombok.Setter;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import java.util.ArrayList;

@Getter
@Setter
public class ProjetoBuilder {

    private Projeto projeto;

    public ProjetoBuilder(){
        this.projeto=new Projeto();
    }

    public ProjetoBuilder setNome(String nome) {
        this.projeto.setNome(nome);
        return this;
    }

    public Projeto build() {
        return this.projeto;
    }

    public ProjetoBuilder adicionaTarefaPrevista(TarefaPrevista tarefaPrevista) {
        //se estiver vazio
        if(this.projeto.getTarefasPrevistas() == null) {
            this.projeto.setTarefasPrevistas(new ArrayList<>()); //cria o array list
        }

        this.projeto.adicionaTarefaPrevista(tarefaPrevista); //adiciona a tarefa
        return this;
    }

    public static void main(String[] args) {
        TarefaPrevista tarefaPrevista = new TarefaPrevista();
        Projeto projeto = new ProjetoBuilder().setNome("Projeto teste 1").adicionaTarefaPrevista(tarefaPrevista).build();
    }
}
package pt.ufp.info.esof.projeto.design_patterns.builder;

import lombok.Getter;
import lombok.Setter;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import java.util.ArrayList;

@Getter
@Setter
public class EmpregadoBuilder {

    private Empregado empregado;

    public EmpregadoBuilder() {
        this.empregado = new Empregado();
    }

    public EmpregadoBuilder setNome(String nome) {
        this.empregado.setNome(nome);
        return this;
    }

    public Empregado build() {
        return this.empregado;
    }

    public EmpregadoBuilder adicionaTarefaPrevista(TarefaPrevista tarefaPrevista) {
        //se estiver vazio
        if(this.empregado.getTarefasPrevistas() == null) {
            this.empregado.setTarefasPrevistas(new ArrayList<>()); //cria o array list
        }

        this.empregado.adicionaTarefaPrevista(tarefaPrevista); //adiciona a tarefa
        return this;
    }

    public static void main(String[] args) {
        TarefaPrevista tarefaPrevista = new TarefaPrevista();
        Empregado empregado = new EmpregadoBuilder().setNome("Empregado teste 1").adicionaTarefaPrevista(tarefaPrevista).build();
    }
}

package pt.ufp.info.esof.projeto.design_patterns;

import java.util.ArrayList;

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

    public EmpregadoBuilder adicionaTarefaPrevista(String tarefaPrevista1) {
        //se estiver vazio
        if(this.empregado.getNomesTarefasPrevistas() == null) {
            this.empregado.setNomesTarefasPrevistas(new ArrayList<>()); //cria o array list
        }

        this.empregado.getNomesTarefasPrevistas().add(tarefaPrevista1); //adiciona a tarefa
        return this;
    }

    public static void main(String[] args) {
        Empregado empregado = new EmpregadoBuilder().setNome("Empregado teste 1").adicionaTarefaPrevista("Tarefa Prevista teste 1").build();
    }
}

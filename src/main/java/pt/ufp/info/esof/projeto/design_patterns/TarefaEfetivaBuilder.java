package pt.ufp.info.esof.projeto.design_patterns;

public class TarefaEfetivaBuilder {

    private TarefaEfetiva tarefaEfetiva;

    public TarefaEfetivaBuilder() {
        this.tarefaEfetiva = new TarefaEfetiva();
    }

    public TarefaEfetivaBuilder setNome(String nome){
        this.tarefaEfetiva.setNome(nome);
        return this;
    }

    public TarefaEfetivaBuilder setProgresso(float progresso){
        this.tarefaEfetiva.setProgresso(progresso);
        return this;
    }

    public TarefaEfetivaBuilder setTempoTrabalhado(float tempoTrabalhado){
        this.tarefaEfetiva.setTempo_trabalhado(tempoTrabalhado);
        return this;
    }

    public TarefaEfetiva build(){
        return this.tarefaEfetiva;
    }

    public static void main(String[] args) {
        TarefaEfetiva tarefaEfetiva = new TarefaEfetivaBuilder().setNome("Tarefa Efetiva teste 1").setProgresso(2).setTempoTrabalhado(4).build();
    }
}

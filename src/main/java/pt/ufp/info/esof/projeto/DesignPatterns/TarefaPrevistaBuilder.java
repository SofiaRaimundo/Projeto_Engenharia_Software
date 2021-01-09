package pt.ufp.info.esof.projeto.DesignPatterns;

public class TarefaPrevistaBuilder {
    private TarefaEfetiva tarefaEfetiva;

    public TarefaPrevistaBuilder(String nome, float tempoPrevisto){
        this.tarefaEfetiva = new TarefaEfetiva();
    }
}

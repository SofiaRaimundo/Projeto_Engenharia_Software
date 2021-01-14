package pt.ufp.info.esof.projeto.dtos;

import lombok.Data;
import pt.ufp.info.esof.projeto.models.TarefaEfetiva;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;

@Data
public class TarefaEfetivaCreateDTO implements CreateDTO<TarefaEfetiva> {
    private String nome;
    private float progresso;
    private float tempoTrabalhado;
    private Long tarefaPrevistaId;

    @Override
    public TarefaEfetiva converter() {
        TarefaEfetiva tarefaEfetiva=new TarefaEfetiva();
        tarefaEfetiva.setNome(this.getNome());
        tarefaEfetiva.setTempoTrabalhado(this.getTempoTrabalhado());
        tarefaEfetiva.setProgresso(this.getProgresso());
        return tarefaEfetiva;
    }
}
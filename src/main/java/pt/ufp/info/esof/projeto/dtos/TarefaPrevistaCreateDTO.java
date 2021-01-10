package pt.ufp.info.esof.projeto.dtos;

import lombok.Data;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class TarefaPrevistaCreateDTO implements CreateDTO<TarefaPrevista> {

    private float tempoPrevisto; //tempo previsto para a realização da tarefa
    private String Nome; //nome da tarefa
    private List<TarefaEfetivaCreateDTO> tarefasEfetivas = new ArrayList<>(); //lista de tarefas efetivas

    @Override
    public TarefaPrevista converter() {
        TarefaPrevista tarefaPrevista=new TarefaPrevista();
        tarefaPrevista.setNome(this.getNome());
        tarefaPrevista.setTempoPrevisto(this.getTempoPrevisto());
        tarefaPrevista.setTarefasEfetivas(tarefasEfetivas.stream().map(TarefaEfetivaCreateDTO::converter).collect(Collectors.toList()));
        return tarefaPrevista;
    }
}
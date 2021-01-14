package pt.ufp.info.esof.projeto.dtos;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class TarefaPrevistaResponseDTO {

    private float tempoPrevisto; //tempo previsto para a realizaçao da tarefa
    private String nome; // nome da tarefa
    private Long projetoId;
    private Long empregadoId;
    private List<TarefaEfetivaCreateDTO> TarefasEfetivas = new ArrayList<>(); //lista de tarefas efetivas
}
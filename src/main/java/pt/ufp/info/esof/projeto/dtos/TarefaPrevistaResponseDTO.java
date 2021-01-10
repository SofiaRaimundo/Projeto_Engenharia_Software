package pt.ufp.info.esof.projeto.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TarefaPrevistaResponseDTO {
    private float tempoPrevisto; //tempo previsto para a realizaçao da tarefa
    private String Nome; // nome da tarefa
    private List<String> TarefasEfetivas = new ArrayList<>(); //lista de tarefas efetivas
}
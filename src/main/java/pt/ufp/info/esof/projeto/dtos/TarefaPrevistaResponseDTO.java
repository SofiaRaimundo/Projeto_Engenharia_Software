package pt.ufp.info.esof.projeto.dtos;

import lombok.Data;

@Data
public class TarefaPrevistaResponseDTO {
    private float tempoPrevisto;//tempo previsto para a realizaçao da tarefa
    private String Nome;// nome da tarefa
}

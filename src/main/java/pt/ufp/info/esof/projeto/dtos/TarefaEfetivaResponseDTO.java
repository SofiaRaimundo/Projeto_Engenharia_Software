package pt.ufp.info.esof.projeto.dtos;

import lombok.Data;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;

@Data
public class TarefaEfetivaResponseDTO {
    private String nome;
    private float progresso;
    private float tempoTrabalhado;
    private Long tarefaPrevistaId;
}
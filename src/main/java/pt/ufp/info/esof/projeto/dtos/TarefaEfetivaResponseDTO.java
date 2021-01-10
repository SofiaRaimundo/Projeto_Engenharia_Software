package pt.ufp.info.esof.projeto.dtos;

import lombok.Data;

@Data
public class TarefaEfetivaResponseDTO {
    private String nome;
    private float progresso;
    private float tempoTrabalhado;
}
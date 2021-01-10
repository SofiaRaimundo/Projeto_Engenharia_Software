package pt.ufp.info.esof.projeto.dtos.conversores;

import pt.ufp.info.esof.projeto.dtos.TarefaEfetivaResponseDTO;
import pt.ufp.info.esof.projeto.models.TarefaEfetiva;

public class ConverterTarefaEfetivaParaDTO implements Conversor<TarefaEfetivaResponseDTO, TarefaEfetiva> {

    @Override
    public TarefaEfetivaResponseDTO converter(TarefaEfetiva tarefaEfetiva) {
        TarefaEfetivaResponseDTO responseDTO = new TarefaEfetivaResponseDTO();
        responseDTO.setNome(tarefaEfetiva.getNome());
        responseDTO.setTempoTrabalhado(tarefaEfetiva.getTempoTrabalhado());
        responseDTO.setProgresso(tarefaEfetiva.getProgresso());

        return responseDTO;
    }
}
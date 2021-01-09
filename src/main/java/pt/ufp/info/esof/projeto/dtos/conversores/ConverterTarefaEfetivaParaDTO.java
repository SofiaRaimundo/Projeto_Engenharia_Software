package pt.ufp.info.esof.projeto.dtos.conversores;

import pt.ufp.info.esof.projeto.dtos.TarefaEfetivaCreateDTO;
import pt.ufp.info.esof.projeto.dtos.TarefaEfetivaResponseDTO;
import pt.ufp.info.esof.projeto.models.TarefaEfetiva;

public class ConverterTarefaEfetivaParaDTO implements Conversor<TarefaEfetivaCreateDTO, TarefaEfetiva> {
    @Override
    public TarefaEfetivaCreateDTO converter(TarefaEfetiva tarefaEfetiva) {
        TarefaEfetivaResponseDTO responseDTO=new TarefaEfetivaResponseDTO();
        responseDTO.setNome(tarefaEfetiva.getNome());
        responseDTO.setTempoTrabalhado(tarefaEfetiva.getTempoTrabalhado());
        responseDTO.setProgresso(tarefaEfetiva.getProgresso());

        return null;
    }
}

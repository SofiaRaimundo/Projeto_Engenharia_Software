package pt.ufp.info.esof.projeto.dtos.conversores;

import pt.ufp.info.esof.projeto.dtos.ProjetoCreateDTO;
import pt.ufp.info.esof.projeto.dtos.TarefaEfetivaCreateDTO;
import pt.ufp.info.esof.projeto.dtos.TarefaPrevistaResponseDTO;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;

import java.util.stream.Collectors;

public class ConverterTarefaPrevistaParaDTO implements Conversor<TarefaPrevistaResponseDTO, TarefaPrevista> {

    @Override
    public TarefaPrevistaResponseDTO converter(TarefaPrevista tarefaPrevista) {
        TarefaPrevistaResponseDTO responseDTO = new TarefaPrevistaResponseDTO();

        responseDTO.setNome(tarefaPrevista.getNome());
        responseDTO.setTempoPrevisto(tarefaPrevista.getTempoPrevisto());
        responseDTO.setTarefasEfetivas(tarefaPrevista.getTarefasEfetivas().stream().map(tarefaEfetiva -> {
            TarefaEfetivaCreateDTO tarefaEfetivaDTO = new TarefaEfetivaCreateDTO();
            tarefaEfetivaDTO.setNome(tarefaEfetivaDTO.getNome());
            tarefaEfetivaDTO.setProgresso(tarefaEfetivaDTO.getProgresso());
            tarefaEfetivaDTO.setTempoTrabalhado(tarefaEfetivaDTO.getTempoTrabalhado());
            tarefaEfetivaDTO.setTarefaPrevistaId(tarefaEfetivaDTO.getTarefaPrevistaId());
            return tarefaEfetivaDTO;
        }).collect(Collectors.toList()));

        return responseDTO;
    }
}
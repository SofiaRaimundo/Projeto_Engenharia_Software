package pt.ufp.info.esof.projeto.dtos.conversores;

import pt.ufp.info.esof.projeto.dtos.TarefaPrevistaResponseDTO;
import pt.ufp.info.esof.projeto.models.TarefaEfetiva;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import java.util.stream.Collectors;

public class ConverterTarefaPrevistaParaDTO implements Conversor<TarefaPrevistaResponseDTO, TarefaPrevista> {

    @Override
    public TarefaPrevistaResponseDTO converter(TarefaPrevista tarefaPrevista) {
        TarefaPrevistaResponseDTO responseDTO = new TarefaPrevistaResponseDTO();
        responseDTO.setNome(tarefaPrevista.getNome());
        responseDTO.setTempoPrevisto(tarefaPrevista.getTempoPrevisto());
        responseDTO.setTarefasEfetivas(tarefaPrevista.getTarefasEfetivas().stream().map(TarefaEfetiva::getNome).collect(Collectors.toList()));

        return responseDTO;
    }
}
package pt.ufp.info.esof.projeto.dtos.conversores;

import pt.ufp.info.esof.projeto.dtos.ProjetoResponseDTO;
import pt.ufp.info.esof.projeto.dtos.TarefaPrevistaCreateDTO;
import pt.ufp.info.esof.projeto.models.Projeto;
import java.util.stream.Collectors;

public class ConverterProjetoParaDTO implements Conversor<ProjetoResponseDTO, Projeto> {

    @Override
    public ProjetoResponseDTO converter(Projeto projeto) {
        ProjetoResponseDTO responseDTO = new ProjetoResponseDTO();

        responseDTO.setNome(projeto.getNome());
        responseDTO.setCliente(projeto.getCliente());
        responseDTO.setTarefasPrevistas(projeto.getTarefasPrevistas().stream().map(tarefaPrevista -> {
            TarefaPrevistaCreateDTO tarefaPrevistaDTO = new TarefaPrevistaCreateDTO();
            tarefaPrevistaDTO.setNome(tarefaPrevistaDTO.getNome());
            tarefaPrevistaDTO.setProjetoId(tarefaPrevistaDTO.getProjetoId());
            tarefaPrevistaDTO.setEmpregadoId(tarefaPrevistaDTO.getEmpregadoId());
            tarefaPrevistaDTO.setTempoPrevisto(tarefaPrevistaDTO.getTempoPrevisto());
            tarefaPrevistaDTO.setTarefasEfetivas(tarefaPrevistaDTO.getTarefasEfetivas());
            return tarefaPrevistaDTO;
        }).collect(Collectors.toList()));

        return responseDTO;
    }
}
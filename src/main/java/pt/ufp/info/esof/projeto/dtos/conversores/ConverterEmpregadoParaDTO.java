package pt.ufp.info.esof.projeto.dtos.conversores;

import pt.ufp.info.esof.projeto.dtos.EmpregadoResponseDTO;
import pt.ufp.info.esof.projeto.dtos.TarefaPrevistaCreateDTO;
import pt.ufp.info.esof.projeto.models.Empregado;
import java.util.stream.Collectors;

public class ConverterEmpregadoParaDTO implements Conversor<EmpregadoResponseDTO, Empregado> {

    @Override
    public EmpregadoResponseDTO converter(Empregado empregado) {
        EmpregadoResponseDTO responseDTO = new EmpregadoResponseDTO();

        responseDTO.setNome(empregado.getNome());
        responseDTO.setCargo(empregado.getCargo());
        responseDTO.setTarefasPrevistas(empregado.getTarefasPrevistas().stream().map(tarefaPrevista -> {
            TarefaPrevistaCreateDTO tarefaPrevistaCreateDTO = new TarefaPrevistaCreateDTO();
            tarefaPrevistaCreateDTO.setNome(tarefaPrevistaCreateDTO.getNome());
            tarefaPrevistaCreateDTO.setTempoPrevisto(tarefaPrevistaCreateDTO.getTempoPrevisto());
            tarefaPrevistaCreateDTO.setEmpregadoId(tarefaPrevistaCreateDTO.getEmpregadoId());
            tarefaPrevistaCreateDTO.setProjetoId(tarefaPrevistaCreateDTO.getProjetoId());
            tarefaPrevistaCreateDTO.setTarefasEfetivas(tarefaPrevistaCreateDTO.getTarefasEfetivas());
            return tarefaPrevistaCreateDTO;
        }).collect(Collectors.toList()));

        return responseDTO;
    }
}
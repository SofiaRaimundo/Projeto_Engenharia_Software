package pt.ufp.info.esof.projeto.dtos.conversores;

import pt.ufp.info.esof.projeto.dtos.EmpregadoResponseDTO;
import pt.ufp.info.esof.projeto.dtos.ProjetoCreateDTO;
import pt.ufp.info.esof.projeto.dtos.TarefaPrevistaCreateDTO;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;

import java.util.stream.Collectors;

public class CoverterEmpregadoParaDto implements Conversor<EmpregadoResponseDTO, Empregado> {
    @Override
    public EmpregadoResponseDTO converter(Empregado empregado) {
        EmpregadoResponseDTO responseDTO=new EmpregadoResponseDTO();
        responseDTO.setName(empregado.getNome());
        responseDTO.setTarefasPrevistas(empregado.getTarefasPrevistas().stream().map(TarefaPrevista::getNome).collect(Collectors.toList()));
        return null;
    }
}

package pt.ufp.info.esof.projeto.dtos.conversores;

import pt.ufp.info.esof.projeto.dtos.ClienteResponseDTO;
import pt.ufp.info.esof.projeto.dtos.ProjetoResponseDTO;
import pt.ufp.info.esof.projeto.dtos.TarefaPrevistaCreateDTO;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;

import java.util.stream.Collectors;

public class ConverterProjetoParaDTO implements Conversor<ProjetoResponseDTO, Projeto>{
    @Override
    public ProjetoResponseDTO converter(Projeto projeto) {
       ProjetoResponseDTO responseDTO=new ProjetoResponseDTO();
        responseDTO.setName(projeto.getNome());
        responseDTO.setTarefasPrevistas(projeto.getTarefasPrevistas().stream().map(TarefaPrevista::getNome).collect(Collectors.toList()));
        return null;
    }
}

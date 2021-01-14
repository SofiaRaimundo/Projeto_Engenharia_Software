package pt.ufp.info.esof.projeto.dtos.conversores;

import pt.ufp.info.esof.projeto.dtos.ClienteResponseDTO;
import pt.ufp.info.esof.projeto.dtos.ProjetoCreateDTO;
import pt.ufp.info.esof.projeto.models.Cliente;
import java.util.stream.Collectors;

public class ConverterClienteParaDTO implements Conversor<ClienteResponseDTO, Cliente> {

    @Override
    public ClienteResponseDTO converter(Cliente cliente) {
        ClienteResponseDTO responseDTO = new ClienteResponseDTO();

        responseDTO.setNome(cliente.getNome());
        responseDTO.setProjetos(cliente.getProjetos().stream().map(projeto -> {
            ProjetoCreateDTO projetoDTO = new ProjetoCreateDTO();
            projetoDTO.setNome(projetoDTO.getNome());
            projetoDTO.setTarefasPrevistas(projetoDTO.getTarefasPrevistas());
            return projetoDTO;
        }).collect(Collectors.toList()));

        return responseDTO;
    }
}
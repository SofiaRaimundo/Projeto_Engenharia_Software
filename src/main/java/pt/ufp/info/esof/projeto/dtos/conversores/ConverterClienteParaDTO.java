package pt.ufp.info.esof.projeto.dtos.conversores;

import pt.ufp.info.esof.projeto.dtos.ClienteResponseDTO;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.models.Projeto;
import java.util.stream.Collectors;

public class ConverterClienteParaDTO implements Conversor<ClienteResponseDTO, Cliente> {

    @Override
    public ClienteResponseDTO converter(Cliente cliente) {
        ClienteResponseDTO responseDTO = new ClienteResponseDTO();
        responseDTO.setProjetos(cliente.getProjetos().stream().map(Projeto::getNome).collect(Collectors.toList()));

        return responseDTO;
    }
}
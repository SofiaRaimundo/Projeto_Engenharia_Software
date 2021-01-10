package pt.ufp.info.esof.projeto.dtos;

import lombok.Data;
import pt.ufp.info.esof.projeto.models.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ClienteCreateDTO implements CreateDTO<Cliente> {
    private List<ProjetoCreateDTO> projetos = new ArrayList<>(); //lista de projetos do cliente

    @Override
    public Cliente converter() {
        Cliente cliente=new Cliente();
        cliente.setProjetos(projetos.stream().map(ProjetoCreateDTO::converter).collect(Collectors.toList()));
        return cliente;
    }
}

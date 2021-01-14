package pt.ufp.info.esof.projeto.dtos;

import lombok.Data;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.models.Projeto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProjetoCreateDTO implements CreateDTO<Projeto> {

    private String nome;
    private List<TarefaPrevistaCreateDTO> TarefasPrevistas=new ArrayList<>();
    private Cliente cliente;

    @Override
    public Projeto converter() {
        Projeto projeto=new Projeto();
        projeto.setNome(this.getNome());
        projeto.setTarefasPrevistas(TarefasPrevistas.stream().map(TarefaPrevistaCreateDTO::converter).collect(Collectors.toList()));
        projeto.setCliente(this.getCliente());
        return projeto;
    }
}
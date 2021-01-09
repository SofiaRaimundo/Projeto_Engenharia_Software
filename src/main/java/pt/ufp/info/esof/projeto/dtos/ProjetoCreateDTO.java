package pt.ufp.info.esof.projeto.dtos;

import lombok.Data;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProjetoCreateDTO implements CreateDTO<Projeto> {

    private String Name;
    private List<TarefaPrevistaCreateDTO> TarefasPrevistas=new ArrayList<>();

    @Override
    public Projeto converter() {
        Projeto projeto=new Projeto();
        projeto.setNome(this.getName());
        projeto.setTarefasPrevistas(TarefasPrevistas.stream().map(TarefaPrevistaCreateDTO::converter).collect(Collectors.toList()));
        return projeto;
    }
}

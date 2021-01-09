package pt.ufp.info.esof.projeto.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.models.Projeto;

@Data
public class EmpregadoCreateDTO implements CreateDTO<Empregado> {

    private String Name;
    private List<TarefaPrevistaCreateDTO> TarefasPrevistas=new ArrayList<>();

    @Override
    public Empregado converter() {
        Empregado empregado=new Empregado();
        empregado.setNome(this.getName());
        empregado.setTarefasPrevistas(TarefasPrevistas.stream().map(TarefaPrevistaCreateDTO::converter).collect(Collectors.toList()));
        return empregado;
    }
}

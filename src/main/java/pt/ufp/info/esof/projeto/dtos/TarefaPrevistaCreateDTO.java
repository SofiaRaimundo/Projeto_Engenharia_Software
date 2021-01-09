package pt.ufp.info.esof.projeto.dtos;

import lombok.Data;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;

import java.time.LocalDateTime;
import java.util.stream.Collectors;


@Data
public class TarefaPrevistaCreateDTO implements CreateDTO<TarefaPrevista> {

        private float tempoPrevisto;//tempo previsto para a realizaçao da tarefa
        private String Nome;// nome da tarefa

    @Override
    public TarefaPrevista converter() {
        TarefaPrevista tarefaPrevista=new TarefaPrevista();
        tarefaPrevista.setNome(this.getNome());
        tarefaPrevista.setTempoPrevisto(this.getTempoPrevisto());
        return tarefaPrevista;
    }
}

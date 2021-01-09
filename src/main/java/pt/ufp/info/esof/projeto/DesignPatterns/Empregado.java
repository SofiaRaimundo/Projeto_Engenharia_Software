package pt.ufp.info.esof.projeto.DesignPatterns;

import lombok.Data;
import pt.ufp.info.esof.projeto.models.Cargo;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;

import java.util.ArrayList;
import java.util.List;

@Data
public class Empregado {
    private Cargo cargo;
    private List<TarefaPrevista> tarefasPrevistas = new ArrayList<>();
}

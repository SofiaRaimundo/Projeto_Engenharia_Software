package pt.ufp.info.esof.projeto.design_patterns;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.ufp.info.esof.projeto.models.Cargo;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Empregado {

    private String nome;
    private Cargo cargo;
    private List<String> nomesTarefasPrevistas;
}
package pt.ufp.info.esof.projeto.design_patterns;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Projeto {

    private String nome;
    private List<String> nomesTarefasPrevistas;
}
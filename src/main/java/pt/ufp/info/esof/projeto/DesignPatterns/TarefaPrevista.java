package pt.ufp.info.esof.projeto.DesignPatterns;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TarefaPrevista {

    private float tempoPrevisto;
    private String nome;
}

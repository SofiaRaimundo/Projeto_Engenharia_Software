package pt.ufp.info.esof.projeto.DesignPatterns;

import lombok.Data;
import pt.ufp.info.esof.projeto.models.Projeto;

import java.util.ArrayList;
import java.util.List;
@Data
public class Cliente {
    private List<Projeto> projetos = new ArrayList<>();
}

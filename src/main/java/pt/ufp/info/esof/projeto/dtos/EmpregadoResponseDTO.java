package pt.ufp.info.esof.projeto.dtos;

import lombok.Data;
import pt.ufp.info.esof.projeto.models.Cargo;
import java.util.ArrayList;
import java.util.List;

@Data
public class EmpregadoResponseDTO {
    private String nome;
    private Cargo cargo;
    private List<TarefaPrevistaCreateDTO> TarefasPrevistas = new ArrayList<>();
}
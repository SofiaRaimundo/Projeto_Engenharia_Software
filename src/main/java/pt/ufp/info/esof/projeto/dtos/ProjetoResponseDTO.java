package pt.ufp.info.esof.projeto.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProjetoResponseDTO {
    private String Name;
    private List<String> TarefasPrevistas=new ArrayList<>();
}

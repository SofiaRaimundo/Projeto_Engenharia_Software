package pt.ufp.info.esof.projeto.dtos;

import lombok.Data;
import pt.ufp.info.esof.projeto.models.Cliente;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProjetoResponseDTO {
    private String nome;
    private List<TarefaPrevistaCreateDTO> TarefasPrevistas = new ArrayList<>();
    private Cliente cliente;
}
package pt.ufp.info.esof.projeto.dtos;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;


@Data
public class ClienteResponseDTO {
    private String nome;
    private List<ProjetoCreateDTO> projetos = new ArrayList<>();
}
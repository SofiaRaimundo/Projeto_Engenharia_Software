package pt.ufp.info.esof.projeto.dtos;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;


@Data
public class ClienteResponseDTO {
    private List<String> projetos = new ArrayList<>();
}
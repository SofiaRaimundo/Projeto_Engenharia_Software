package pt.ufp.info.esof.projeto.services;

import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.models.TarefaEfetiva;

import java.util.List;

public interface EmpregadoService {
    List<Empregado> findAll();

}

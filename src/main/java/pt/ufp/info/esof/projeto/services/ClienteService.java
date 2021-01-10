package pt.ufp.info.esof.projeto.services;

import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.models.Projeto;
import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<Cliente> findAll();

    Optional<Cliente> findById(Long id);

    Optional<Cliente> criarCliente(Cliente converter);

    Optional<Cliente> adicionaProjeto(Long clienteId, Projeto projeto);
}
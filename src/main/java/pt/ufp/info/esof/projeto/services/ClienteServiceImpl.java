package pt.ufp.info.esof.projeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.repositories.ClienteRepository;
import pt.ufp.info.esof.projeto.repositories.ProjetoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private ClienteRepository clienteRepository;
    private ProjetoRepository projetoRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository, ProjetoRepository projetoRepository) {
        this.clienteRepository = clienteRepository;
        this.projetoRepository = projetoRepository;
    }

    @Override
    public List<Cliente> findAll() {
        List<Cliente> clientes = new ArrayList<>(); //cria uma lista de clientes
        clienteRepository.findAll().forEach(clientes::add); //vai ao repositório buscar o clientes e adiciona na lista criada
        return clientes;
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id); //retorna o cliente encontrado
    }

    @Override
    public Optional<Cliente> criarCliente(Cliente cliente) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(cliente.getId());
        if(optionalCliente.isEmpty()){
            clienteRepository.save(cliente);
            return Optional.of(clienteRepository.save(cliente));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Cliente> adicionaProjeto(Long clienteId, Projeto projeto) {
        Optional<Cliente> optionalCliente = this.clienteRepository.findById(clienteId);
        if(optionalCliente.isPresent()){
            Cliente cliente = optionalCliente.get();
            int quantidadeDeProjetosAntes = cliente.getProjetos().size();
            cliente.adicionaProjeto(projeto);
            int quantidadedeProjetosDepois = cliente.getProjetos().size();
            if(quantidadeDeProjetosAntes != quantidadedeProjetosDepois) {
                return Optional.of(cliente);
            }
        }
        return Optional.empty();
    }
}

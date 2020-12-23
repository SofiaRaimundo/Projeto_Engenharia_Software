package pt.ufp.info.esof.projeto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.repositories.ClienteRepository;
import java.util.Optional;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping()
    public ResponseEntity<Iterable<Cliente>> getAllCliente() {
        return ResponseEntity.ok(clienteRepository.findAll()); //retorna todos os clientes
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        return optionalCliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente) {
        Optional<Cliente> optionalCliente =clienteRepository.findById(cliente.getId());
        if(optionalCliente.isEmpty()){
            return ResponseEntity.ok(clienteRepository.save(cliente));
        }
        return ResponseEntity.badRequest().build();
    }

    @PatchMapping("/{clienteId}")
    public ResponseEntity<Cliente> adicionaProjeto(@PathVariable Long clienteId, @RequestBody Projeto projeto){
        Optional<Cliente> optionalCliente = this.clienteRepository.findById(clienteId);
        if(optionalCliente.isPresent()){
            Cliente cliente = optionalCliente.get();
            int quantidadeDeProjetosAntes = cliente.getProjetos().size();
            cliente.adicionaProjeto(projeto);
            int quantidadeDeProjetosDepois = cliente.getProjetos().size();
            if(quantidadeDeProjetosAntes != quantidadeDeProjetosDepois) {
                return ResponseEntity.ok(cliente);
            }
        }
        return ResponseEntity.badRequest().build();
    }
}

package pt.ufp.info.esof.projeto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.ufp.info.esof.projeto.dtos.ClienteCreateDTO;
import pt.ufp.info.esof.projeto.dtos.ClienteResponseDTO;
import pt.ufp.info.esof.projeto.dtos.ProjetoCreateDTO;
import pt.ufp.info.esof.projeto.dtos.conversores.ConverterClienteParaDTO;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.services.ClienteService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;
    private final ConverterClienteParaDTO converterClienteParaDTO = new ConverterClienteParaDTO();

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping()
    public ResponseEntity<Iterable<ClienteResponseDTO>> getAllCliente() {
        List<ClienteResponseDTO> responseDTOS = new ArrayList<>();
        clienteService.findAll().forEach(cliente -> responseDTOS.add(converterClienteParaDTO.converter(cliente)));
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> getClienteById(@PathVariable Long id) {
        Optional<Cliente> optionalCliente = clienteService.findById(id);
        return optionalCliente.map(cliente -> {
            ClienteResponseDTO clienteResponseDTO = converterClienteParaDTO.converter(cliente);
            return ResponseEntity.ok(clienteResponseDTO);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criarCliente(@RequestBody ClienteCreateDTO cliente) {
        Optional<Cliente> optionalCliente = clienteService.criarCliente(cliente.converter());
        return optionalCliente.map(value -> ResponseEntity.ok(converterClienteParaDTO.converter(value))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PatchMapping("/{clienteId}")
    public ResponseEntity<ClienteResponseDTO> adicionaProjeto(@PathVariable Long clienteId, @RequestBody ProjetoCreateDTO projeto){
        Optional<Cliente> optionalCliente = clienteService.adicionaProjeto(clienteId, projeto.converter());
        return optionalCliente.map(cliente -> ResponseEntity.ok(converterClienteParaDTO.converter(cliente))).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}

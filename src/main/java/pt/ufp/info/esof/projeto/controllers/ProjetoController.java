package pt.ufp.info.esof.projeto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.ufp.info.esof.projeto.dtos.ClienteResponseDTO;
import pt.ufp.info.esof.projeto.dtos.ProjetoCreateDTO;
import pt.ufp.info.esof.projeto.dtos.ProjetoResponseDTO;
import pt.ufp.info.esof.projeto.dtos.TarefaPrevistaCreateDTO;
import pt.ufp.info.esof.projeto.dtos.conversores.ConverterClienteParaDTO;
import pt.ufp.info.esof.projeto.dtos.conversores.ConverterProjetoParaDTO;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import pt.ufp.info.esof.projeto.repositories.ProjetoRepository;
import pt.ufp.info.esof.projeto.services.ClienteService;
import pt.ufp.info.esof.projeto.services.ProjetoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/projeto")
public class ProjetoController {

    private final ProjetoService projetoService;
    private final ConverterProjetoParaDTO converterProjetoParaDTO = new ConverterProjetoParaDTO();

    @Autowired
    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @GetMapping()
    public ResponseEntity<Iterable<ProjetoResponseDTO>> getAllProjeto() {
        List<ProjetoResponseDTO> responseDTOS = new ArrayList<>();
        projetoService.findAll().forEach(projeto -> responseDTOS.add(converterProjetoParaDTO.converter(projeto)));
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetoResponseDTO> getProjetoById(@PathVariable Long id) {
        Optional<Projeto> optionalProjeto = projetoService.findById(id);
        return optionalProjeto.map(projeto -> {
            ProjetoResponseDTO projetoResponseDTO = converterProjetoParaDTO.converter(projeto);
            return ResponseEntity.ok(projetoResponseDTO);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProjetoResponseDTO> criarProjeto(@RequestBody ProjetoCreateDTO projeto) {
        Optional<Projeto> optionalProjeto = projetoService.criarProjeto(projeto.converter());
        return optionalProjeto.map(value -> ResponseEntity.ok(converterProjetoParaDTO.converter(value))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PatchMapping("/{projetoId}")
    public ResponseEntity<ProjetoResponseDTO> adicionaTarefaPrevista(@PathVariable Long projetoId, @RequestBody TarefaPrevistaCreateDTO tarefaPrevista){
        Optional<Projeto> optionalProjeto = projetoService.adicionaTarefaPrevista(projetoId, tarefaPrevista.converter());
        return optionalProjeto.map(projeto -> ResponseEntity.ok(converterProjetoParaDTO.converter(projeto))).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}

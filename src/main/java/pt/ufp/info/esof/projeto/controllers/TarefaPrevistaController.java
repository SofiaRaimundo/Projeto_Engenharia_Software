package pt.ufp.info.esof.projeto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.ufp.info.esof.projeto.dtos.TarefaEfetivaCreateDTO;
import pt.ufp.info.esof.projeto.dtos.TarefaPrevistaCreateDTO;
import pt.ufp.info.esof.projeto.dtos.TarefaPrevistaResponseDTO;
import pt.ufp.info.esof.projeto.dtos.conversores.ConverterTarefaPrevistaParaDTO;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import pt.ufp.info.esof.projeto.services.TarefaPrevistaService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tarefaPrevista")
public class TarefaPrevistaController {

    private final TarefaPrevistaService tarefaPrevistaService;
    private final ConverterTarefaPrevistaParaDTO converterTarefaPrevistaParaDTO = new ConverterTarefaPrevistaParaDTO();

    @Autowired
    public TarefaPrevistaController(TarefaPrevistaService tarefaPrevistaService) {
        this.tarefaPrevistaService = tarefaPrevistaService;
    }

    @GetMapping()
    public ResponseEntity<Iterable<TarefaPrevistaResponseDTO>> getAllTarefaPrevista() {
        List<TarefaPrevistaResponseDTO> responseDTOS = new ArrayList<>();
        tarefaPrevistaService.findAll().forEach(tarefaPrevista -> responseDTOS.add(converterTarefaPrevistaParaDTO.converter(tarefaPrevista)));
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaPrevistaResponseDTO> getTarefaPrevistaById(@PathVariable Long id) {
        Optional<TarefaPrevista> optionalTarefaPrevista = tarefaPrevistaService.findById(id);
        return optionalTarefaPrevista.map(tarefaPrevista -> {
            TarefaPrevistaResponseDTO tarefaPrevistaResponseDTO = converterTarefaPrevistaParaDTO.converter(tarefaPrevista);
            return ResponseEntity.ok(tarefaPrevistaResponseDTO);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TarefaPrevistaResponseDTO> criarTarefaPrevista(@RequestBody TarefaPrevistaCreateDTO tarefaPrevista) {
        Optional<TarefaPrevista> optionalTarefaPrevista = tarefaPrevistaService.criarTarefaPrevista(tarefaPrevista.converter());
        return optionalTarefaPrevista.map(value -> ResponseEntity.ok(converterTarefaPrevistaParaDTO.converter(value))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PatchMapping("/{tarefaPrevistaId}")
    public ResponseEntity<TarefaPrevistaResponseDTO> adicionaTarefaEfetiva(@PathVariable Long tarefaPrevistaId, @RequestBody TarefaEfetivaCreateDTO tarefaEfetiva){
        Optional<TarefaPrevista> optionalTarefaPrevista = tarefaPrevistaService.adicionaTarefaEfetiva(tarefaPrevistaId, tarefaEfetiva.converter());
        return optionalTarefaPrevista.map(tarefaPrevista -> ResponseEntity.ok(converterTarefaPrevistaParaDTO.converter(tarefaPrevista))).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
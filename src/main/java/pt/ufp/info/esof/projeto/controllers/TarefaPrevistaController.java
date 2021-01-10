package pt.ufp.info.esof.projeto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.ufp.info.esof.projeto.models.TarefaEfetiva;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import pt.ufp.info.esof.projeto.repositories.TarefaPrevistaRepository;
import java.util.Optional;

@Controller
@RequestMapping("/tarefaPrevista")
public class TarefaPrevistaController {
    private final TarefaPrevistaRepository tarefaPrevistaRepository;

    @Autowired
    public TarefaPrevistaController(TarefaPrevistaRepository tarefaPrevistaRepository) {
        this.tarefaPrevistaRepository = tarefaPrevistaRepository;
    }

    @GetMapping()
    public ResponseEntity<Iterable<TarefaPrevista>> getAllTarefaPrevista() {
        return ResponseEntity.ok(tarefaPrevistaRepository.findAll()); //retorna todas as tarefas previstas
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaPrevista> getTarefaPrevistaById(@PathVariable Long id) {
        Optional<TarefaPrevista> optionalTarefaPrevista = tarefaPrevistaRepository.findById(id);
        return optionalTarefaPrevista.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TarefaPrevista> criarTarefaPrevista(@RequestBody TarefaPrevista tarefaPrevista) {
        Optional<TarefaPrevista> optionalTarefaPrevista =tarefaPrevistaRepository.findById(tarefaPrevista.getId());
        if(optionalTarefaPrevista.isEmpty()){
            return ResponseEntity.ok(tarefaPrevistaRepository.save(tarefaPrevista));
        }
        return ResponseEntity.badRequest().build();
    }

    @PatchMapping("/{tarefaPrevistaId}")
    public ResponseEntity<TarefaPrevista> adicionaTarefaEfetiva(@PathVariable Long tarefaPrevistaId, @RequestBody TarefaEfetiva tarefaEfetiva){
        Optional<TarefaPrevista> optionalTarefaPrevista = this.tarefaPrevistaRepository.findById(tarefaPrevistaId);
        if(optionalTarefaPrevista.isPresent()){
            TarefaPrevista tarefaPrevista = optionalTarefaPrevista.get();
            int quantidadeDeTarefaEAntes = tarefaPrevista.getTarefasEfetivas().size();
            tarefaPrevista.adicionaTarefaEfetiva(tarefaEfetiva);
            int quantidadeDeTarefaEDepois = tarefaPrevista.getTarefasEfetivas().size();
            if(quantidadeDeTarefaEAntes != quantidadeDeTarefaEDepois) {
                return ResponseEntity.ok(tarefaPrevista);
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
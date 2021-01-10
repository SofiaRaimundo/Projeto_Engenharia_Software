package pt.ufp.info.esof.projeto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import pt.ufp.info.esof.projeto.repositories.ProjetoRepository;
import java.util.Optional;

@Controller
@RequestMapping("/projeto")
public class ProjetoController {
    private final ProjetoRepository projetoRepository;

    @Autowired
    public ProjetoController(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    @GetMapping()
    public ResponseEntity<Iterable<Projeto>> getAllProjeto() {
        return ResponseEntity.ok(projetoRepository.findAll()); //retorna todos os projetos
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> getProjetoById(@PathVariable Long id) {
        Optional<Projeto> optionalProjeto = projetoRepository.findById(id);
        return optionalProjeto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Projeto> criarProjeto(@RequestBody Projeto projeto) {
        Optional<Projeto> optionalProjeto = projetoRepository.findById(projeto.getId());
        if(optionalProjeto.isEmpty()){
            return ResponseEntity.ok(projetoRepository.save(projeto));
        }
        return ResponseEntity.badRequest().build();
    }

    @PatchMapping("/{projetoId}")
    public ResponseEntity<Projeto> adicionaTarefaPrevista(@PathVariable Long projetoId, @RequestBody TarefaPrevista tarefaPrevista){
        Optional<Projeto> optionalProjeto = this.projetoRepository.findById(projetoId);
        if(optionalProjeto.isPresent()){
            Projeto projeto = optionalProjeto.get();
            int quantidadeDeProjetosAntes = projeto.getTarefasPrevistas().size();
            projeto.adicionaTarefaPrevista(tarefaPrevista);
            int quantidadeDeProjetosDepois = projeto.getTarefasPrevistas().size();
            if(quantidadeDeProjetosAntes != quantidadeDeProjetosDepois) {
                return ResponseEntity.ok(projeto);
            }
        }
        return ResponseEntity.badRequest().build();
    }
}

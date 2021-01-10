package pt.ufp.info.esof.projeto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import pt.ufp.info.esof.projeto.repositories.ClienteRepository;
import pt.ufp.info.esof.projeto.repositories.EmpregadoRepository;

import java.util.Optional;

@Controller
@RequestMapping("/empregado")
public class EmpregadoController {
    private final EmpregadoRepository empregadoRepository;

    @Autowired
    public EmpregadoController(EmpregadoRepository empregadoRepository) {
        this.empregadoRepository = empregadoRepository;
    }

    @GetMapping()
    public ResponseEntity<Iterable<Empregado>> getAllEmpregado() {
        return ResponseEntity.ok(empregadoRepository.findAll()); //retorna todos os empregados
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empregado> getEmpregadoById(@PathVariable Long id) {
        Optional<Empregado> optionalEmpregado = empregadoRepository.findById(id);
        return optionalEmpregado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Empregado> criarEmpregado(@RequestBody Empregado empregado) {
        Optional<Empregado> optionalEmpregado = empregadoRepository.findById(empregado.getId());
        if (optionalEmpregado.isEmpty()) {
            return ResponseEntity.ok(empregadoRepository.save(empregado));
        }
        return ResponseEntity.badRequest().build();

    }

    @PatchMapping("/{empregadoId}")
    public ResponseEntity<Empregado> adicionaTarefaP(@PathVariable Long empregadoId, @RequestBody TarefaPrevista tarefaPrevista) {
        Optional<Empregado> optionalEmpregado = this.empregadoRepository.findById(empregadoId);
        if (optionalEmpregado.isPresent()) {
            Empregado empregado = optionalEmpregado.get();
            int quantidadeDeTarefaPAntes = empregado.getTarefasPrevistas().size();
            empregado.adicionaTarefaP(tarefaPrevista);
            int quantidadeDeTarefaPDepois = empregado.getTarefasPrevistas().size();
            if (quantidadeDeTarefaPAntes != quantidadeDeTarefaPDepois) {
                return ResponseEntity.ok(empregado);
            }
        }
        return ResponseEntity.badRequest().build();
    }
}


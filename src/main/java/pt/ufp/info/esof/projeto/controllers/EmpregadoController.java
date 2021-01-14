package pt.ufp.info.esof.projeto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.ufp.info.esof.projeto.dtos.EmpregadoCreateDTO;
import pt.ufp.info.esof.projeto.dtos.EmpregadoResponseDTO;
import pt.ufp.info.esof.projeto.dtos.TarefaPrevistaCreateDTO;
import pt.ufp.info.esof.projeto.dtos.conversores.ConverterEmpregadoParaDTO;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.services.EmpregadoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/empregado")
public class EmpregadoController {
    private final EmpregadoService empregadoService;
    private final ConverterEmpregadoParaDTO converterEmpregadoParaDTO = new ConverterEmpregadoParaDTO();

    @Autowired
    public EmpregadoController(EmpregadoService empregadoService) {
        this.empregadoService = empregadoService;
    }

    @GetMapping()
    public ResponseEntity<Iterable<EmpregadoResponseDTO>> getAllEmpregado() {
        List<EmpregadoResponseDTO> responseDTOS = new ArrayList<>();
        empregadoService.findAll().forEach(empregado -> responseDTOS.add(converterEmpregadoParaDTO.converter(empregado)));
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpregadoResponseDTO> getEmpregadoById(@PathVariable Long id){
        Optional<Empregado> optionalEmpregado = empregadoService.findById(id);
        return optionalEmpregado.map(empregado -> {
            EmpregadoResponseDTO empregadoResponseDTO = converterEmpregadoParaDTO.converter(empregado);
            return ResponseEntity.ok(empregadoResponseDTO);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EmpregadoResponseDTO> criarEmpregado(@RequestBody EmpregadoCreateDTO empregadoCreateDTO){
        Optional<Empregado> optionalEmpregado = empregadoService.criarEmpregado(empregadoCreateDTO.converter());
        return optionalEmpregado.map(value -> ResponseEntity.ok(converterEmpregadoParaDTO.converter(value))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PatchMapping("/{empregadoId}")
    public ResponseEntity<EmpregadoResponseDTO> adicionaTarefaPrevista(@PathVariable Long empregadoId, @RequestBody TarefaPrevistaCreateDTO tarefaPrevista){
        Optional<Empregado> optionalEmpregado = empregadoService.adicionaTarefaPrevista(empregadoId, tarefaPrevista.converter());
        return optionalEmpregado.map(empregado -> ResponseEntity.ok(converterEmpregadoParaDTO.converter(empregado))).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}


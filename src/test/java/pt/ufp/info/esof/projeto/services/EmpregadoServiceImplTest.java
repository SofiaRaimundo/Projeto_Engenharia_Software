package pt.ufp.info.esof.projeto.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import pt.ufp.info.esof.projeto.repositories.EmpregadoRepository;
import pt.ufp.info.esof.projeto.repositories.TarefaPrevistaRepository;
import java.util.ArrayList;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = EmpregadoServiceImpl.class)
class EmpregadoServiceImplTest {

    @Autowired
    private EmpregadoService empregadoService;

    @MockBean
    private EmpregadoRepository empregadoRepository;

    @MockBean
    private TarefaPrevistaRepository tarefaPrevistaRepository;

    @Test
    void findAll() {
        when(empregadoRepository.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(empregadoService.findAll());
    }

    @Test
    void findById() {
        when(empregadoRepository.findById(1L)).thenReturn(Optional.of(new Empregado()));
        assertTrue(empregadoService.findById(1L).isPresent()); //encontra o empregado com id 1
        assertTrue(empregadoService.findById(2L).isEmpty()); //mas não com id 2
    }

    @Test
    void criarEmpregado() {
        Empregado empregado = new Empregado();
        empregado.setNome("Empregado teste");
        empregado.setId(1L);

        when(empregadoRepository.save(empregado)).thenReturn(empregado);

        assertTrue(empregadoService.criarEmpregado(empregado).isPresent());

        empregado.setId(2L);
        when(empregadoRepository.findById(2L)).thenReturn(Optional.of(empregado));
        assertTrue(empregadoService.criarEmpregado(empregado).isEmpty());
    }

    @Test
    void adicionaTarefaPrevista() {
        TarefaPrevista tarefaPrevista = new TarefaPrevista();
        tarefaPrevista.setId(1L);
        tarefaPrevista.setNome("Tarefa prevista teste");

        Empregado empregado = new Empregado();

        when(empregadoRepository.findById(1L)).thenReturn(Optional.of(empregado));

        assertTrue(empregadoService.adicionaTarefaPrevista(1L,tarefaPrevista).isPresent());
        assertTrue(empregadoService.adicionaTarefaPrevista(2L,tarefaPrevista).isEmpty());
    }
}
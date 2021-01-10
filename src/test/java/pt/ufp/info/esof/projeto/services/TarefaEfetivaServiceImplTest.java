package pt.ufp.info.esof.projeto.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.info.esof.projeto.models.TarefaEfetiva;
import pt.ufp.info.esof.projeto.repositories.TarefaEfetivaRepository;
import java.util.ArrayList;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = TarefaEfetivaServiceImpl.class)
class TarefaEfetivaServiceImplTest {

    @Autowired
    private TarefaEfetivaService tarefaEfetivaService;

    @MockBean
    private TarefaEfetivaRepository tarefaEfetivaRepository;

    @Test
    void findAll() {
        when(tarefaEfetivaRepository.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(tarefaEfetivaService.findAll());
    }

    @Test
    void findById() {
        when(tarefaEfetivaRepository.findById(1L)).thenReturn(Optional.of(new TarefaEfetiva()));
        assertTrue(tarefaEfetivaService.findById(1L).isPresent()); //encontra a tarefa efetiva com id 1
        assertTrue(tarefaEfetivaService.findById(2L).isEmpty()); //mas não com id 2
    }

    @Test
    void criarTarefaEfetiva() {
        TarefaEfetiva tarefaEfetiva = new TarefaEfetiva();
        tarefaEfetiva.setId(1L);
        tarefaEfetiva.setNome("Tarefa efetiva teste");

        when(tarefaEfetivaRepository.save(tarefaEfetiva)).thenReturn(tarefaEfetiva);

        assertTrue(tarefaEfetivaService.criarTarefaEfetiva(tarefaEfetiva).isPresent());

        tarefaEfetiva.setId(2L);
        when(tarefaEfetivaRepository.findById(2L)).thenReturn(Optional.of(tarefaEfetiva));
        assertTrue(tarefaEfetivaService.criarTarefaEfetiva(tarefaEfetiva).isEmpty());
    }
}
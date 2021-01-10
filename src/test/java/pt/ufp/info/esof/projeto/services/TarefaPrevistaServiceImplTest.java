package pt.ufp.info.esof.projeto.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.info.esof.projeto.models.TarefaEfetiva;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import pt.ufp.info.esof.projeto.repositories.TarefaEfetivaRepository;
import pt.ufp.info.esof.projeto.repositories.TarefaPrevistaRepository;
import java.util.ArrayList;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = TarefaPrevistaServiceImpl.class)
class TarefaPrevistaServiceImplTest {

    @Autowired
    private TarefaPrevistaService tarefaPrevistaService;

    @MockBean
    private TarefaPrevistaRepository tarefaPrevistaRepository;

    @MockBean
    private TarefaEfetivaRepository tarefaEfetivaRepository;

    @Test
    void findAll() {
        when(tarefaPrevistaRepository.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(tarefaPrevistaService.findAll());
    }

    @Test
    void findById() {
        when(tarefaPrevistaRepository.findById(1L)).thenReturn(Optional.of(new TarefaPrevista()));
        assertTrue(tarefaPrevistaService.findById(1L).isPresent()); //encontra a tarefa com id 1
        assertTrue(tarefaPrevistaService.findById(2L).isEmpty()); //mas não com id 2
    }

    @Test
    void criarTarefaPrevista() {
        TarefaPrevista tarefaPrevista = new TarefaPrevista();
        tarefaPrevista.setNome("Tarefa prevista teste");
        tarefaPrevista.setId(1L);

        when(tarefaPrevistaRepository.save(tarefaPrevista)).thenReturn(tarefaPrevista);

        assertTrue(tarefaPrevistaService.criarTarefaPrevista(tarefaPrevista).isPresent());

        tarefaPrevista.setId(2L);
        when(tarefaPrevistaRepository.findById(2L)).thenReturn(Optional.of(tarefaPrevista));
        assertTrue(tarefaPrevistaService.criarTarefaPrevista(tarefaPrevista).isEmpty());
    }

    @Test
    void adicionaTarefaEfetiva() {
        TarefaEfetiva tarefaEfetiva = new TarefaEfetiva();
        tarefaEfetiva.setId(1L);
        tarefaEfetiva.setNome("Tarefa efetiva teste");

        TarefaPrevista tarefaPrevista = new TarefaPrevista();

        when(tarefaPrevistaRepository.findById(1L)).thenReturn(Optional.of(tarefaPrevista));

        assertTrue(tarefaPrevistaService.adicionaTarefaEfetiva(1L, tarefaEfetiva).isPresent());
        assertTrue(tarefaPrevistaService.adicionaTarefaEfetiva(2L, tarefaEfetiva).isEmpty());
    }
}
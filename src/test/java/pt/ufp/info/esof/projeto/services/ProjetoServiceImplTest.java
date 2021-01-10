package pt.ufp.info.esof.projeto.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import pt.ufp.info.esof.projeto.repositories.ProjetoRepository;
import pt.ufp.info.esof.projeto.repositories.TarefaPrevistaRepository;
import java.util.ArrayList;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ProjetoServiceImpl.class)
class ProjetoServiceImplTest {

    @Autowired
    private ProjetoService projetoService;

    @MockBean
    private ProjetoRepository projetoRepository;

    @MockBean
    private TarefaPrevistaRepository tarefaPrevistaRepository;

    @Test
    void findAll() {
        when(projetoRepository.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(projetoService.findAll());
    }

    @Test
    void findById() {
        when(projetoRepository.findById(1L)).thenReturn(Optional.of(new Projeto()));
        assertTrue(projetoService.findById(1L).isPresent()); //encontra o projeto com id 1
        assertTrue(projetoService.findById(2L).isEmpty()); //mas não com id 2
    }

    @Test
    void criarProjeto() {
        Projeto projeto = new Projeto();
        projeto.setNome("Projeto teste");
        projeto.setId(1L);

        when(projetoRepository.save(projeto)).thenReturn(projeto);

        assertTrue(projetoService.criarProjeto(projeto).isPresent());

        projeto.setId(2L);
        when(projetoRepository.findById(2L)).thenReturn(Optional.of(projeto));
        assertTrue(projetoService.criarProjeto(projeto).isEmpty());
    }

    @Test
    void adicionaTarefaPrevista() {
        TarefaPrevista tarefaPrevista = new TarefaPrevista();
        tarefaPrevista.setId(1L);
        tarefaPrevista.setNome("Tarefa prevista teste");

        Projeto projeto = new Projeto();

        when(projetoRepository.findById(1L)).thenReturn(Optional.of(projeto));

        assertTrue(projetoService.adicionaTarefaPrevista(1L,tarefaPrevista).isPresent());
        assertTrue(projetoService.adicionaTarefaPrevista(2L,tarefaPrevista).isEmpty());
    }
}
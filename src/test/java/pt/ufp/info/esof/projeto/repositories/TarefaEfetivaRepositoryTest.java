package pt.ufp.info.esof.projeto.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.models.TarefaEfetiva;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TarefaEfetivaRepositoryTest {

    @Autowired
    TarefaEfetivaRepository tarefaEfetivaRepository;

    @Autowired
    TarefaPrevistaRepository tarefaPrevistaRepository;

    @Autowired
    ProjetoRepository projetoRepository;

    @Test
    public void testaCriacaoTarefaEfetiva() {
        TarefaEfetiva tarefaEfetiva = new TarefaEfetiva(); //cria uma nova tarefa efetiva
        String nome = "Tarefa efetiva 1";
        tarefaEfetiva.setNome(nome);

        TarefaPrevista tarefaPrevista = new TarefaPrevista();
        tarefaPrevista.setNome("Tarefa prevista 1");
        tarefaPrevista.adicionaTarefaEfetiva(tarefaEfetiva);

        Projeto projeto = new Projeto();
        projeto.setNome("Projeto 1");
        projeto.adicionaTarefaPrevista(tarefaPrevista);

        assertEquals(0, tarefaEfetivaRepository.count());
        assertEquals(0, tarefaPrevistaRepository.count());
        assertEquals(0, projetoRepository.count());

        tarefaEfetivaRepository.save(tarefaEfetiva);
        tarefaPrevistaRepository.save(tarefaPrevista);
        projetoRepository.save(projeto);

        assertEquals(1, tarefaEfetivaRepository.count());
        assertEquals(1, tarefaPrevistaRepository.count());
        assertEquals(1, projetoRepository.count());

        assertNotNull(tarefaEfetivaRepository.findByNome(nome));
    }

}
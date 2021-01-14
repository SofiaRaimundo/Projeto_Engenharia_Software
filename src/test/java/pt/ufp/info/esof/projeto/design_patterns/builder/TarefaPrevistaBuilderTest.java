package pt.ufp.info.esof.projeto.design_patterns.builder;

import org.junit.jupiter.api.Test;
import pt.ufp.info.esof.projeto.models.TarefaEfetiva;
import static org.junit.jupiter.api.Assertions.*;

class TarefaPrevistaBuilderTest {

    @Test
    void setNome() {
        TarefaPrevistaBuilder tarefaPrevistaBuilder = new TarefaPrevistaBuilder(); //cria uma nova tarefaEfetivaBuilder
        tarefaPrevistaBuilder.setNome("Tarefa teste 1");
        assertEquals("Tarefa teste 1", tarefaPrevistaBuilder.getTarefaPrevista().getNome());

        tarefaPrevistaBuilder.setNome("Tarefa teste 2");
        assertEquals("Tarefa teste 2", tarefaPrevistaBuilder.getTarefaPrevista().getNome());
    }

    @Test
    void setTempoPrevisto() {
        TarefaPrevistaBuilder tarefaPrevistaBuilder = new TarefaPrevistaBuilder(); //cria uma nova tarefaEfetivaBuilder
        tarefaPrevistaBuilder.setTempoPrevisto(30);
        assertEquals(30, tarefaPrevistaBuilder.getTarefaPrevista().getTempoPrevisto());

        tarefaPrevistaBuilder.setTempoPrevisto(40);
        assertEquals(40, tarefaPrevistaBuilder.getTarefaPrevista().getTempoPrevisto());
    }

    @Test
    void adicionaTarefaEfetiva() {
        TarefaPrevistaBuilder tarefaPrevistaBuilder = new TarefaPrevistaBuilder();
        TarefaEfetiva tarefaEfetiva = new TarefaEfetiva();

        assertEquals(0, tarefaPrevistaBuilder.getTarefaPrevista().getTarefasEfetivas().size());

        tarefaPrevistaBuilder.adicionaTarefaEfetiva(tarefaEfetiva);

        assertEquals(1, tarefaPrevistaBuilder.getTarefaPrevista().getTarefasEfetivas().size());
    }
}
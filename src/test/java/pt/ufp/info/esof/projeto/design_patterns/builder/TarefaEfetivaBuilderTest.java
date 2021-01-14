package pt.ufp.info.esof.projeto.design_patterns.builder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TarefaEfetivaBuilderTest {

    @Test
    void setNome() {
        TarefaEfetivaBuilder tarefaEfetivaBuilder = new TarefaEfetivaBuilder(); //cria uma nova tarefaEfetivaBuilder
        tarefaEfetivaBuilder.setNome("Tarefa teste 1");
        assertEquals("Tarefa teste 1", tarefaEfetivaBuilder.getTarefaEfetiva().getNome());

        tarefaEfetivaBuilder.setNome("Tarefa teste 2");
        assertEquals("Tarefa teste 2", tarefaEfetivaBuilder.getTarefaEfetiva().getNome());
    }

    @Test
    void setProgresso() {
        TarefaEfetivaBuilder tarefaEfetivaBuilder = new TarefaEfetivaBuilder(); //cria uma nova tarefaEfetivaBuilder
        tarefaEfetivaBuilder.setProgresso(20);
        assertEquals(20, tarefaEfetivaBuilder.getTarefaEfetiva().getProgresso());

        tarefaEfetivaBuilder.setProgresso(40);
        assertEquals(40, tarefaEfetivaBuilder.getTarefaEfetiva().getProgresso());
    }

    @Test
    void setTempoTrabalhado() {
        TarefaEfetivaBuilder tarefaEfetivaBuilder = new TarefaEfetivaBuilder(); //cria uma nova tarefaEfetivaBuilder
        tarefaEfetivaBuilder.setTempoTrabalhado(3);
        assertEquals(3, tarefaEfetivaBuilder.getTarefaEfetiva().getTempoTrabalhado());

        tarefaEfetivaBuilder.setTempoTrabalhado(6);
        assertEquals(6, tarefaEfetivaBuilder.getTarefaEfetiva().getTempoTrabalhado());
    }
}
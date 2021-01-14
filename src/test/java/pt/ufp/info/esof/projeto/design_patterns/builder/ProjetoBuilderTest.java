package pt.ufp.info.esof.projeto.design_patterns.builder;

import org.junit.jupiter.api.Test;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import static org.junit.jupiter.api.Assertions.*;

class ProjetoBuilderTest {

    @Test
    void setNome() {
        ProjetoBuilder projetoBuilder = new ProjetoBuilder(); //cria um novo projetoBuilder
        projetoBuilder.setNome("Projeto teste 1");
        assertEquals("Projeto teste 1", projetoBuilder.getProjeto().getNome());

        projetoBuilder.setNome("Projeto teste 2");
        assertEquals("Projeto teste 2", projetoBuilder.getProjeto().getNome());
    }

    @Test
    void adicionaTarefaPrevista() {
        ProjetoBuilder projetoBuilder = new ProjetoBuilder();
        TarefaPrevista tarefaPrevista = new TarefaPrevista();

        assertEquals(0, projetoBuilder.getProjeto().getTarefasPrevistas().size());

        projetoBuilder.adicionaTarefaPrevista(tarefaPrevista);

        assertEquals(1, projetoBuilder.getProjeto().getTarefasPrevistas().size());
    }
}
package pt.ufp.info.esof.projeto.design_patterns.builder;

import org.junit.jupiter.api.Test;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import static org.junit.jupiter.api.Assertions.*;

class EmpregadoBuilderTest {

    @Test
    void setNome() {
        EmpregadoBuilder empregadoBuilder = new EmpregadoBuilder(); //cria um novo empregadoBuilder
        empregadoBuilder.setNome("Empregado teste 1");
        assertEquals("Empregado teste 1", empregadoBuilder.getEmpregado().getNome());

        empregadoBuilder.setNome("Empregado teste 2");
        assertEquals("Empregado teste 2", empregadoBuilder.getEmpregado().getNome());
    }

    @Test
    void adicionaTarefaPrevista() {
        EmpregadoBuilder empregadoBuilder = new EmpregadoBuilder();
        TarefaPrevista tarefaPrevista = new TarefaPrevista();

        assertEquals(0, empregadoBuilder.getEmpregado().getTarefasPrevistas().size());

        empregadoBuilder.adicionaTarefaPrevista(tarefaPrevista);

        assertEquals(1, empregadoBuilder.getEmpregado().getTarefasPrevistas().size());
    }
}
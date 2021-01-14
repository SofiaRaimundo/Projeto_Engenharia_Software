package pt.ufp.info.esof.projeto.design_patterns.builder;

import org.junit.jupiter.api.Test;
import pt.ufp.info.esof.projeto.models.Projeto;
import static org.junit.jupiter.api.Assertions.*;

class ClienteBuilderTest {

    @Test
    void setNome() {
        ClienteBuilder clienteBuilder = new ClienteBuilder(); //cria um novo clienteBuilder
        clienteBuilder.setNome("Cliente teste 1");
        assertEquals("Cliente teste 1", clienteBuilder.getCliente().getNome());

        clienteBuilder.setNome("Cliente teste 2");
        assertEquals("Cliente teste 2", clienteBuilder.getCliente().getNome());
    }

    @Test
    void adicionaProjeto() {
        ClienteBuilder clienteBuilder = new ClienteBuilder();
        Projeto projeto = new Projeto();

        assertEquals(0, clienteBuilder.getCliente().getProjetos().size());

        clienteBuilder.adicionaProjeto(projeto);

        assertEquals(1, clienteBuilder.getCliente().getProjetos().size());
    }

}
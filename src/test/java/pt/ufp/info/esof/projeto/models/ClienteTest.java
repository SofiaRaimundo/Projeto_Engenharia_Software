package pt.ufp.info.esof.projeto.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void adicionaProjeto() {
        Projeto projeto = new Projeto(); //cria um novo projeto
        projeto.setNome("Projeto teste 1");

        Cliente cliente = new Cliente(); //cria um novo cliente
        cliente.nome = "AAA"; //nome do cliente de teste
        projeto.setCliente(cliente); //associa o cliente ao projeto

        cliente.adicionaProjeto(projeto); //adiciona o projeto ao cliente

        assertEquals(1, cliente.getProjetos().size()); //testa se adicionou ou não, é esperado que o número de projetos do cliente seja 1
    }

    @Test
    void removeProjeto() {
        Projeto projeto = new Projeto(); //cria um novo projeto
        projeto.setNome("Projeto teste 2");

        Cliente cliente = new Cliente(); //cria um novo cliente
        cliente.nome = "BBB"; //nome do cliente de teste
        projeto.setCliente(cliente);

        cliente.adicionaProjeto(projeto); //adiciona o projeto ao cliente
        assertEquals(1, cliente.getProjetos().size());

        cliente.removeProjeto(projeto); //remove o projeto do cliente
        assertEquals(0, cliente.getProjetos().size()); //como só tinha 1, é esperado que fique com 0 projetos
    }
}
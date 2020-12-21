package pt.ufp.info.esof.projeto.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pt.ufp.info.esof.projeto.models.Cliente;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ClienteRepositoryTest {

    @Autowired
    ClienteRepository clienteRepository;

    @Test
    public void testeCriacaoCliente() {
        Cliente cliente = new Cliente(); //cria um novo cliente
        cliente.setNome("Cliente 1");

        assertEquals(0, clienteRepository.count()); //espera-se que antes de guardar o cliente não tenha nada no repositorio

        clienteRepository.save(cliente); //guarda o cliente no repositorio

        assertEquals(1, clienteRepository.count()); //depois de guardar espera-se que tenha 1
    }

}
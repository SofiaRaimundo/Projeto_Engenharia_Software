package pt.ufp.info.esof.projeto.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.repositories.ClienteRepository;
import pt.ufp.info.esof.projeto.repositories.ProjetoRepository;
import java.util.ArrayList;
import java.util.Optional;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ClienteServiceImpl.class)
class ClienteServiceImplTest {

    @Autowired
    private ClienteService clienteService;

    @MockBean
    private ClienteRepository clienteRepository;

    @MockBean
    private ProjetoRepository projetoRepository;

    @Test
    void findAll() {
        when(clienteRepository.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(clienteService.findAll());
    }

    @Test
    void findById() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(new Cliente()));
        assertTrue(clienteService.findById(1L).isPresent()); //encontra o cliente com id 1
        assertTrue(clienteService.findById(2L).isEmpty()); //mas não com id 2
    }

    @Test
    void criarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente teste");
        cliente.setId(1L);

        when(clienteRepository.save(cliente)).thenReturn(cliente);

        assertTrue(clienteService.criarCliente(cliente).isPresent());

        cliente.setId(2L);
        when(clienteRepository.findById(2L)).thenReturn(Optional.of(cliente));
        assertTrue(clienteService.criarCliente(cliente).isEmpty());
    }

    @Test
    void adicionaProjeto() {
        Projeto projeto = new Projeto();
        projeto.setId(1L);
        projeto.setNome("Projeto teste");

        Cliente cliente = new Cliente();

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        assertTrue(clienteService.adicionaProjeto(1L,projeto).isPresent());
        assertTrue(clienteService.adicionaProjeto(2L,projeto).isEmpty());
    }
}
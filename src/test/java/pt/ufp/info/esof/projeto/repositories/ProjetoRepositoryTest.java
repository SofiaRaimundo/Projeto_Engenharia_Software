package pt.ufp.info.esof.projeto.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pt.ufp.info.esof.projeto.models.*;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProjetoRepositoryTest {

    @Autowired
    ProjetoRepository projetoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EmpregadoRepository empregadoRepository;

    @Autowired
    TarefaPrevistaRepository tarefaPrevistaRepository;

    @Test
    void testaCriacaoProjeto() {
        Projeto projeto = new Projeto(); //cria um novo projeto
        String nome = "Projeto 1";
        projeto.setNome(nome);

        Cliente cliente = new Cliente();
        cliente.setNome("Cliente 1");
        cliente.adicionaProjeto(projeto);
        projeto.setCliente(cliente);

        TarefaPrevista tarefaPrevista = new TarefaPrevista();
        tarefaPrevista.setNome("Tarefa prevista 1");
        tarefaPrevista.setProjeto(projeto);
        projeto.adicionaTarefaPrevista(tarefaPrevista);

        Empregado empregado = new Empregado();
        empregado.setNome("Empregado 1");
        empregado.setCargo(Cargo.DEV_JR);
        empregado.adicionaTarefaP(tarefaPrevista);
        tarefaPrevista.setEmpregado(empregado);

        assertEquals(0, projetoRepository.count()); //espera-se 0 antes de guardar
        assertEquals(0, clienteRepository.count());
        assertEquals(0, tarefaPrevistaRepository.count());
        assertEquals(0, empregadoRepository.count());

        assertNull(cliente.getId());
        assertNull(projeto.getId());
        assertNull(tarefaPrevista.getId());
        assertNull(empregado.getId());

        projetoRepository.save(projeto);
        clienteRepository.save(cliente);
        tarefaPrevistaRepository.save(tarefaPrevista);
        empregadoRepository.save(empregado);

        assertEquals(1, projetoRepository.count());
        assertEquals(1, clienteRepository.count());
        assertEquals(1, tarefaPrevistaRepository.count());
        assertEquals(1, empregadoRepository.count());

        assertNotNull(projetoRepository.findByNome(nome)); //espera-se que ao procurar o projeto pelo nome não seja null
    }
}
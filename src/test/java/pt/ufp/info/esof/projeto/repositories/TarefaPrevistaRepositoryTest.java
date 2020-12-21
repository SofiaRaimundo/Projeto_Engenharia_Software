package pt.ufp.info.esof.projeto.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pt.ufp.info.esof.projeto.models.Cargo;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TarefaPrevistaRepositoryTest {

    @Autowired
    TarefaPrevistaRepository tarefaPrevistaRepository;

    @Autowired
    ProjetoRepository projetoRepository;

    @Autowired
    EmpregadoRepository empregadoRepository;

    @Test
    public void testaCriacaoTarefaPrevista() {
        TarefaPrevista tarefaPrevista = new TarefaPrevista();
        String nome = "Tarefa Prevista 1";
        tarefaPrevista.setNome(nome);

        Projeto projeto = new Projeto();
        projeto.setNome("Projeto 1");
        projeto.adicionaTarefaPrevista(tarefaPrevista);

        Empregado empregado = new Empregado();
        empregado.setNome("Empregado 1");
        empregado.setCargo(Cargo.DEV_JR);
        empregado.adicionaTarefaP(tarefaPrevista);
        tarefaPrevista.setEmpregado(empregado);

        assertEquals(0, tarefaPrevistaRepository.count());
        assertEquals(0, projetoRepository.count());
        assertEquals(0, empregadoRepository.count());

        tarefaPrevistaRepository.save(tarefaPrevista);
        projetoRepository.save(projeto);
        empregadoRepository.save(empregado);

        assertEquals(1, tarefaPrevistaRepository.count());
        assertEquals(1, projetoRepository.count());
        assertEquals(1, empregadoRepository.count());

        assertNotNull(tarefaPrevistaRepository.findByNome(nome));
        assertNotNull(tarefaPrevistaRepository.findByProjeto(projeto));
        assertNotNull(tarefaPrevistaRepository.findByEmpregado(empregado));
    }

}
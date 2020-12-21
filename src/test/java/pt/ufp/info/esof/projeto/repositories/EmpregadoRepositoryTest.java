package pt.ufp.info.esof.projeto.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pt.ufp.info.esof.projeto.models.Cargo;
import pt.ufp.info.esof.projeto.models.Empregado;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmpregadoRepositoryTest {

    @Autowired
    EmpregadoRepository empregadoRepository;

    @Test
    public void testeCriacaoEmpregado() {
        Empregado empregado = new Empregado();
        empregado.setCargo(Cargo.DEV_JR);
        empregado.setNome("Empregado 1");

        assertEquals(0, empregadoRepository.count()); //espera-se que tenha 0 antes de guardar

        empregadoRepository.save(empregado); //guarda o empregado

        assertEquals(1, empregadoRepository.count()); //espera-se que tenha 1 depois de guardar
    }
}
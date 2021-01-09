package pt.ufp.info.esof.projeto.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TarefaEfetivaTest {

    @Test
    void registaConclusaoTarefa() {
        TarefaEfetiva tarefaEfetiva = new TarefaEfetiva(); //cria uma nova tarefa
        tarefaEfetiva.registaConclusaoTarefa(); //regista 1 no progresso da tarefa (100 = 100%)

        assertEquals(100, tarefaEfetiva.getProgresso()); //espera-se 1 depois de registar a conclusão da tarefa
    }

    @Test
    void registaTempoExecucao() {
        TarefaEfetiva tarefaEfetiva = new TarefaEfetiva(); //cria uma nova tarefa
        tarefaEfetiva.registaTempoExecucao(8); //regista 8 horas no tempo de execução da tarefa

        assertEquals(8, tarefaEfetiva.getTempoTrabalhado()); //espera-se 8 depois de registar o tempo de execucao da tarefa
    }
}

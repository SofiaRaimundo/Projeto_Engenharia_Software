package pt.ufp.info.esof.projeto.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TarefaPrevistaTest {

    @Test
    void mostraCustoTarefaP() {
        Empregado empregado = new Empregado(); //cria um novo empregado
        empregado.setCargo(Cargo.AN_SR); //cargo do empregado

        TarefaPrevista tarefaPrevista = new TarefaPrevista(); //cria uma nova tarefa
        tarefaPrevista.setEmpregado(empregado); //associa o empregado à tarefa

        assertEquals(80, tarefaPrevista.mostraCustoTarefaP()); //como o cargo do empregado é an_sr, espera-se  que retorne 80
    }

    @Test
    void adicionaTarefaEfetiva() {
        TarefaPrevista tarefaPrevista = new TarefaPrevista(); //cria uma nova tarefa prevista
        tarefaPrevista.setNome("Tarefa teste 1"); //nome da tarefa de teste

        TarefaEfetiva tarefaEfetiva = new TarefaEfetiva(); //cria uma nova tarefa efetiva
        tarefaEfetiva.setNome("Tarefa efetiva teste 1"); //nome da tarefa

        assertEquals(0, tarefaPrevista.getTarefasEfetivas().size()); //antes de adicionar espera-se que naõ tenha nenhuma tarefa efetiva

        tarefaPrevista.adicionaTarefaEfetiva(tarefaEfetiva); //adiciona a tarefa efetiva
        assertEquals(1, tarefaPrevista.getTarefasEfetivas().size()); //espera-se 1 tarefa depois de adicionar
    }

    @Test
    void removeTarefaEfetiva() {
        TarefaPrevista tarefaPrevista = new TarefaPrevista(); //cria uma nova tarefa prevista
        tarefaPrevista.setNome("Tarefa teste 2"); //nome da tarefa de teste

        TarefaEfetiva tarefaEfetiva = new TarefaEfetiva(); //cria uma nova tarefa efetiva
        tarefaEfetiva.setNome("Tarefa efetiva teste 2"); //nome da tarefa

        tarefaPrevista.adicionaTarefaEfetiva(tarefaEfetiva); //adiciona a tarefa
        assertEquals(1, tarefaPrevista.getTarefasEfetivas().size()); //depois de adicionar espera-se 1 tarefa na lista

        tarefaPrevista.removeTarefaEfetiva(tarefaEfetiva); //remove a tarefa
        assertEquals(0, tarefaPrevista.getTarefasEfetivas().size()); //depois de remover espera-se 0 tarefas na lista
    }
}
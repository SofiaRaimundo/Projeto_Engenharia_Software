package pt.ufp.info.esof.projeto.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmpregadoTest {

    @Test
    void salarioValorHora() {
        Empregado empregado1 = new Empregado(); //cria um novo empregado
        empregado1.setCargo(Cargo.DEV_JR); //cargo do empregado
        assertEquals(10, empregado1.salarioValorHora()); //espera-se que o salario seja 10 se for dev_jr

        Empregado empregado2 = new Empregado(); //cria um novo empregado
        empregado2.setCargo(Cargo.DEV_SR); //cargo do empregado
        assertEquals(40, empregado2.salarioValorHora()); //espera-se que o salario seja 40 se for dev_sr

        Empregado empregado3 = new Empregado(); //cria um novo empregado
        empregado3.setCargo(Cargo.AN_JR); //cargo do empregado
        assertEquals(20, empregado3.salarioValorHora()); //espera-se que o salario seja 20 se for an_jr

        Empregado empregado4 = new Empregado(); //cria um novo empregado
        empregado4.setCargo(Cargo.AN_SR); //cargo do empregado
        assertEquals(80, empregado4.salarioValorHora()); //espera-se que o salario seja 80 se for an_sr
    }

    @Test
    void adicionaTarefaP() {
        Empregado empregado = new Empregado(); //cria um novo empregado
        empregado.setCargo(Cargo.DEV_JR); //cargo do empregado
        empregado.setNome("Empregado teste 1"); //nome do empregado de teste

        TarefaPrevista tarefaPrevista = new TarefaPrevista(); //cria uma nova tarefa
        tarefaPrevista.setNome("Tarefa teste 3"); //nome da tarefa de teste

        assertEquals(0, empregado.getTarefasPrevistas().size()); //antes de adicionar a tarefa espera-se que a lista esteja vazia

        empregado.adicionaTarefaPrevista(tarefaPrevista); //adiciona a tarefa

        assertEquals(1, empregado.getTarefasPrevistas().size()); //depois de adicionar espera-se que tenha lá uma tarefa
    }

    @Test
    void removeTarefaP() {
        Empregado empregado = new Empregado(); //cria um novo empregado
        empregado.setCargo(Cargo.DEV_JR); //cargo do empregado
        empregado.setNome("Empregado teste 2"); //nome do empregado de teste

        TarefaPrevista tarefaPrevista = new TarefaPrevista(); //cria uma nova tarefa
        tarefaPrevista.setNome("Tarefa teste 4"); //nome da tarefa

        empregado.adicionaTarefaPrevista(tarefaPrevista); //adiciona a tarefa
        assertEquals(1, empregado.getTarefasPrevistas().size()); //espera-se uma tarefa ao adicionar

        empregado.removeTarefaPrevista(tarefaPrevista); //remove a tarefa
        assertEquals(0, empregado.getTarefasPrevistas().size()); //espera-se 0 tarefas ao remover
    }
}
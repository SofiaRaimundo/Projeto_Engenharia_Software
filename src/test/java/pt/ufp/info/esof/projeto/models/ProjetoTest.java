package pt.ufp.info.esof.projeto.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProjetoTest {

    @Test
    void mostraEstadoProjeto() {
        Projeto projeto = new Projeto(); //cria um novo projeto

        TarefaPrevista tarefaPrevista = new TarefaPrevista(); //cria uma nova tarefa prevista

        TarefaEfetiva tarefaEfetiva = new TarefaEfetiva(); //cria uma nova tarefa efetiva
        tarefaEfetiva.setProgresso(50); //progresso da tarefa

        TarefaEfetiva tarefaEfetiva1 = new TarefaEfetiva(); //cria uma nova tarefa efetiva
        tarefaEfetiva1.setProgresso(40); //progresso da tarefa

        tarefaPrevista.adicionaTarefaEfetiva(tarefaEfetiva); //adiciona a tarefa efetiva à tarefa prevista
        tarefaPrevista.adicionaTarefaEfetiva(tarefaEfetiva1);

        projeto.adicionaTarefaPrevista(tarefaPrevista); //adiciona a tarefa prevista ao projeto

        assertEquals(45, projeto.mostraEstadoProjeto()); //espera-se que o estado do projeto vá a 30 (= 30%)
    }

    @Test
    void mostraCustoProjeto() {
        Projeto projeto = new Projeto(); //cria um novo projeto

        Empregado empregado = new Empregado(); //cria um nova empregado
        empregado.setCargo(Cargo.AN_SR); //cargo do empregado - 80 euros

        Empregado empregado1 = new Empregado();
        empregado1.setCargo(Cargo.DEV_JR); //10 euros

        TarefaPrevista tarefaPrevista = new TarefaPrevista(); //cria uma nova tarefa prevista
        tarefaPrevista.setEmpregado(empregado); //associa um empregado à tarefa
        tarefaPrevista.setTempoPrevisto(4); //4 horas

        TarefaPrevista tarefaPrevista1 = new TarefaPrevista();
        tarefaPrevista1.setEmpregado(empregado1);
        tarefaPrevista1.setTempoPrevisto(5); //5 horas

        projeto.adicionaTarefaPrevista(tarefaPrevista); //adiciona a tarefa ao projeto
        projeto.adicionaTarefaPrevista(tarefaPrevista1);

        assertEquals(370, projeto.mostraCustoProjeto()); //espera-se que o proejto custe 370 euros
    }

    @Test
    void mostraTempoProjeto() {
        Projeto projeto = new Projeto(); //cria um novo projeto

        TarefaPrevista tarefaPrevista = new TarefaPrevista(); //cria uma nova tarefa prevista
        tarefaPrevista.setTempoPrevisto(6); //6 horas

        TarefaPrevista tarefaPrevista1 = new TarefaPrevista();
        tarefaPrevista1.setTempoPrevisto(8); //8 horas

        projeto.adicionaTarefaPrevista(tarefaPrevista); //adiciona a tarefa ao projeto
        projeto.adicionaTarefaPrevista(tarefaPrevista1);

        assertEquals(14, projeto.mostraTempoProjeto()); //espera-se que o projeto demore 14 horas
    }

    @Test
    void adicionaTarefaPrevista() {
        Projeto projeto = new Projeto(); //cria um novo projeto
        projeto.setNome("Projeto teste 3"); //nome do projeto de teste

        TarefaPrevista tarefaPrevista = new TarefaPrevista(); //cria uma nova tarefa
        tarefaPrevista.setProjeto(projeto); //associa a tarefa ao projeto
        tarefaPrevista.setNome("Tarefa prevista teste 1"); //nome da tarefa de teste

        assertEquals(0, projeto.getTarefasPrevistas().size()); //antes de adicionar a tarefa espera-se que a lista de tarefas esteja vazia

        projeto.adicionaTarefaPrevista(tarefaPrevista); //adiciona a tarefa ao projeto

        assertEquals(1, projeto.getTarefasPrevistas().size()); //depois de adicionar espera-se que esteja lá a tarefa
    }

    @Test
    void removeTarefaPrevista() {
        Projeto projeto = new Projeto(); //cria um novo projeto
        projeto.setNome("Projeto teste 4"); //nome do projeto de teste

        TarefaPrevista tarefaPrevista = new TarefaPrevista(); //cria uma nova tarefa
        tarefaPrevista.setProjeto(projeto); //associa a tarefa ao projeto
        tarefaPrevista.setNome("Tarefa prevista teste 2"); //nome da tarefa de teste

        projeto.adicionaTarefaPrevista(tarefaPrevista); //adiciona a tarefa
        assertEquals(1, projeto.getTarefasPrevistas().size()); //espera-se 1 tarefa depois de adicionar

        projeto.removeTarefaPrevista(tarefaPrevista); //remove a tarefa
        assertEquals(0, projeto.getTarefasPrevistas().size()); //espera-se 0 tarefas depois de remover a tarefa
    }
}
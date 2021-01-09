package pt.ufp.info.esof.projeto.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import pt.ufp.info.esof.projeto.repositories.EmpregadoRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmpregadoController.class)
class EmpregadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmpregadoRepository empregadoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllEmpregado()throws Exception {
        //cria 3 novos empregados
        Empregado empregado1 = new Empregado();
        Empregado empregado2 = new Empregado();
        Empregado empregado3 = new Empregado();

        List<Empregado> empregados = Arrays.asList(empregado1, empregado2, empregado3); //coloca os empregados no array de empregados

        String listEmpregadosAsJsonString = new ObjectMapper().writeValueAsString(empregados);

        //quando encontrar todos os empregados deve retorná-los
        when(empregadoRepository.findAll()).thenReturn(empregados);

        String httpResponseAsString = mockMvc.perform(get("/empregado")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);

        //espera-se que liste os empregados como resultado
        assertEquals(listEmpregadosAsJsonString, httpResponseAsString);
    }


    @Test
    void getEmpregadoById() throws Exception {
        Empregado empregado = new Empregado(); //cria um novo empregado
        String empregadoAsJsonString = new ObjectMapper().writeValueAsString(empregado);

        //quando encontrar o empregado com o id, deve retorná-lo
        when(empregadoRepository.findById(1L)).thenReturn(Optional.of(empregado));


        String httpResponseAsString = mockMvc.perform(get("/empregado/1")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString); //deve encontrar o empregado com id 1
        assertEquals(empregadoAsJsonString, httpResponseAsString); //espera-se que ambas tenham encontrado o empregado 1

        //se procurar o empregado com o id 2, não deve encontrar pois ele não existe
        mockMvc.perform(get("/empregado/2")).andExpect(status().isNotFound());
    }


    @Test
    void criarEmpregado() throws Exception {
        Empregado empregado = new Empregado();
        empregado.setId(1L);

        //quando guarda o empregado deve retorná-lo
        when(this.empregadoRepository.save(empregado)).thenReturn(empregado);

        String empregadoAsJsonString = new ObjectMapper().writeValueAsString(empregado);

        mockMvc.perform(post("/empregado").content(empregadoAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        Empregado empregadoExistente = new Empregado();
        empregadoExistente.setId(2L);
        String empregadoExistenteAsJsonString = new ObjectMapper().writeValueAsString(empregadoExistente);
        when(this.empregadoRepository.findById(2L)).thenReturn(Optional.of(empregadoExistente));

        mockMvc.perform(post("/empregado").content(empregadoExistenteAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }


    @Test
    void adicionaTarefaP() throws Exception {
        //cria um empregado
        Empregado empregado = new Empregado();
        empregado.setNome("Empregado teste");
        empregado.setId(1L);

        //cria uma tarefa prevista
        TarefaPrevista tarefaPrevista = new TarefaPrevista();

        //atribui valores a tarefa
        tarefaPrevista.setNome("Tarefa 1");

        String tarefaJson = objectMapper.writeValueAsString(tarefaPrevista);

        //quando encontra o empregado com o id, deve retorná-lo
        when(empregadoRepository.findById(1L)).thenReturn(Optional.of(empregado));

        //ao adicionar ao empregado com id 1 dá certo
        mockMvc.perform(
                patch("/empregado/1")
                        .content(tarefaJson)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        //se voltar a tentar adicionar ao mesmo empregado devia dar um badrequest => perguntar ao professor!!!!
        mockMvc.perform(
                patch("/empregado/1")
                        .content(tarefaJson)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());

        //se tentar adicionar a um empregado que não existe dá também badrequest
        mockMvc.perform(
                patch("/empregado/3")
                        .content(tarefaJson)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }
}


package pt.ufp.info.esof.projeto.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.models.TarefaEfetiva;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import pt.ufp.info.esof.projeto.repositories.ClienteRepository;
import pt.ufp.info.esof.projeto.repositories.TarefaPrevistaRepository;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TarefaPrevistaController.class)
class TarefaPrevistaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TarefaPrevistaRepository tarefaPrevistaRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllTarefaPrevista() throws Exception {
        //cria 3 novas Tarefas Prevista
        TarefaPrevista tarefaPrevista1 = new TarefaPrevista();
        TarefaPrevista tarefaPrevista2 = new TarefaPrevista();
        TarefaPrevista tarefaPrevista3 = new TarefaPrevista();;

        List<TarefaPrevista> tarefaPrevistas = Arrays.asList(tarefaPrevista1, tarefaPrevista2, tarefaPrevista3); //coloca as TE no array de TP

        String listTarefaPrevistasAsJsonString= new ObjectMapper().writeValueAsString(tarefaPrevistas);

        //quando encontrar todas as TP deve retorná-las
        when(tarefaPrevistaRepository.findAll()).thenReturn(tarefaPrevistas);

        String httpResponseAsString = mockMvc.perform(get("/tarefaPrevista")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);

        //espera-se que liste as TP como resultado
        assertEquals(listTarefaPrevistasAsJsonString, httpResponseAsString);
    }


    @Test
    void getTarefaPrevistaById() throws Exception {
        TarefaPrevista tarefaPrevista = new TarefaPrevista(); //cria uma nova TP
        String tarefaPrevistasAsJsonString = new ObjectMapper().writeValueAsString(tarefaPrevista);

        //quando encontrar a TP com o id, deve retorná-la
        when(tarefaPrevistaRepository.findById(1L)).thenReturn(Optional.of(tarefaPrevista));


        String httpResponseAsString = mockMvc.perform(get("/tarefaPrevista/1")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString); //deve encontrar a TP com id 1
        assertEquals(tarefaPrevistasAsJsonString, httpResponseAsString); //espera-se que ambas tenham encontrado a TP 1

        //se procurar a TP com o id 2, não deve encontrar pois ela não existe
        mockMvc.perform(get("/tarefaPrevista/2")).andExpect(status().isNotFound());
    }


    @Test
    void criarTarefaPrevista() throws Exception {
        TarefaPrevista tarefaPrevista = new TarefaPrevista();
        tarefaPrevista.setId(1L);
        String tarefaPrevistasExistenteAsJsonString = new ObjectMapper().writeValueAsString(tarefaPrevista);

        //quando guarda a TP deve retorná-le
        when(this.tarefaPrevistaRepository.save(tarefaPrevista)).thenReturn(tarefaPrevista);

        String tarefaPrevistaAsJsonString = new ObjectMapper().writeValueAsString(tarefaPrevista);

        mockMvc.perform(post("/tarefaPrevista").content(tarefaPrevistaAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        TarefaPrevista tarefaPrevistaExistente = new TarefaPrevista();
        tarefaPrevistaExistente.setId(2L);
        when(this.tarefaPrevistaRepository.findById(2L)).thenReturn(Optional.of(tarefaPrevistaExistente));

        mockMvc.perform(post("/tarefaPrevista").content(tarefaPrevistasExistenteAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    void adicionaProjeto() throws Exception {
        //cria uma TP
        TarefaPrevista tarefaPrevista = new TarefaPrevista();
        tarefaPrevista.setNome("TarefaPrevista teste");
        tarefaPrevista.setId(1L);

        //cria um projeto
        Projeto projeto = new Projeto();

        //atribui valores ao projeto
        projeto.setNome("Projeto teste");

        String projetoJson = objectMapper.writeValueAsString(projeto);

        //quando encontra a TP com o id, deve retorná-la
        when(tarefaPrevistaRepository.findById(1L)).thenReturn(Optional.of(tarefaPrevista));

        //ao adicionar a TP com id 1 dá certo
        mockMvc.perform(
                patch("/tarefaPrevista/1")
                        .content(projetoJson)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        //se voltar a tentar adicionar a mesma TP devia dar um badrequest => perguntar ao professor!!!!
        mockMvc.perform(
                patch("/tarefaPrevista/1")
                        .content(projetoJson)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());

        //se tentar adicionar a uma TP que não existe dá também badrequest
        mockMvc.perform(
                patch("/tarefaPrevista/3")
                        .content(projetoJson)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }
}
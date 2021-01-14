package pt.ufp.info.esof.projeto.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pt.ufp.info.esof.projeto.dtos.ProjetoCreateDTO;
import pt.ufp.info.esof.projeto.dtos.TarefaEfetivaCreateDTO;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import pt.ufp.info.esof.projeto.services.ClienteService;
import pt.ufp.info.esof.projeto.services.TarefaPrevistaService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TarefaPrevistaController.class)
class TarefaPrevistaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TarefaPrevistaService tarefaPrevistaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllTarefaPrevista() throws Exception {
        TarefaPrevista tarefaPrevista1 = new TarefaPrevista();
        TarefaPrevista tarefaPrevista2 = new TarefaPrevista();
        TarefaPrevista tarefaPrevista3 = new TarefaPrevista();

        List<TarefaPrevista> tarefas = Arrays.asList(tarefaPrevista1, tarefaPrevista2, tarefaPrevista3);

        when(tarefaPrevistaService.findAll()).thenReturn(tarefas);

        String httpResponseAsString = mockMvc.perform(get("/tarefaPrevista")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);
    }

    @Test
    void getTarefaPrevistaById() throws Exception {
        TarefaPrevista tarefaPrevista = new TarefaPrevista();

        String tarefaAsJsonString = new ObjectMapper().writeValueAsString(tarefaPrevista);

        when(tarefaPrevistaService.findById(1L)).thenReturn(Optional.of(tarefaPrevista));

        String httpResponseAsString = mockMvc.perform(get("/tarefaPrevista/1")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);

        mockMvc.perform(get("/tarefaPrevista/2")).andExpect(status().isNotFound());
    }

    @Test
    void criarTarefaPrevista() {
        TarefaPrevista tarefaPrevista = new TarefaPrevista();
        tarefaPrevista.setNome("Tarefa teste");

        when(this.tarefaPrevistaService.criarTarefaPrevista(tarefaPrevista)).thenReturn(Optional.of(tarefaPrevista));

        assertTrue(tarefaPrevistaService.criarTarefaPrevista(tarefaPrevista).isPresent());
    }

    @Test
    void adicionaTarefaEfetiva() throws Exception {
        TarefaPrevista tarefaPrevista = new TarefaPrevista();
        tarefaPrevista.setNome("Tarefa teste");

        TarefaEfetivaCreateDTO tarefaEfetivaDTO = new TarefaEfetivaCreateDTO();
        tarefaEfetivaDTO.setNome("Tarefa efetiva 1");

        String tarefaEfetivaJson = objectMapper.writeValueAsString(tarefaEfetivaDTO);

        when(tarefaPrevistaService.adicionaTarefaEfetiva(1L, tarefaEfetivaDTO.converter())).thenReturn(Optional.of(tarefaPrevista));

        mockMvc.perform(
                patch("/tarefaPrevista/1")
                        .content(tarefaEfetivaJson)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        mockMvc.perform(
                patch("/tarefaPrevista/2")
                        .content(tarefaEfetivaJson)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }
}
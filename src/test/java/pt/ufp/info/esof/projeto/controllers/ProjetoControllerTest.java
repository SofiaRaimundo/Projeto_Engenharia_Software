package pt.ufp.info.esof.projeto.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pt.ufp.info.esof.projeto.dtos.TarefaPrevistaCreateDTO;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.services.ProjetoService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProjetoController.class)
class ProjetoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjetoService projetoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllProjeto() throws Exception {
        Projeto projeto1 = new Projeto();
        Projeto projeto2 = new Projeto();
        Projeto projeto3 = new Projeto();

        List<Projeto> projetos = Arrays.asList(projeto1, projeto2, projeto3);

        when(projetoService.findAll()).thenReturn(projetos);

        String httpResponseAsString = mockMvc.perform(get("/projeto")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);
    }

    @Test
    void getProjetoById() throws Exception {
        Projeto projeto = new Projeto();

        String projetoAsJsonString = new ObjectMapper().writeValueAsString(projeto);

        when(projetoService.findById(1L)).thenReturn(Optional.of(projeto));

        String httpResponseAsString = mockMvc.perform(get("/projeto/1")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);

        mockMvc.perform(get("/projeto/2")).andExpect(status().isNotFound());
    }

    @Test
    void criarProjeto() {
        Projeto projeto = new Projeto();
        projeto.setNome("Projeto teste");

        when(this.projetoService.criarProjeto(projeto)).thenReturn(Optional.of(projeto));

        assertTrue(projetoService.criarProjeto(projeto).isPresent());
    }

    @Test
    void adicionaTarefaPrevista() throws Exception {
        Projeto projeto = new Projeto();
        projeto.setNome("Projeto teste");

        TarefaPrevistaCreateDTO tarefaPrevistaDTO = new TarefaPrevistaCreateDTO();
        tarefaPrevistaDTO.setNome("Tarefa teste");

        String tarefaPrevistaJson = objectMapper.writeValueAsString(tarefaPrevistaDTO);

        when(projetoService.adicionaTarefaPrevista(1L, tarefaPrevistaDTO.converter())).thenReturn(Optional.of(projeto));

        mockMvc.perform(
                patch("/projeto/1")
                        .content(tarefaPrevistaJson)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        mockMvc.perform(
                patch("/projeto/2")
                        .content(tarefaPrevistaJson)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }
}
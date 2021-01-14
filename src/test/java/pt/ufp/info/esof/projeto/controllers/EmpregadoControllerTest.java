package pt.ufp.info.esof.projeto.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pt.ufp.info.esof.projeto.dtos.TarefaPrevistaCreateDTO;
import pt.ufp.info.esof.projeto.models.Cargo;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.models.Empregado;
import pt.ufp.info.esof.projeto.services.EmpregadoService;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(EmpregadoController.class)
class EmpregadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmpregadoService empregadoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllEmpregado() throws Exception {
        Empregado empregado1 = new Empregado();
        Empregado empregado2 = new Empregado();
        Empregado empregado3 = new Empregado();

        List<Empregado> empregados = Arrays.asList(empregado1, empregado2, empregado3);

        when(empregadoService.findAll()).thenReturn(empregados);

        String httpResponseAsString = mockMvc.perform(get("/empregado")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);
    }

    @Test
    void getEmpregadoById() throws Exception {
        Empregado empregado = new Empregado();

        String empregadoAsJsonString = new ObjectMapper().writeValueAsString(empregado);

        when(empregadoService.findById(1L)).thenReturn(Optional.of(empregado));

        String httpResponseAsString = mockMvc.perform(get("/empregado/1")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);

        mockMvc.perform(get("/empregado/2")).andExpect(status().isNotFound()); //o empregado com id 2 não existe
    }

    @Test
    void criarEmpregado() throws Exception {
        Empregado empregado = new Empregado();
        empregado.setNome("Empregado teste");

        when(this.empregadoService.criarEmpregado(empregado)).thenReturn(Optional.of(empregado));

        assertTrue(empregadoService.criarEmpregado(empregado).isPresent());
    }

    @Test
    void adicionaTarefaPrevista() throws Exception {
        Empregado empregado = new Empregado();
        empregado.setCargo(Cargo.DEV_SR);

        TarefaPrevistaCreateDTO tarefaPrevista = new TarefaPrevistaCreateDTO();
        tarefaPrevista.setNome("Tarefa 1");
        tarefaPrevista.setTempoPrevisto(4);

        String tarefaPrevistaJson = objectMapper.writeValueAsString(tarefaPrevista);

        when(empregadoService.adicionaTarefaPrevista(1L, tarefaPrevista.converter())).thenReturn(Optional.of(empregado));

        mockMvc.perform(
                patch("/empregado/1")
                        .content(tarefaPrevistaJson)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        mockMvc.perform(
                patch("/empregado/2")
                        .content(tarefaPrevistaJson)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }
}
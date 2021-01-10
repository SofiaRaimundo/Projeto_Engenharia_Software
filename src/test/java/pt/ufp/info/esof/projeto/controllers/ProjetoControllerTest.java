package pt.ufp.info.esof.projeto.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pt.ufp.info.esof.projeto.models.Projeto;
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import pt.ufp.info.esof.projeto.repositories.ProjetoRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProjetoController.class)
class ProjetoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjetoRepository projetoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllProjeto() throws Exception {
        //cria 3 novos projetos
        Projeto projeto1 = new Projeto();
        Projeto projeto2 = new Projeto();
        Projeto projeto3 = new Projeto();

        List<Projeto> projetos = Arrays.asList(projeto1, projeto2, projeto3); //coloca os projetos no array de projetos

        String listProjetosAsJsonString = new ObjectMapper().writeValueAsString(projetos);

        //quando encontrar todos os projetos deve retorná-los
        when(projetoRepository.findAll()).thenReturn(projetos);

        String httpResponseAsString = mockMvc.perform(get("/projeto")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);

        //espera-se que liste os projetos como resultado
        assertEquals(listProjetosAsJsonString, httpResponseAsString);
    }

    @Test
    void getProjetoById() throws Exception {
        Projeto projeto = new Projeto(); //cria um novo projeto
        String projetoAsJsonString = new ObjectMapper().writeValueAsString(projeto);

        //quando encontrar o projeto com o id, deve retorná-lo
        when(projetoRepository.findById(1L)).thenReturn(Optional.of(projeto));

        String httpResponseAsString = mockMvc.perform(get("/projeto/1")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString); //deve encontrar o projeto com id 1
        assertEquals(projetoAsJsonString, httpResponseAsString); //espera-se que ambas tenham encontrado o projeto 1

        //se procurar o projeto com o id 2, não deve encontrar pois ele não existe
        mockMvc.perform(get("/projeto/2")).andExpect(status().isNotFound());
    }

    @Test
    void criarProjeto() throws Exception {
        Projeto projeto = new Projeto();
        projeto.setId(1L);

        //quando guarda o projeto deve retorná-lo
        when(this.projetoRepository.save(projeto)).thenReturn(projeto);

        String projetoAsJsonString = new ObjectMapper().writeValueAsString(projeto);

        mockMvc.perform(post("/projeto").content(projetoAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        Projeto projetoExistente = new Projeto();
        projetoExistente.setId(2L);
        String projetoExistenteAsJsonString = new ObjectMapper().writeValueAsString(projetoExistente);
        when(this.projetoRepository.findById(2L)).thenReturn(Optional.of(projetoExistente));

        mockMvc.perform(post("/projeto").content(projetoExistenteAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    void adicionaTarefaPrevista() throws Exception {
        //cria um projeto
        Projeto projeto = new Projeto();
        projeto.setNome("Projeto teste");
        projeto.setId(1L);

        //cria uma tarefa prevista
        TarefaPrevista tarefaPrevista = new TarefaPrevista();
        tarefaPrevista.setNome("Tarefa 1");
        tarefaPrevista.setId(1L);

        String tarefaJson = objectMapper.writeValueAsString(tarefaPrevista);

        //quando encontra o projeto com o id, deve retorná-lo
        when(projetoRepository.findById(1L)).thenReturn(Optional.of(projeto));

        //ao adicionar ao projeto com id 1 dá certo
        mockMvc.perform(
                patch("/projeto/1")
                        .content(tarefaJson)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        //se voltar a tentar adicionar ao mesmo projeto devia dar um badrequest => perguntar ao professor!!!!
        mockMvc.perform(
                patch("/projeto/1")
                        .content(tarefaJson)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());

        //se tentar adicionar a um cliente que não existe dá também badrequest
        mockMvc.perform(
                patch("/projeto/3")
                        .content(tarefaJson)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }
}
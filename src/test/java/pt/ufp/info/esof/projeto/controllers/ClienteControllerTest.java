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
import pt.ufp.info.esof.projeto.models.TarefaPrevista;
import pt.ufp.info.esof.projeto.repositories.ClienteRepository;
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

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteRepository clienteRepository;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void getAllCliente() throws Exception {
        //cria 3 novos clientes
        Cliente cliente1 = new Cliente();
        Cliente cliente2 = new Cliente();
        Cliente cliente3 = new Cliente();

        List<Cliente> clientes = Arrays.asList(cliente1, cliente2, cliente3); //coloca os clientes no array de clientes

        String listClientesAsJsonString = new ObjectMapper().writeValueAsString(clientes);

        //quando encontrar todos os clientes deve retorná-los
        when(clienteRepository.findAll()).thenReturn(clientes);

        String httpResponseAsString = mockMvc.perform(get("/cliente")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);

        //espera-se que liste os clientes como resultado
        assertEquals(listClientesAsJsonString, httpResponseAsString);
    }

    @Test
    void getClienteById() throws Exception {
        Cliente cliente = new Cliente(); //cria um novo cliente
        String clienteAsJsonString = new ObjectMapper().writeValueAsString(cliente);

        //quando encontrar o cliente com o id, deve retorná-lo
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));


        String httpResponseAsString = mockMvc.perform(get("/cliente/1")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString); //deve encontrar o cliente com id 1
        assertEquals(clienteAsJsonString, httpResponseAsString); //espera-se que ambas tenham encontrado o cliente 1

        //se procurar o cliente com o id 2, não deve encontrar pois ele não existe
        mockMvc.perform(get("/cliente/2")).andExpect(status().isNotFound());
    }


    @Test
    void criarCliente() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setId(1L);

        //quando guarda o cliente deve retorná-lo
        when(this.clienteRepository.save(cliente)).thenReturn(cliente);

        String clienteAsJsonString = new ObjectMapper().writeValueAsString(cliente);

        mockMvc.perform(post("/cliente").content(clienteAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        Cliente clienteExistente = new Cliente();
        clienteExistente.setId(2L);
        String clienteExistenteAsJsonString = new ObjectMapper().writeValueAsString(clienteExistente);
        when(this.clienteRepository.findById(2L)).thenReturn(Optional.of(clienteExistente));

        mockMvc.perform(post("/cliente").content(clienteExistenteAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    void adicionaProjeto() throws Exception {
        //cria um cliente
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente teste");
        cliente.setId(1L);

        //cria um projeto
        Projeto projeto = new Projeto();

        //atribui valores ao projeto
        projeto.setNome("Projeto teste");

        String projetoJson = objectMapper.writeValueAsString(projeto);

        //quando encontra o cliente com o id, deve retorná-lo
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        //ao adicionar ao cliente com id 1 dá certo
        mockMvc.perform(
                patch("/cliente/1")
                        .content(projetoJson)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        //se voltar a tentar adicionar ao mesmo cliente devia dar um badrequest => perguntar ao professor!!!!
        mockMvc.perform(
                patch("/cliente/1")
                        .content(projetoJson)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());

        //se tentar adicionar a um cliente que não existe dá também badrequest
        mockMvc.perform(
                patch("/cliente/3")
                        .content(projetoJson)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }
}
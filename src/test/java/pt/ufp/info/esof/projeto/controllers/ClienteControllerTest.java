package pt.ufp.info.esof.projeto.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pt.ufp.info.esof.projeto.dtos.ProjetoCreateDTO;
import pt.ufp.info.esof.projeto.models.Cliente;
import pt.ufp.info.esof.projeto.services.ClienteService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllCliente() throws Exception {
        Cliente cliente1 = new Cliente();
        Cliente cliente2 = new Cliente();
        Cliente cliente3 = new Cliente();

        List<Cliente> clientes = Arrays.asList(cliente1, cliente2, cliente3);

        when(clienteService.findAll()).thenReturn(clientes);

        String httpResponseAsString = mockMvc.perform(get("/cliente")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);
    }

    @Test
    void getClienteById() throws Exception {
        Cliente cliente = new Cliente();

        String clienteAsJsonString = new ObjectMapper().writeValueAsString(cliente);

        when(clienteService.findById(1L)).thenReturn(Optional.of(cliente));

        String httpResponseAsString = mockMvc.perform(get("/cliente/1")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);

        mockMvc.perform(get("/cliente/2")).andExpect(status().isNotFound()); //o cliente com id 2 não existe
    }

    @Test
    void criarCliente() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente teste");

        when(this.clienteService.criarCliente(cliente)).thenReturn(Optional.of(cliente));

        assertTrue(clienteService.criarCliente(cliente).isPresent());
    }

    @Test
    public void adicionaProjeto() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente 1");

        ProjetoCreateDTO projetoDTO = new ProjetoCreateDTO();
        projetoDTO.setNome("Projeto 1");

        String projetoJson = objectMapper.writeValueAsString(projetoDTO);

        when(clienteService.adicionaProjeto(1L, projetoDTO.converter())).thenReturn(Optional.of(cliente));

        mockMvc.perform(
                patch("/cliente/1")
                        .content(projetoJson)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        mockMvc.perform(
                patch("/cliente/2")
                        .content(projetoJson)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }
}
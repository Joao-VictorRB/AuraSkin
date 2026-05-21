package com.AuraSkin.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.AuraSkin.entity.Client;
import com.AuraSkin.service.ClientService;

@WebMvcTest(ClientController.class)
@SuppressWarnings("null") // 👈 Remove a necessidade de usar Objects.requireNonNull nos métodos MockMvc
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClientService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnAllClients() throws Exception {
        Client client = new Client();
        client.setIdClient(1L); // 👈 Setamos um ID para os testes de retorno
        client.setName("João");
        client.setEmail("joao@gmail.com");
        client.setPhone("11999999999");

        when(service.getAllClients()).thenReturn(List.of(client));

        mockMvc.perform(get("/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("João"));
    }

    @Test
    void shouldReturnClientById() throws Exception {
        Client client = new Client();
        client.setIdClient(1L); // 👈 Evita problemas de nulo no retorno por ID
        client.setName("João");

        when(service.getClientById(1L)).thenReturn(client);

        mockMvc.perform(get("/clients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("João"));
    }

    @Test
    void shouldCreateClient() throws Exception {
        Client client = new Client();
        // Não definimos ID aqui porque simulamos um cliente novo enviado no corpo
        client.setName("Carlos");
        client.setEmail("carlos@gmail.com");
        client.setPhone("11999999999");

        when(service.saveClient(org.mockito.ArgumentMatchers.any(Client.class)))
                .thenReturn(client);

        // Código limpo sem Objects.requireNonNull graças ao SuppressWarnings
        mockMvc.perform(post("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isOk()) 
                .andExpect(jsonPath("$.name").value("Carlos"));
    }

    @Test
    void shouldUpdateClient() throws Exception {
        Client client = new Client();
        client.setName("Atualizado");
        client.setEmail("novo@gmail.com");

        when(service.updateClient(
                org.mockito.ArgumentMatchers.eq(1L),
                org.mockito.ArgumentMatchers.any(Client.class)))
                .thenReturn(client);

        mockMvc.perform(put("/clients/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Atualizado"));
    }

    @Test
    void shouldDeleteClient() throws Exception {
        doNothing().when(service).deleteClient(1L);

        mockMvc.perform(delete("/clients/1"))
                .andExpect(status().isOk());
    }
}
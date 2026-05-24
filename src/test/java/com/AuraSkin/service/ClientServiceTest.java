package com.AuraSkin.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.AuraSkin.entity.Client;
import com.AuraSkin.repository.ClientRepository;

public class ClientServiceTest {

    @Mock
    private ClientRepository repository;

    @InjectMocks
    private ClientService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveClient() {

        Client client = new Client();

        client.setName("João");
        client.setEmail("joao@gmail.com");
        client.setPhone("11999999999");

        when(repository.save(client))
                .thenReturn(client);

        Client result = service.saveClient(client);

        assertNotNull(result);

        assertEquals("João",
                result.getName());
    }

    @Test
    void shouldReturnAllClients() {

        Client client = new Client();

        client.setName("João");

        when(repository.findAll())
                .thenReturn(List.of(client));

        List<Client> result =
                service.getAllClients();

        assertEquals(1,
                result.size());

        assertEquals("João",
                result.get(0).getName());
    }

    @Test
    void shouldReturnClientById() {

        Client client = new Client();

        client.setName("João");

        when(repository.findById(1L))
                .thenReturn(Optional.of(client));

        Client result =
                service.getClientById(1L);

        assertNotNull(result);

        assertEquals("João",
                result.getName());
    }

    @Test
    void shouldUpdateClient() {

        Client existingClient = new Client();

        existingClient.setName("João");

        Client updatedClient = new Client();

        updatedClient.setName("Carlos");
        updatedClient.setEmail("carlos@gmail.com");
        updatedClient.setPhone("11999999999");

        when(repository.findById(1L))
                .thenReturn(Optional.of(existingClient));

        when(repository.save(existingClient))
                .thenReturn(existingClient);

        Client result =
                service.updateClient(1L, updatedClient);

        assertEquals("Carlos",
                result.getName());

        assertEquals("carlos@gmail.com",
                result.getEmail());
    }

    @Test
    void shouldDeleteClient() {

        doNothing().when(repository)
                .deleteById(1L);

        service.deleteClient(1L);

        verify(repository)
                .deleteById(1L);
    }

    @Test
    void shouldThrowExceptionWhenClientNotFound() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> service.getClientById(1L));
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonexistentClient() {

        Client client = new Client();

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,() -> service.updateClient(1L, client));
    }
}
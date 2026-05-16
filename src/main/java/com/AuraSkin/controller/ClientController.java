package com.AuraSkin.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.AuraSkin.dto.ClientDTO;
import com.AuraSkin.entity.Client;
import com.AuraSkin.service.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping
    public List<Client> getAllClients() {
        return service.getAllClients();
    }

    @PostMapping
    public Client createClient(@RequestBody @Valid ClientDTO dto) {
        
        Client client = new Client();
        client.setName(dto.getName());
        client.setEmail(dto.getEmail());
        client.setPhone(dto.getPhone());

        return service.saveClient(client);
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id) {

        return service.getClientById(id);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id,
        @RequestBody Client client) {

        return service.updateClient(id, client);
    }

    @DeleteMapping("/{id}")
    public String deleteClient(@PathVariable Long id) {

        service.deleteClient(id);
        return "Client with ID " + id + " has been deleted.";
    }
}

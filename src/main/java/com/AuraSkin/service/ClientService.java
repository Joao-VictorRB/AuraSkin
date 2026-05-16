package com.AuraSkin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.AuraSkin.entity.Client;
import com.AuraSkin.repository.ClientRepository;

@Service
public class ClientService {

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public Client saveClient(Client client) {
        return repository.save(client);
    }

    public List<Client> getAllClients() { 
        return repository.findAll();
    }

    public Client getClientById(Long id) {

        return repository.findById(id).orElse(null);
    }

    public Client updateClient(Long id, Client client) {

        Client existingClient = repository.findById(id).orElse(null);

        if (existingClient != null) {

            if(client.getName() != null){
                existingClient.setName(client.getName());
            }
            if(client.getEmail() != null){
                existingClient.setEmail(client.getEmail());
            }
            if(client.getPhone() != null){
                existingClient.setPhone(client.getPhone());
            }

            return repository.save(existingClient);
        }

        return null;
    }

    public void deleteClient(Long id) {

        repository.deleteById(id);
    }
}
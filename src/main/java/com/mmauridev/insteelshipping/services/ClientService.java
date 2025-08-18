package com.mmauridev.insteelshipping.services;

import com.mmauridev.insteelshipping.models.Client;
import com.mmauridev.insteelshipping.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long clientId) {
        return clientRepository.findById(clientId);
    }

    public Client updateClient(Client client) {
        return clientRepository.save(client); // save() works for both insert and update
    }

    public void deleteClient(Long clientId) {
        clientRepository.deleteById(clientId);
    }
}

package com.mmauridev.insteelshipping.controllers;

import com.mmauridev.insteelshipping.models.Client;
import com.mmauridev.insteelshipping.services.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SuppressWarnings("deprecation")
class ClientControllerTests {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    private Client client1;
    private Client client2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        client1 = new Client();
        client1.setName("John Doe");
        client1.setEmail("john@example.com");

        client2 = new Client();
        client2.setName("Jane Smith");
        client2.setEmail("jane@example.com");
    }

    @Test
    void getAllClients_returnsAllClients() {
        when(clientService.getAllClients()).thenReturn(Arrays.asList(client1, client2));

        List<Client> result = clientController.getAllClients();

        assertThat(result).hasSize(2).contains(client1, client2);
        verify(clientService, times(1)).getAllClients();
    }

    @Test
    void getClientById_existingId_returnsClient() {
        when(clientService.getClientById(1L)).thenReturn(Optional.of(client1));

        ResponseEntity<Client> response = clientController.getClientById(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(client1);
    }

    @Test
    void getClientById_nonExistingId_returnsNotFound() {
        when(clientService.getClientById(99L)).thenReturn(Optional.empty());

        ResponseEntity<Client> response = clientController.getClientById(99L);

        assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }

    @Test
    void createClient_savesClient() {
        when(clientService.saveClient(client1)).thenReturn(client1);

        Client result = clientController.createClient(client1);

        assertThat(result).isEqualTo(client1);
        verify(clientService, times(1)).saveClient(client1);
    }

    @Test
    void updateClient_existingId_updatesAndReturnsClient() {
        Client updatedClient = new Client();
        updatedClient.setName("John Updated");
        updatedClient.setEmail("john.updated@example.com");

        when(clientService.getClientById(1L)).thenReturn(Optional.of(client1));
        when(clientService.saveClient(any(Client.class))).thenAnswer(i -> i.getArgument(0));

        ResponseEntity<Client> response = clientController.updateClient(1L, updatedClient);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getName()).isEqualTo("John Updated");
        assertThat(response.getBody().getEmail()).isEqualTo("john.updated@example.com");
        verify(clientService, times(1)).saveClient(client1);
    }

    @Test
    void updateClient_nonExistingId_returnsNotFound() {
        when(clientService.getClientById(99L)).thenReturn(Optional.empty());

        ResponseEntity<Client> response = clientController.updateClient(99L, client1);

        assertThat(response.getStatusCodeValue()).isEqualTo(404);
        verify(clientService, never()).saveClient(any());
    }

    @Test
    void deleteClient_existingId_returnsNoContent() {
        when(clientService.getClientById(1L)).thenReturn(Optional.of(client1));

        ResponseEntity<Void> response = clientController.deleteClient(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(204);
        verify(clientService, times(1)).deleteClient(1L);
    }

    @Test
    void deleteClient_nonExistingId_returnsNotFound() {
        when(clientService.getClientById(99L)).thenReturn(Optional.empty());

        ResponseEntity<Void> response = clientController.deleteClient(99L);

        assertThat(response.getStatusCodeValue()).isEqualTo(404);
        verify(clientService, never()).deleteClient(anyLong());
    }
}


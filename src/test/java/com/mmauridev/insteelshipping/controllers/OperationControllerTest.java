package com.mmauridev.insteelshipping.controllers;

import com.mmauridev.insteelshipping.models.Client;
import com.mmauridev.insteelshipping.models.Operation;
import com.mmauridev.insteelshipping.services.OperationService;
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

class OperationControllerTests {

    @Mock
    private OperationService operationService;

    @InjectMocks
    private OperationController operationController;

    private Operation operation1;
    private Operation operation2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Client client1 = new Client();
        client1.setName("John Doe");

        Client client2 = new Client();
        client2.setName("Jane Smith");

        operation1 = new Operation();
        operation1.setDescription("Operation 1");
        operation1.setClient(client1);

        operation2 = new Operation();
        operation2.setDescription("Operation 2");
        operation2.setClient(client2);
    }

    @Test
    void getAllOperations_returnsAllOperations() {
        when(operationService.getAllOperations()).thenReturn(Arrays.asList(operation1, operation2));

        List<Operation> result = operationController.getAllOperations();

        assertThat(result).hasSize(2).contains(operation1, operation2);
        verify(operationService, times(1)).getAllOperations();
    }

    @Test
    void getOperationById_existingId_returnsOperation() {
        when(operationService.getOperationById(1L)).thenReturn(Optional.of(operation1));

        ResponseEntity<Operation> response = operationController.getOperationById(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(operation1);
    }

    @Test
    void getOperationById_nonExistingId_returnsNotFound() {
        when(operationService.getOperationById(99L)).thenReturn(Optional.empty());

        ResponseEntity<Operation> response = operationController.getOperationById(99L);

        assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }

    @Test
    void createOperation_savesOperation() {
        when(operationService.saveOperation(operation1)).thenReturn(operation1);

        Operation result = operationController.createOperation(operation1);

        assertThat(result).isEqualTo(operation1);
        verify(operationService, times(1)).saveOperation(operation1);
    }

    @Test
    void updateOperation_existingId_updatesAndReturnsOperation() {
        Operation updatedOperation = new Operation();
        updatedOperation.setDescription("Updated Operation");

        when(operationService.getOperationById(1L)).thenReturn(Optional.of(operation1));
        when(operationService.saveOperation(any(Operation.class))).thenAnswer(i -> i.getArgument(0));

        ResponseEntity<Operation> response = operationController.updateOperation(1L, updatedOperation);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getDescription()).isEqualTo("Updated Operation");
        verify(operationService, times(1)).saveOperation(operation1);
    }

    @Test
    void updateOperation_nonExistingId_returnsNotFound() {
        when(operationService.getOperationById(99L)).thenReturn(Optional.empty());

        ResponseEntity<Operation> response = operationController.updateOperation(99L, operation1);

        assertThat(response.getStatusCodeValue()).isEqualTo(404);
        verify(operationService, never()).saveOperation(any());
    }

    @Test
    void deleteOperation_existingId_returnsNoContent() {
        when(operationService.getOperationById(1L)).thenReturn(Optional.of(operation1));

        ResponseEntity<Void> response = operationController.deleteOperation(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(204);
        verify(operationService, times(1)).deleteOperation(1L);
    }

    @Test
    void deleteOperation_nonExistingId_returnsNotFound() {
        when(operationService.getOperationById(99L)).thenReturn(Optional.empty());

        ResponseEntity<Void> response = operationController.deleteOperation(99L);

        assertThat(response.getStatusCodeValue()).isEqualTo(404);
        verify(operationService, never()).deleteOperation(anyLong());
    }
}

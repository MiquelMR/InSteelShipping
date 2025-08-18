package com.mmauridev.insteelshipping.services;

import com.mmauridev.insteelshipping.models.Operation;
import com.mmauridev.insteelshipping.repositories.OperationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperationService {

    private final OperationRepository operationRepository;

    public OperationService(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }

    public Optional<Operation> getOperationById(Long operationId) {
        return operationRepository.findById(operationId);
    }

    public Operation saveOperation(Operation operation) {
        return operationRepository.save(operation);
    }

    public Operation updateOperation(Operation operation) {
        if (operation.getOperationId() == null) {
            throw new IllegalArgumentException("Operation ID cannot be null for update");
        }
        return operationRepository.save(operation);
    }

    public void deleteOperation(Long operationId) {
        operationRepository.deleteById(operationId);
    }
}

package com.mmauridev.insteelshipping.controllers;

import com.mmauridev.insteelshipping.models.Operation;
import com.mmauridev.insteelshipping.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operations")
public class OperationController {

    private final OperationService operationService;

    @Autowired
    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping
    public List<Operation> getAllOperations() {
        return operationService.getAllOperations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Operation> getOperationById(@PathVariable Long id) {
        return operationService.getOperationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Operation createOperation(@RequestBody Operation operation) {
        return operationService.saveOperation(operation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Operation> updateOperation(@PathVariable Long id, @RequestBody Operation updatedOperation) {
        return operationService.getOperationById(id)
                .map(existingOperation -> {
                    existingOperation.setClient(updatedOperation.getClient());
                    existingOperation.setDescription(updatedOperation.getDescription());
                    Operation savedOperation = operationService.saveOperation(existingOperation);
                    return ResponseEntity.ok(savedOperation);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOperation(@PathVariable Long id) {
        if (operationService.getOperationById(id).isPresent()) {
            operationService.deleteOperation(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

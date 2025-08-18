package com.mmauridev.insteelshipping.controllers;

import com.mmauridev.insteelshipping.models.Freight;
import com.mmauridev.insteelshipping.services.FreightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/freights")
public class FreightController {

    private final FreightService freightService;

    @Autowired
    public FreightController(FreightService freightService) {
        this.freightService = freightService;
    }

    @GetMapping
    public List<Freight> getAllFreights() {
        return freightService.getAllFreights();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Freight> getFreightById(@PathVariable Long id) {
        return freightService.getFreightById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Freight createFreight(@RequestBody Freight freight) {
        return freightService.saveFreight(freight);
    }
}
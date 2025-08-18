package com.mmauridev.insteelshipping.services;

import com.mmauridev.insteelshipping.models.Freight;
import com.mmauridev.insteelshipping.repositories.FreightRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FreightService {

    private final FreightRepository freightRepository;

    public FreightService(FreightRepository freightRepository) {
        this.freightRepository = freightRepository;
    }

    public List<Freight> getAllFreights() {
        return freightRepository.findAll();
    }

    public Optional<Freight> getFreightById(Long freightId) {
        return freightRepository.findById(freightId);
    }

    public Freight saveFreight(Freight freight) {
        return freightRepository.save(freight);
    }

    public Freight updateFreight(Freight freight) {
        if (freight.getFreightId() == null) {
            throw new IllegalArgumentException("Freight ID cannot be null for update");
        }
        return freightRepository.save(freight);
    }

    public void deleteFreight(Long freightId) {
        freightRepository.deleteById(freightId);
    }
}

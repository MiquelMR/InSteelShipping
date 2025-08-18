package com.mmauridev.insteelshipping.services;

import com.mmauridev.insteelshipping.models.Container;
import com.mmauridev.insteelshipping.repositories.ContainerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContainerService {

    private final ContainerRepository containerRepository;

    public ContainerService(ContainerRepository containerRepository) {
        this.containerRepository = containerRepository;
    }

    public List<Container> getAllContainers() {
        return containerRepository.findAll();
    }

    public Optional<Container> getContainerById(Long containerId) {
        return containerRepository.findById(containerId);
    }

    public Container saveContainer(Container container) {
        return containerRepository.save(container);
    }

    public Container updateContainer(Container container) {
        if (container.getContainerId() == null) {
            throw new IllegalArgumentException("Container ID cannot be null for update");
        }
        return containerRepository.save(container);
    }

    public void deleteContainer(Long containerId) {
        containerRepository.deleteById(containerId);
    }
}

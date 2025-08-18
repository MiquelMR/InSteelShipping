package com.mmauridev.insteelshipping.controllers;

import com.mmauridev.insteelshipping.models.Container;
import com.mmauridev.insteelshipping.services.ContainerService;
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

class ContainerControllerTests {

    @Mock
    private ContainerService containerService;

    @InjectMocks
    private ContainerController containerController;

    private Container container1;
    private Container container2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        container1 = new Container();
        container1.setCode("YYYYYY");

        container2 = new Container();
        container2.setCode("XXXXXX");
    }

    @Test
    void getAllContainers_returnsAllContainers() {
        when(containerService.getAllContainers()).thenReturn(Arrays.asList(container1, container2));

        List<Container> result = containerController.getAllContainers();

        assertThat(result).hasSize(2).contains(container1, container2);
        verify(containerService, times(1)).getAllContainers();
    }

    @Test
    void getContainerById_existingId_returnsContainer() {
        when(containerService.getContainerById(1L)).thenReturn(Optional.of(container1));

        ResponseEntity<Container> response = containerController.getContainerById(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(container1);
    }

    @Test
    void getContainerById_nonExistingId_returnsNotFound() {
        when(containerService.getContainerById(99L)).thenReturn(Optional.empty());

        ResponseEntity<Container> response = containerController.getContainerById(99L);

        assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }

    @Test
    void createContainer_savesContainer() {
        when(containerService.saveContainer(container1)).thenReturn(container1);

        Container result = containerController.createContainer(container1);

        assertThat(result).isEqualTo(container1);
        verify(containerService, times(1)).saveContainer(container1);
    }
}

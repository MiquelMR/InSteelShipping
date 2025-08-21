package com.mmauridev.insteelshipping.controllers;

import com.mmauridev.insteelshipping.controllers.api.FreightController;
import com.mmauridev.insteelshipping.models.Freight;
import com.mmauridev.insteelshipping.services.FreightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class FreightControllerTests {

    @Mock
    private FreightService freightService;

    @InjectMocks
    private FreightController freightController;

    private Freight freight1;
    private Freight freight2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        freight1 = new Freight();
        freight1.setCost(BigDecimal.valueOf(1000));

        freight2 = new Freight();
        freight2.setCost(BigDecimal.valueOf(800));
    }

    @Test
    void getAllFreights_returnsAllFreights() {
        when(freightService.getAllFreights()).thenReturn(Arrays.asList(freight1, freight2));

        List<Freight> result = freightController.getAllFreights();

        assertThat(result).hasSize(2).contains(freight1, freight2);
        verify(freightService, times(1)).getAllFreights();
    }

    @Test
    void getFreightById_existingId_returnsFreight() {
        when(freightService.getFreightById(1L)).thenReturn(Optional.of(freight1));

        ResponseEntity<Freight> response = freightController.getFreightById(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(freight1);
    }

    @Test
    void getFreightById_nonExistingId_returnsNotFound() {
        when(freightService.getFreightById(99L)).thenReturn(Optional.empty());

        ResponseEntity<Freight> response = freightController.getFreightById(99L);

        assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }

    @Test
    void createFreight_savesFreight() {
        when(freightService.saveFreight(freight1)).thenReturn(freight1);

        Freight result = freightController.createFreight(freight1);

        assertThat(result).isEqualTo(freight1);
        verify(freightService, times(1)).saveFreight(freight1);
    }
}

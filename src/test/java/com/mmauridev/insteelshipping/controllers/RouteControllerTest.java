package com.mmauridev.insteelshipping.controllers;

import com.mmauridev.insteelshipping.models.Route;
import com.mmauridev.insteelshipping.services.RouteService;
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

class RouteControllerTests {

    @Mock
    private RouteService routeService;

    @InjectMocks
    private RouteController routeController;

    private Route route1;
    private Route route2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        route1 = new Route();
        route1.setName("Route A");

        route2 = new Route();
        route2.setName("Route B");
    }

    @Test
    void getAllRoutes_returnsAllRoutes() {
        when(routeService.getAllRoutes()).thenReturn(Arrays.asList(route1, route2));

        List<Route> result = routeController.getAllRoutes();

        assertThat(result).hasSize(2).contains(route1, route2);
        verify(routeService, times(1)).getAllRoutes();
    }

    @Test
    void getRouteById_existingId_returnsRoute() {
        when(routeService.getRouteById(1L)).thenReturn(Optional.of(route1));

        ResponseEntity<Route> response = routeController.getRouteById(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(route1);
    }

    @Test
    void getRouteById_nonExistingId_returnsNotFound() {
        when(routeService.getRouteById(99L)).thenReturn(Optional.empty());

        ResponseEntity<Route> response = routeController.getRouteById(99L);

        assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }

    @Test
    void createRoute_savesRoute() {
        when(routeService.saveRoute(route1)).thenReturn(route1);

        Route result = routeController.createRoute(route1);

        assertThat(result).isEqualTo(route1);
        verify(routeService, times(1)).saveRoute(route1);
    }
}

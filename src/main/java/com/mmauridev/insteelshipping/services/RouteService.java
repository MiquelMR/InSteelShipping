package com.mmauridev.insteelshipping.services;

import com.mmauridev.insteelshipping.models.Route;
import com.mmauridev.insteelshipping.repositories.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public Optional<Route> getRouteById(Long routeId) {
        return routeRepository.findById(routeId);
    }

    public Route saveRoute(Route route) {
        return routeRepository.save(route);
    }

    public Route updateRoute(Route route) {
        if (route.getRouteId() == null) {
            throw new IllegalArgumentException("Route ID cannot be null for update");
        }
        return routeRepository.save(route);
    }

    public void deleteRoute(Long routeId) {
        routeRepository.deleteById(routeId);
    }
}

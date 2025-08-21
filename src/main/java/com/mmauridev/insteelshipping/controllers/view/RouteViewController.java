package com.mmauridev.insteelshipping.controllers.view;

import com.mmauridev.insteelshipping.models.Route;
import com.mmauridev.insteelshipping.services.RouteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/routes")
public class RouteViewController {
    private final RouteService routeService;

    public RouteViewController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping
    public String showRoutes(Model model) {
        List<Route> routes = routeService.getAllRoutes();
        model.addAttribute("routes", routes);
        return "routes";
    }
}

package com.mmauridev.insteelshipping.controllers.view;

import com.mmauridev.insteelshipping.models.Freight;
import com.mmauridev.insteelshipping.services.FreightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/freights")
public class FreightViewController {
    private final FreightService freightService;

    public FreightViewController(FreightService freightService) {
        this.freightService = freightService;
    }

    @GetMapping
    public String showFreight(Model model) {
        List<Freight> freights = freightService.getAllFreights();
        model.addAttribute("freights", freights);
        return "freights";
    }
}

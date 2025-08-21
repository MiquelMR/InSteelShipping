package com.mmauridev.insteelshipping.controllers.view;

import com.mmauridev.insteelshipping.models.Container;
import com.mmauridev.insteelshipping.services.ContainerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/containers")
public class ContainerViewController {
    private final ContainerService containerService;

    public ContainerViewController(ContainerService containerService) {
        this.containerService = containerService;
    }

    @GetMapping
    public String showContainers(Model model) {
        List<Container> containers = containerService.getAllContainers();
        model.addAttribute("containers", containers);
        return "containers";
    }
}

package com.mmauridev.insteelshipping.controllers.view;

import com.mmauridev.insteelshipping.models.Client;
import com.mmauridev.insteelshipping.services.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientViewController {

    private final ClientService clientService;

    public ClientViewController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String showClients(Model model) {
        List<Client> clients = clientService.getAllClients();
        model.addAttribute("clients", clients);
        return "clients"; // resolves to templates/clients.html
    }
}
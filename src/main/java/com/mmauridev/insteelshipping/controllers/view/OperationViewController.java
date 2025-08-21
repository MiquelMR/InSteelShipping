package com.mmauridev.insteelshipping.controllers.view;

import com.mmauridev.insteelshipping.models.Operation;
import com.mmauridev.insteelshipping.services.OperationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/operations")
public class OperationViewController {
    private final OperationService operationService;

    public OperationViewController(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping
    public String showOperations(Model model) {
        List<Operation> operations = operationService.getAllOperations();
        model.addAttribute("operations", operations);
        return "operations";
    }
}

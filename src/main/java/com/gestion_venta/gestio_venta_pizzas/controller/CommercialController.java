package com.gestion_venta.gestio_venta_pizzas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gestion_venta.gestio_venta_pizzas.model.entity.Commercial;
import com.gestion_venta.gestio_venta_pizzas.model.service.ICommercialService;

@Controller
public class CommercialController {

    private ICommercialService service;

    public CommercialController(ICommercialService service) {
        this.service = service;
    }

    @GetMapping({ "/commercials" })
    public String commercialLists(Model model) {
        model.addAttribute("title", "Facturación | Commercial");
        model.addAttribute("commercials", service.commercialLists());
        return "commercials";
    }

    @PostMapping("/register_commercial")
    public String saveCommercial(@ModelAttribute("commercial") Commercial commercial, BindingResult result,
            RedirectAttributes flash, SessionStatus status) {
        if (result.hasErrors()) {
            return "commercials";
        }
        flash.addAttribute("success", "comercio guardado con exito");
        service.saveCommercial(commercial);
        status.setComplete();
        return "redirect:/commercials";
    }

    @GetMapping("/commercials/new")
    public String ShowCommercialRegistrationForm(Model model) { // mostrar Formulario De Registrar comercio
        Commercial commercial = new Commercial();
        model.addAttribute("commercial", commercial);
        return "create_commercials";
    }

    @GetMapping("/commercials/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) { // mostrar Formulario De Editar
        model.addAttribute("commercial", service.getCommercialById(id));
        return "edit_commercial";
    }

    @PostMapping("/commercials/{id}")
    public String updateCommercial(@PathVariable Long id, @ModelAttribute("commercial") Commercial commercial,
            Model model) {
        Commercial ExistingCommercials = service.getCommercialById(id);
        ExistingCommercials.setId(id);
        ExistingCommercials.setName(commercial.getName());
        ExistingCommercials.setCity(commercial.getCity());
        ExistingCommercials.setCreateAt(commercial.getCreateAt());

        service.updateCommercial(ExistingCommercials);
        return "redirect:/commercials";
    }

    @GetMapping("/commercials/{id}")
    public String deleteClients(@PathVariable Long id) {
        if (service.getCommercialById(id).getOrders().size() == 0) {
            service.deleteCommercial(id);
            return "redirect:/customers";
            // return "customers";
        }
        return "redirect:/commercials";
    }
}

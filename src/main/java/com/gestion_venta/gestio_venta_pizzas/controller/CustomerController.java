package com.gestion_venta.gestio_venta_pizzas.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gestion_venta.gestio_venta_pizzas.model.entity.Customer;
import com.gestion_venta.gestio_venta_pizzas.model.service.ICustomerService;

@Controller
// @SessionAttributes("customer")
// @RequestMapping("customer")
public class CustomerController {

    private ICustomerService service;

    public CustomerController(ICustomerService service) {
        this.service = service;
    }

    @GetMapping({ "/", "/customers", "/index" })
    public String listCustomer(Model model) {
        model.addAttribute("title", "Facturación | Customer");
        model.addAttribute("customers", service.listCustomers());
        return "customers";
    }

    @PostMapping("/register")
    public String saveCustomer(@ModelAttribute("customer") Customer customer, BindingResult result,
            RedirectAttributes flash, SessionStatus status) {
        if (result.hasErrors()) {
            return "customers";
        }
        flash.addAttribute("success", "cliente guardado con exito");
        service.saveCustomer(customer);
        status.setComplete();
        return "redirect:/customers";
    }

    @GetMapping("/customers/new")
    public String ShowCustomerRegistrationForm(Model model) { // mostrar Formulario De Registrar Clientes
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "create_customer";
    }

    @GetMapping("/customers/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) { // mostrar Formulario De Editar
        model.addAttribute("customer", service.getCustomersById(id));
        return "edit_customer";
    }

    @PostMapping("/customers/{id}")
    public String updateClient(@PathVariable Long id, @ModelAttribute("customer") Customer customer,
            Model model) {
        Customer ExistingClients = service.getCustomersById(id);
        ExistingClients.setId(id);
        ExistingClients.setName(customer.getName());
        ExistingClients.setFirstSurname(customer.getFirstSurname());
        ExistingClients.setSecondSurname(customer.getSecondSurname());
        ExistingClients.setCity(customer.getCity());
        ExistingClients.setCreateAt(customer.getCreateAt());

        service.updateClients(ExistingClients);
        return "redirect:/customers";
    }

    @GetMapping("/customers/{id}")
    public String deleteClients(@PathVariable Long id) {
        if (service.getCustomersById(id).getOrders().size() == 0) {
            service.deleteClients(id);
            return "redirect:/customers";
            // return "customers";
        }
        // service.deleteClients(id);
        return "redirect:/customers";
    }

    @GetMapping({ "/consultation" })
    public String listConsultation(Model model) {
        model.addAttribute("consultations", service.listConsultation());
        return "consultation";
    }
    
}

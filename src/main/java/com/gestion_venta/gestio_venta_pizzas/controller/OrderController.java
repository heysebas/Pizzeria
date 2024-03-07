package com.gestion_venta.gestio_venta_pizzas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gestion_venta.gestio_venta_pizzas.model.entity.Order;
import com.gestion_venta.gestio_venta_pizzas.model.service.ICommercialService;
import com.gestion_venta.gestio_venta_pizzas.model.service.IOrderService;

@Controller
public class OrderController {

    @Autowired
    private ICommercialService service2;

    private IOrderService service;

    public OrderController(IOrderService service) {
        this.service = service;
    }

    @GetMapping({ "/orders" })
    public String commercialLists(Model model) {
        model.addAttribute("title", "Facturación | Order");
        model.addAttribute("orders", service.orderLists());
        model.addAttribute("commercial", service2.commercialLists());
        return "orders";
    }

    @PostMapping("/register_order")
    public String saveCommercial(@ModelAttribute("order") Order order, BindingResult result,
            RedirectAttributes flash, SessionStatus status) {
        if (result.hasErrors()) {
            return "redirect:/orders";
        }
        flash.addAttribute("success", "el pedidos fue guardado con exito");
        service.saveOrder(order);
        status.setComplete();
        return "redirect:/orders";
    }

    @GetMapping("/orders/new")
    public String ShowOrdersRegistrationForm(Model model) { // mostrar Formulario De Registrar comercio
        Order order = new Order();
        model.addAttribute("order", order);
        model.addAttribute("commercial", service2.commercialLists());
        return "create_orders";
    }

    @GetMapping("/orders/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) { // mostrar Formulario De Editar
        model.addAttribute("order", service.getOrderById(id));
        model.addAttribute("commercial", service2.getCommercialById(id));
        return "edit_order";
    }

    @PostMapping("/orders/{id}")
    public String updateOrder(@PathVariable Long id, @ModelAttribute("order") Order order,
            Model model) {
        Order ExistingOrders = service.getOrderById(id);
        ExistingOrders.setId(id);
        ExistingOrders.setAmount(order.getAmount());
        ExistingOrders.setCreateAt(order.getCreateAt());


        service.updateOrder(ExistingOrders);
        return "redirect:/orders";
    }

    @GetMapping("/orders/{id}")
    public String deleteClients(@PathVariable Long id) {
        service.deleteOrder(id);
        return "redirect:/orders";
    }
}

package com.gestion_venta.gestio_venta_pizzas.model.service;

import java.util.List;

import com.gestion_venta.gestio_venta_pizzas.model.entity.Order;

public interface IOrderService {
    public List<Order> orderLists(); // lista pedidos

    public void saveOrder(Order order); // guardar pedidos

    public Order getOrderById(Long id); // obtener pedidos por id

    public Order updateOrder(Order order); // actualizar pedidos

    public void deleteOrder(Long id); // eliminar pedidos

    
}

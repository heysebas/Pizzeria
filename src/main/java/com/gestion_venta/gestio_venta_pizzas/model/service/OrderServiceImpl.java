package com.gestion_venta.gestio_venta_pizzas.model.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestion_venta.gestio_venta_pizzas.model.entity.Order;
import com.gestion_venta.gestio_venta_pizzas.model.repository.IOrderDao;

import java.util.List;
@Service
public class OrderServiceImpl implements IOrderService{

    private final IOrderDao orderDao;
    // private  ICustomerDao customerDao;

    public OrderServiceImpl (IOrderDao orderDao){
        this.orderDao = orderDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> orderLists() {
        return orderDao.findAll();
    }

    @Override
    @Transactional
    public void saveOrder(Order order) {
        orderDao.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderDao.findById(id).get();
    }

    @Override
    public Order updateOrder(Order order) {
        return orderDao.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderDao.deleteById(id);
    }
}

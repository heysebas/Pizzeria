package com.gestion_venta.gestio_venta_pizzas.model.service;



import java.util.List;

import com.gestion_venta.gestio_venta_pizzas.model.entity.Customer;

public interface ICustomerService {
public List<Customer> listCustomers(); // lista de clientes
public void saveCustomer(Customer customer); // guardar clientes
public Customer getCustomersById(Long id); // obtener clienyes por id
public Customer updateClients(Customer customer); // actualizar clientes
public void deleteClients(Long id); // eliminar clientes
public List<Object[]> listConsultation();
}

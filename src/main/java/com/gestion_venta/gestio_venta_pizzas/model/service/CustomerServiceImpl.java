package com.gestion_venta.gestio_venta_pizzas.model.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestion_venta.gestio_venta_pizzas.model.entity.Customer;
import com.gestion_venta.gestio_venta_pizzas.model.repository.ICustomerDao;

import java.util.List;
@Service
public class CustomerServiceImpl implements ICustomerService{

    private final ICustomerDao customerDao;
    // private  ICustomerDao customerDao;

    public CustomerServiceImpl (ICustomerDao customerDao){
        this.customerDao = customerDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> listCustomers() {
        return customerDao.findAll();
    }

    @Override
    @Transactional
    public void saveCustomer(Customer customer) {
        customerDao.save(customer);
    }

    @Override
    public Customer getCustomersById(Long id) {
        return customerDao.findById(id).get();
    }

    @Override
    public Customer updateClients(Customer customer) {
        return customerDao.save(customer);
    }

    @Override
    public void deleteClients(Long id) {
        customerDao.deleteById(id);
    }



    @Override
    @Transactional(readOnly = true)
    public List<Object[]> listConsultation() {
        return customerDao.filter();
    }
}

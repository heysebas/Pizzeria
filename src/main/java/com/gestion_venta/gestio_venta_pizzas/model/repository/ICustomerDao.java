package com.gestion_venta.gestio_venta_pizzas.model.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gestion_venta.gestio_venta_pizzas.model.entity.Customer;

public interface ICustomerDao extends JpaRepository<Customer, Long> {

    @Query(
        value = "SELECT * FROM customers c LEFT JOIN order2 p ON c.id = p.customer_id ORDER BY c.first_surname ASC, c.second_surname ASC, c.name ASC",
            // value = "select * from customers cu left join order2 ord on cu.id = ord.customer_id order by cu.first_surname,cu.second_surname,cu.name asc",
            nativeQuery=true)
public List<Object[]> filter();

    // @Query("select c.first_surname, c.second_surname, c.name, o from customers c " +
    // "left join fetch order2 o on c.id = o.customer_id order by c.first_surname asc, c.second_surname asc, c.name asc = ?1")
    // public Customer findByName(String name);

    
}

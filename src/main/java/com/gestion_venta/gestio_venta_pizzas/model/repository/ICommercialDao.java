package com.gestion_venta.gestio_venta_pizzas.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;

import com.gestion_venta.gestio_venta_pizzas.model.entity.Commercial;

public interface ICommercialDao extends JpaRepository<Commercial, Long> {
// @Query("select c from Commercial c left join fetch c.orders f where c.name = ?1")
public Commercial findByName(String name);
}

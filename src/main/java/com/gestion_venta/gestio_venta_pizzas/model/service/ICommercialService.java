package com.gestion_venta.gestio_venta_pizzas.model.service;



import java.util.List;

import com.gestion_venta.gestio_venta_pizzas.model.entity.Commercial;

public interface ICommercialService {
public List<Commercial> commercialLists(); // lista comerciales
public void saveCommercial(Commercial commercial); // guardar comercial
public Commercial getCommercialById(Long id); // obtener comercial por id
public Commercial updateCommercial(Commercial commercial); // actualizar comercial
public void deleteCommercial(Long id); // eliminar comercial
}

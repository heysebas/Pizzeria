package com.gestion_venta.gestio_venta_pizzas.model.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestion_venta.gestio_venta_pizzas.model.entity.Commercial;
import com.gestion_venta.gestio_venta_pizzas.model.repository.ICommercialDao;

import java.util.List;
@Service
public class CommercialServiceImpl implements ICommercialService{

    private final ICommercialDao commercialDao;
    // private  ICustomerDao customerDao;

    public CommercialServiceImpl (ICommercialDao commercialDao){
        this.commercialDao = commercialDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Commercial> commercialLists() {
        return commercialDao.findAll();
    }

    @Override
    @Transactional
    public void saveCommercial(Commercial commercial) {
        commercialDao.save(commercial);
    }

    @Override
    public Commercial getCommercialById(Long id) {
        return commercialDao.findById(id).get();
    }

    @Override
    public Commercial updateCommercial(Commercial commercial) {
        return commercialDao.save(commercial);
    }

    @Override
    public void deleteCommercial(Long id) {
        commercialDao.deleteById(id);
    }
}

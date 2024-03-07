package com.gestion_venta.gestio_venta_pizzas.model.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "commercials", indexes = { @Index(name = "index_name", columnList = "name"),
                @Index(name = "index_email", unique = true, columnList = "email")})
public class Commercial implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String name;

    @Email
    private String email;

    private String city;


    @NotNull
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createAt;

    @OneToMany(mappedBy = "commercial", fetch = FetchType.LAZY)
    private List<Order> orders;

    public Commercial() {
            orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
    orders.add(order);
    }

    @Serial
    private static final long serialVersionUID = -4076903521346057017L;

}

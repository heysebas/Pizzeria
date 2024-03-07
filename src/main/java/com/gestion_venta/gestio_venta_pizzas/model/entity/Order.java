package com.gestion_venta.gestio_venta_pizzas.model.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "order2")
// @Table(name = "order", indexes = { @Index(name = "index_order", columnList =
// "order") })
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createAt;

    @NotEmpty
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @NotEmpty
    @ManyToOne(fetch = FetchType.LAZY)
    private Commercial commercial;

    @PrePersist
    public void PrePersist() {
        createAt = new Date();
    }

    @Serial
    private static final long serialVersionUID = 45450201L;

}

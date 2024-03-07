package com.gestion_venta.gestio_venta_pizzas.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Data
@Entity
@Table(name = "customers", indexes = { @Index(name = "index_name", columnList = "name"),
                @Index(name = "index_email", unique = true, columnList = "email"),
                @Index(name = "index_fullname", columnList = "name, firstSurname") })

// indexes = { @Index(name = "index_name", columnList = "name"),
// @Index(name = "index_fullname", columnList = "name, firstSurname,
// secondSurname, city, category, create_at") })
public class Customer implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @NotEmpty
        private String name;
        @NotEmpty
        private String firstSurname;
        
        private String secondSurname;
        @Email
        private String email;
        
        private String city;
        

        @NotNull
        @Column(name = "create_at")
        @Temporal(TemporalType.DATE)
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date createAt;

        @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
        private List<Order> orders;

        public Customer() {
                orders = new ArrayList<>();
        }

        public void addOrder(Order order) {
        orders.add(order);
        }

        @Serial
        private static final long serialVersionUID = -4076903521346057017L;
}

package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "address")
    private String address;

    @Column(name = "totalPrice")
    private int totalPrice;

    @ManyToOne
    private Users user_id;

    public Invoice(Long id, String address, int totalPrice) {
        this.id = id;
        this.address = address;
        this.totalPrice = totalPrice;
    }
}

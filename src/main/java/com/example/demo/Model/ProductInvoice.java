package com.example.demo.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(ProductInvoiceId.class)
public class ProductInvoice {
    @Id
    @Column(name = "product_id")
    private Long productId;

    @Id
    @Column(name = "invoice_id")
    private Long invoiceId;

    private int amount, totalCost;

}

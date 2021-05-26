package com.example.demo.Model;

import lombok.Data;

import java.io.Serializable;
@Data
public class ProductInvoiceId implements Serializable {
    private Long productId, invoiceId;
}

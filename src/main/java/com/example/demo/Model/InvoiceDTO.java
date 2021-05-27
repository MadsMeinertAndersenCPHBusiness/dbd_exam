package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InvoiceDTO {
    String address;
    int totalPrice;
    Collection<InvoiceProductDTO> products;

    public InvoiceDTO(String address, int totalPrice) {
        this.address = address;
        this.totalPrice = totalPrice;
    }
}

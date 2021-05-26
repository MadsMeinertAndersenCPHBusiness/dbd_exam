package com.example.demo.Repository;

import com.example.demo.Model.Invoice;
import com.example.demo.Model.ProductInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInvoiceRepository extends JpaRepository<ProductInvoice, Integer> {

}

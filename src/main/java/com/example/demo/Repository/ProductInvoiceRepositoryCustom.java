package com.example.demo.Repository;

import com.example.demo.Model.ProductInvoice;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInvoiceRepositoryCustom {
    boolean insertProductInvoice(ProductInvoice productInvoice);
}

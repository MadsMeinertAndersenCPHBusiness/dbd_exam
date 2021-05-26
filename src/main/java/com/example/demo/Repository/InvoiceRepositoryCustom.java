package com.example.demo.Repository;

import com.example.demo.Model.Invoice;
import com.example.demo.Model.InvoiceDTO;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Collection;

@Repository
public interface InvoiceRepositoryCustom {
    Collection<InvoiceDTO> getInvoicesForUser(Long userId);
    boolean updateInvoice(Invoice invoice) throws SQLException;
    int createInvoice(Invoice invoice);
}

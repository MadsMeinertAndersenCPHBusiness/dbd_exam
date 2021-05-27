package com.example.demo.Repository;

import com.example.demo.Model.Invoice;
import com.example.demo.Model.InvoiceDTO;
import com.example.demo.Model.InvoiceProductDTO;
import com.example.demo.Model.ProductDTO;
import com.example.demo.dbUtility.DbConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

@Repository
public class InvoiceRepositoryCustomImpl implements InvoiceRepositoryCustom {
    @Autowired
    DbConnection dbConnection = new DbConnection();
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Collection<InvoiceProductDTO> getProducts(Long invoiceId) {
        Collection<InvoiceProductDTO> invoiceProductDTOS = new ArrayList<>();
        try (Connection conn = dbConnection.connect()) {

            PreparedStatement stmt = conn.prepareCall("select * from ProductInvoice where invoice_id = " + invoiceId);
            var result = stmt.executeQuery();
            while (result.next()) {
                        var name = productRepository.findById(result.getLong("product_id"));
                        if (name.isEmpty()){
                            return null;
                        }
                InvoiceProductDTO invoice = new InvoiceProductDTO(
                        name.get().getName(),
                        result.getInt("amount"),
                        result.getInt("totalCost")

                );

                invoiceProductDTOS.add(invoice);
            }
        } catch (Exception exception) {
            System.out.println("An error has occurred.");
            System.out.println("See full details below.");
            exception.printStackTrace();
        }
        return invoiceProductDTOS;

    }
    @Override
    public Collection<InvoiceDTO> getInvoicesForUser(Long userId) {
        Collection<InvoiceDTO> invoices = new ArrayList<>();
        try (Connection conn = dbConnection.connect()) {

            PreparedStatement stmt = conn.prepareCall("select * from Invoice where user_id = " + userId);
            var result = stmt.executeQuery();
            while (result.next()) {
                Invoice invoice = new Invoice(
                        result.getLong("id"),
                        result.getString("address"),
                        result.getInt("totalPrice")
                );
                var products = getProducts(invoice.getId());

                InvoiceDTO invoiceDTO = new InvoiceDTO(invoice.getAddress(), invoice.getTotalPrice(), products);

                invoices.add(invoiceDTO);
            }
        } catch (Exception exception) {
            System.out.println("An error has occurred.");
            System.out.println("See full details below.");
            exception.printStackTrace();
        }
        return invoices;
    }

    @Override
    public boolean updateInvoice(Invoice invoice) {
        Invoice updatedInvoice = null;
        try (Connection conn = dbConnection.connect()) {

            System.out.println("update Invoice set address = " + invoice.getAddress() + " " +
                    ", totalPrice = " + invoice.getTotalPrice() + " where id = " + invoice.getId());

            PreparedStatement stmt = conn.prepareCall("update Invoice set address = '" + invoice.getAddress() + "' " +
                    ", totalPrice = " + invoice.getTotalPrice() + " where id = " + invoice.getId());
            stmt.execute();
            return true;

        } catch (Exception exception) {
            System.out.println("An error has occurred.");
            System.out.println("See full details below.");
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public int createInvoice(Invoice invoice) {
        int id = -1;
        try (Connection conn = dbConnection.connect()) {
            PreparedStatement stmt = conn.prepareCall("select insert_invoice(?,?,?)");
            stmt.setString(1, invoice.getAddress());
            stmt.setInt(2, invoice.getTotalPrice());
            stmt.setInt(3, Integer.parseInt(invoice.getUser_id().getId().toString()));
            var result = stmt.executeQuery();
            while (result.next()){
            id = result.getInt("insert_invoice");
            }
            return id;

        } catch (Exception exception) {
            System.out.println("An error has occurred.");
            System.out.println("See full details below.");
            exception.printStackTrace();
        }
        return id;
    }

}

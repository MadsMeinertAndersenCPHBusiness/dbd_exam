package com.example.demo.Repository;

import com.example.demo.Model.Invoice;
import com.example.demo.Model.InvoiceDTO;
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

    @Override
    public Collection<InvoiceDTO> getInvoicesForUser(Long userId) {
        Collection<InvoiceDTO> invoices = new ArrayList<>();
        try (Connection conn = dbConnection.connect()) {

            PreparedStatement stmt = conn.prepareCall("select * from Invoice where user_id = " + userId);
            var result = stmt.executeQuery();
            while (result.next()) {
                InvoiceDTO invoice = new InvoiceDTO(
                        result.getString("address"),
                        result.getInt("totalPrice")
                );
                invoices.add(invoice);
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

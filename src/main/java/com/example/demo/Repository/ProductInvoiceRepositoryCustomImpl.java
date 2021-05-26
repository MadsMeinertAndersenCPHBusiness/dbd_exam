package com.example.demo.Repository;

import com.example.demo.Model.ProductInvoice;
import com.example.demo.dbUtility.DbConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
@Repository
public class ProductInvoiceRepositoryCustomImpl implements ProductInvoiceRepositoryCustom{
    @Autowired
    private DbConnection dbConnection;

    @Override
    public boolean insertProductInvoice(ProductInvoice productInvoice) {
        try(Connection conn = dbConnection.connect()) {

            PreparedStatement stmt = conn.prepareCall("call insert_productinvoice(?,?,?,?)");
            stmt.setInt(1, Integer.parseInt(productInvoice.getProductId().toString()));
            stmt.setInt(2, Integer.parseInt(productInvoice.getInvoiceId().toString()));
            stmt.setInt(3, productInvoice.getAmount());
            stmt.setInt(4, productInvoice.getTotalCost());
            stmt.execute();
            return true;

        }catch (Exception exception){
            System.out.println("An error has occurred.");
            System.out.println("See full details below.");
            exception.printStackTrace();
        }
        return false;
    }
}

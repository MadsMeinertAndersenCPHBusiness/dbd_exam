package com.example.demo.Repository;

import com.example.demo.Model.Product;
import com.example.demo.Model.ProductDTO;
import com.example.demo.dbUtility.DbConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;
@Repository
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    @Autowired
    private DbConnection dbConnection;

    @Override
    public boolean insertProduct(Product product) {
        try(Connection conn = dbConnection.connect()) {

            PreparedStatement stmt = conn.prepareCall("call insert_product(?,?,?,?,?)");
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getBrand());
            stmt.setInt(3, product.getPrice());
            stmt.setBoolean(4, product.isOrganic());
            stmt.setString(5, product.getProduct_type().toString());
            stmt.execute();
            return true;
        }catch (Exception exception){
            System.out.println("An error has occurred.");
            System.out.println("See full details below.");
            exception.printStackTrace();
        }
        return false;
    }


    @Override
    public Collection<ProductDTO> getView(String view) {
        Collection<ProductDTO> products = new ArrayList<>();
        try(Connection conn = dbConnection.connect()) {

            PreparedStatement stmt = conn.prepareCall("select * from " + view);
            var result = stmt.executeQuery();
            while (result.next()){
                ProductDTO product = new ProductDTO(
                        result.getString("name"),
                        result.getString("brand"),
                        result.getInt("price"),
                        result.getBoolean("organic"),
                        result.getString("product_type")
                );
                products.add(product);
            }
        }catch (Exception exception){
            System.out.println("An error has occurred.");
            System.out.println("See full details below.");
            exception.printStackTrace();
        }
        return products;
    }

    @Override
    public boolean deleteProduct(int id) {
        try(Connection conn = dbConnection.connect()) {

            PreparedStatement stmt = conn.prepareCall("call delete_product(?)");
            stmt.setInt(1, id);
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

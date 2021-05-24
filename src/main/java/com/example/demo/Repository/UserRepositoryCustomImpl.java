package com.example.demo.Repository;

import com.example.demo.Model.Users;
import com.example.demo.dbUtility.DbConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom{

    @Autowired
    private DbConnection dbConnection;

    @Override
    public boolean createUser(Users user) {
        try(Connection conn = dbConnection.connect()) {

            PreparedStatement stmt = conn.prepareCall("call create_user(?,?,?,?)");
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getAddress());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getPassword());
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
    public boolean deleteUser(int id) {
        try(Connection conn = dbConnection.connect()) {

            PreparedStatement stmt = conn.prepareCall("call delete_user(?)");
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

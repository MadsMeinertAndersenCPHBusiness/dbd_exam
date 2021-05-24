package com.example.demo.Repository;

import com.example.demo.Model.Users;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryCustom {
    boolean createUser(Users user);
    boolean deleteUser(int id);
}

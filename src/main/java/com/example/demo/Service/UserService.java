package com.example.demo.Service;

import com.example.demo.Model.Users;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Repository.UserRepositoryCustom;
import com.example.demo.Repository.UserRepositoryCustomImpl;
import com.example.demo.dbUtility.DbConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserRepositoryCustomImpl userRepositoryCustom;

    public Collection<Users> findAll(){
        return userRepository.findAll();
    }

    public boolean createUser(Users user){
        return userRepositoryCustom.createUser(user);
    }

    public boolean deleteUser(int id){
        return userRepositoryCustom.deleteUser(id);
    }

    public Optional<Users> findOne(Long id){
        return userRepository.findById(id);
    }



}

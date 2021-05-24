package com.example.demo.Controller;

import com.example.demo.Model.Users;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("")
    public boolean addUser(@RequestBody Users user){
        return userService.createUser(user);
    }
    @DeleteMapping("/{id}")
    public boolean getUser(@PathVariable int id){
        return userService.deleteUser(id);
    }
    @GetMapping("")
    public Collection<Users> findAll(){
        return userService.findAll();
    }
}

package com.lxb.example.demo.controller;

import com.lxb.example.demo.exception.ParamValidException;
import com.lxb.example.demo.exception.UserNotFoundException;
import com.lxb.example.demo.exception.UsernameAlreadyExistsException;
import com.lxb.example.demo.models.User;
import com.lxb.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping
    public User update(@RequestBody User user) {
        return userService.save(user);
    }

    @PostMapping
    public User create(@Validated @RequestBody User user, BindingResult result) throws UsernameAlreadyExistsException, ParamValidException {
        if(result.hasErrors()) {
            throw new ParamValidException("参数格式错误", result.getAllErrors());
        }
        return userService.save(user);
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{username}")
    public User findByUsername(@PathVariable("username") String username) throws UserNotFoundException {
        return userService.findByUsername(username);
    }
}

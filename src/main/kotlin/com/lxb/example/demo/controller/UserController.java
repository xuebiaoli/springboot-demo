package com.lxb.example.demo.controller;

import com.lxb.example.demo.exception.InvalidParamException;
import com.lxb.example.demo.exception.UserNotFoundException;
import com.lxb.example.demo.exception.UsernameAlreadyExistsException;
import com.lxb.example.demo.models.User;
import com.lxb.example.demo.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "用户接口")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // //支持xml,json 设置 produces = "application/xml,application/json")
    @ApiOperation(value="修改用户信息", notes = "第一个测试api", response = User.class, produces = "application/json")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "user", value = "用户信息", required = true, dataTypeClass = User.class)
    )
    @PutMapping
    public User update(@RequestBody User user) {
        return userService.save(user);
    }

    @ApiOperation(value="创建用户信息", response = User.class)
    @ApiImplicitParams(
            @ApiImplicitParam(name = "user", value = "用户信息", required = true, dataTypeClass = User.class)
    )
    @PostMapping
    public User create(@Validated @RequestBody User user, BindingResult result) throws UsernameAlreadyExistsException, InvalidParamException {
        if(result.hasErrors()) {
            throw new InvalidParamException("参数格式错误", result.getAllErrors());
        }
        return userService.save(user);
    }

    @ApiOperation(value="获取用户信息", response = User.class)
    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @ApiOperation(value="根据用户名获取用户信息", response = User.class)
//    @ApiImplicitParams(
//            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String")
//    )
    @GetMapping("/{username}")
    public User findByUsername(@PathVariable("username") String username) throws UserNotFoundException {
        return userService.findByUsername(username);
    }
}

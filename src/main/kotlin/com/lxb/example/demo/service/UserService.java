package com.lxb.example.demo.service;

import com.lxb.example.demo.exception.UserNotFoundException;
import com.lxb.example.demo.exception.UsernameAlreadyExistsException;
import com.lxb.example.demo.models.User;
import com.lxb.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {
    /*
    * Java变量的初始化顺序为：静态变量或静态语句块–>实例变量或初始化语句块–>构造方法–>@Autowired
    * */

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Cacheable(value = "user", key = "'user_' + #userId")
    public User findById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Cacheable(value = "user", key = "'user_' + #username")
    public User findByUsername(String username) throws UserNotFoundException {
        User u = userRepository.findUserByUsername(username);
        if (u == null) {
            throw new UserNotFoundException("不存在用户名为'" + username + "'的用户");
        }
        return u;
    }

    @CachePut(value = "user", key = "'user_' + #u.username", unless = "#u eq null")
    public User save(User u) throws UsernameAlreadyExistsException {
        if (u.getId() != null && u.getId() > 0) {
            // update
            u.setUpdateTime(new Date());
        } else {
            // create
            if (userRepository.findUserByUsername(u.getUsername()) != null) {
                throw new UsernameAlreadyExistsException("用户名已存在");
            }
            u.setUpdateTime(new Date());
            u.setCreateTime(new Date());
        }

        return userRepository.save(u);
    }

    public List<User> findAll() {
//        userRepository.findAll(new Sort(Sort.Direction.ASC, "username"))
        return userRepository.findAll();
    }

    @CacheEvict(value = "user", key = "'user_' + #userId", condition = "#result eq true")
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }
}

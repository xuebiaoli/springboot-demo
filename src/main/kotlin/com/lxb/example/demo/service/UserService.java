package com.lxb.example.demo.service;

import com.lxb.example.demo.exception.UserNotFoundException;
import com.lxb.example.demo.exception.UsernameAlreadyExistsException;
import com.lxb.example.demo.models.User;
import com.lxb.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User findByUsername(String username) throws UserNotFoundException {
        User u = userRepository.findUserByUsername(username);
        if (u == null) {
            throw new UserNotFoundException("不存在用户名为'" + username + "'的用户");
        }
        return u;
    }

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
}

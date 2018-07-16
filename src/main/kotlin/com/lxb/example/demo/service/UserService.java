package com.lxb.example.demo.service;

import com.lxb.example.demo.models.User;
import com.lxb.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    public User save(User u) {
        if (u.getId() != null && u.getId() > 0) {
            // update
            u.setUpdateTime(new Date());
        } else {
            // create
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

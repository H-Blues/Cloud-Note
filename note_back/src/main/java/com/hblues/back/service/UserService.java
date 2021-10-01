package com.hblues.back.service;

import com.hblues.back.dao.UserDao;
import com.hblues.back.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public User getUserByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public boolean isExist(String username) {
        User user = getUserByUsername(username);
        return user != null;
    }

    public void addUser(User user) {
        userDao.save(user);
    }
}

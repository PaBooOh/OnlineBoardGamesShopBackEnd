package com.wchen.onlineboardgamesshop.Service;

import com.wchen.onlineboardgamesshop.Entity.User;
import com.wchen.onlineboardgamesshop.Repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private LoginRepository loginRepository;

    @Override
    @Cacheable(value = "user_login", key = "#username")
    public User findUserByUserName(String username) {
        return loginRepository.findUserByUserName(username);
    }

    @Override
    public User findUserByUUid(long uuid) {
        return loginRepository.findUserByUUid(uuid);
    }

    @Override
    public User findUserByEmail(String email) {
        return loginRepository.findUserByEmail(email);
    }
}

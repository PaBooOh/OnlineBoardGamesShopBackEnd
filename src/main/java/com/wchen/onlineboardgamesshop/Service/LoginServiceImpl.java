package com.wchen.onlineboardgamesshop.Service;

import com.wchen.onlineboardgamesshop.Entity.User;
import com.wchen.onlineboardgamesshop.Repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;
    @Autowired
    public LoginServiceImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }
    @Override
    public User login(String frontUsername, String frontPassword) {
        // check if username exist
        return loginRepository.findUserByUsernameAndPassword(frontUsername, frontPassword);
    }

//    @Override
//    public User findUserByUUid(long uuid) {
//        return loginRepository.findUserByUUid(uuid);
//    }

//    @Override
//    public User findUserByEmail(String email) {
//        return loginRepository.findUserByEmail(email);
//    }
}

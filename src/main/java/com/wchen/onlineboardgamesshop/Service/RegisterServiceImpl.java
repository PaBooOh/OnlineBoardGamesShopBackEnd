package com.wchen.onlineboardgamesshop.Service;

import com.wchen.onlineboardgamesshop.Controller.LoginController;
import com.wchen.onlineboardgamesshop.Entity.User;
import com.wchen.onlineboardgamesshop.Repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RegisterServiceImpl implements RegisterService{

    private final RegisterRepository registerRepository;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    public RegisterServiceImpl(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    @Override
    public User register(String username, String password, String email) {
        // Check if username exists
        if (registerRepository.findUserByUserName(username) == null) // allow register
        {
            logger.info("User can be registered");
            registerRepository.createUser(username, password, email, "client");
            return registerRepository.findUserByUserName(username); // return registered User
        }
        logger.info("User already exists");
        return null;
    }
}
